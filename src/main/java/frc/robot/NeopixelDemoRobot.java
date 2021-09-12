package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;

/** Neopixel Demo Robot */
public class NeopixelDemoRobot extends TimedRobot
{
    private final static int NEOPIXEL_COUNT = 30;
    private final AddressableLED neopixel = new AddressableLED(9);

    /** Is user button currently pressed? */
    private boolean user_pressed = false;

    /** Pattern to use: 0, 1, 2 */
    private int pattern = 0;
    
    @Override
    public void robotInit()
    {
        System.out.println("\n\n\n\n");
        System.out.println("*****************************");
        System.out.println("** NeopixelDemoRobot robot **");
        System.out.println("*****************************");

        neopixel.setLength(NEOPIXEL_COUNT);
    }
    
    @Override
    public void teleopInit()
    {
        neopixel.start();
    }

    @Override
    public void teleopPeriodic()
    {
        if (pattern == 0)
            blink_pattern();
        else if (pattern == 1)
            dot_pattern();
        else
            rainbow();
        
        // If USER button is pressed, change to next pattern
        if (RobotController.getUserButton())
        {
            // But only once, not again until button is released!
            if (! user_pressed)
            {
                user_pressed = true;
                pattern = (pattern + 1) % 3;
            }
        }
        else
            user_pressed = false;
    }

    @Override
    public void disabledInit()
    {
        // Turn LEDs off when robot is disabled
        // Otherwise it would be 'stuck' on the last pattern
        AddressableLEDBuffer pattern = new AddressableLEDBuffer(NEOPIXEL_COUNT);
        neopixel.setData(pattern);
    }
    
    private void blink_pattern()
    {
        // For step 0, 1, 2, turn R, G, B on        
        long now = System.currentTimeMillis();
        long step = (now / 500) % 3;
   
        AddressableLEDBuffer pattern = new AddressableLEDBuffer(NEOPIXEL_COUNT);
        for (int i=0; i<NEOPIXEL_COUNT; ++i)
            pattern.setRGB(i,
                           step == 0 ? 255:0,
                           step == 1 ? 255:0,
                           step == 2 ? 255:0);
        neopixel.setData(pattern);
    }

    private void dot_pattern()
    {        
        long now = System.currentTimeMillis();
        // Math.sin(...) gives number -1..1, changing over 3 seconds
        // Scale that to 0 .. (NEOPIXEL_COUNT-1)
        long pos = Math.round((NEOPIXEL_COUNT-1) * (0.5 + 0.5*Math.sin(2.0*Math.PI*(now%3000)/3000.0)));

        // Show dark blue with a red dot at 'pos'
        AddressableLEDBuffer pattern = new AddressableLEDBuffer(NEOPIXEL_COUNT);
        for (int i=0; i<NEOPIXEL_COUNT; ++i)
            if (i == pos)
                pattern.setRGB(i, 255, 0, 0);
            else
                pattern.setRGB(i, 0, 0, 50);
        neopixel.setData(pattern);
    }

    private void rainbow()
    {
        // Instead of RGB we use Hue, Saturation, Value
        // Actually S & V are always full (255)
        // Hue uses 0 .. 180 range to select color
        long now = System.currentTimeMillis();

        // 'hue' cycles from 0 to 180 in 3 seconds
        long hue = (now * 180 / 3000) % 180;

        // First pixel uses that hue which changes over 3 seconds.
        // Following pixels run from that hue to hue+180 (wrapping around at 180)
        // so that LED string always shows the full spectrum,
        // rolling around the string.
        AddressableLEDBuffer pattern = new AddressableLEDBuffer(NEOPIXEL_COUNT);
        for (int i=0; i<NEOPIXEL_COUNT; ++i)
            pattern.setHSV(i, (int) (hue + 180.0*i/NEOPIXEL_COUNT) % 180, 255, 255);
        neopixel.setData(pattern);
    }
}
