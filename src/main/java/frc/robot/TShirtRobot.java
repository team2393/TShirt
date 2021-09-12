package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpiutil.math.MathUtil;


/** TShirt Cannon Robot */
public class TShirtRobot extends TimedRobot
{
    private static final double TILT_SPEED = 0.3;
 
    // Motors
    private final VictorSP tilt_motor = new VictorSP(RobotMap.PWM_TILT);

    private final DigitalOutput tube_a = new DigitalOutput(8);

    private final DigitalOutput red = new DigitalOutput(5);
    private final DigitalOutput green = new DigitalOutput(4);
    private final DigitalOutput blue = new DigitalOutput(3);

    /** Run once on startup */
    @Override
    public void robotInit()
    {
        System.out.println("\n\n\n\n");
        System.out.println("*******************");
        System.out.println("** T-shirt robot **");
        System.out.println("*******************");
    }

    /** Called all the time */
    @Override
    public void robotPeriodic()
    {
        // Trigger scheduler so that commands will "work"
        CommandScheduler.getInstance().run();
    }

    private final static double MOTOR_LIMIT = 0.2;

    double limit(double motor_value)
    {
        if (motor_value > MOTOR_LIMIT)
            return MOTOR_LIMIT;
        if (motor_value < -MOTOR_LIMIT)
            return -MOTOR_LIMIT;
        return motor_value;
    }

    long fire_a = 0;

    @Override
    public void teleopInit()
    {
        super.teleopInit();
        // Clear old press indication
        OI.joystick.getAButtonPressed();

        // TODO Enable PMW on LEDs for pattern3?
        red.enablePWM(0);
        green.enablePWM(0);
        blue.enablePWM(0);
    }

    /** Called in teleop mode */
    @Override
    public void teleopPeriodic()
    {
        // Tilt
        if (OI.isTiltUpPushed())
            tilt_motor.set(TILT_SPEED);
        else if (OI.isTiltDownPushed())
            tilt_motor.set (-TILT_SPEED);
        else
            tilt_motor.set (0);
        
        // Drive
        // System.out.println("Speed: " + OI.getSpeed() + ", Turn: " + OI.getTurn());
        double left  =  OI.getSpeed() + OI.getTurn();
        double right = -OI.getSpeed() + OI.getTurn();

        left = MathUtil.clamp(left, -1, 1);
        right = MathUtil.clamp(right, -1, 1);

        // Tubes
        // // When the fire button is pressed,
        // // open valve for 500 ms
        // long now = System.currentTimeMillis();
        // if (OI.joystick.getAButtonPressed())
        //     fire_a = now + 500;
        // if (now < fire_a)
        //     tube_a.set(true);
        // else
        //     tube_a.set(false);

        // LED
        led_pattern3();
    }
    
    // First pattern
    private void led_pattern1()
    {        
        long now = System.currentTimeMillis();
        long step = (now / 500) % 3;
        red.set(step == 0);
        green.set(step == 1);
        blue.set(step == 2);
    }

    // Table of LED pattern steps
    // One step uses 4 numbers:
    // 0/1 to turn red LED on,
    // 0/1 to turn green LED on,
    // 0/1 to turn blue LED on,
    // duration of step in milliseconds
    final private int steps[][] = new int[][]
    {
        //R  G  B    ms
        { 1, 1, 1, 1000 },

        { 1, 0, 0,  100 },
        { 0, 1, 0,  100 },
        { 0, 0, 1,  100 },
        { 1, 0, 0,  100 },
        { 0, 1, 0,  100 },
        { 0, 0, 1,  100 },

        { 1, 0, 0,  100 },
        { 0, 0, 0,  100 },
        { 1, 0, 0,  100 },
        { 0, 0, 0,  100 },
        { 1, 0, 0,  100 },
        { 0, 0, 0,  100 },
        { 1, 0, 0,  100 },
        { 0, 0, 0,  100 },

        { 0, 1, 0,  100 },
        { 0, 0, 0,  100 },
        { 0, 1, 0,  100 },
        { 0, 0, 0,  100 },
        { 0, 1, 0,  100 },
        { 0, 0, 0,  100 },
        { 0, 1, 0,  100 },
        { 0, 0, 0,  100 },

        { 0, 0, 1,  100 },
        { 0, 0, 0,  100 },
        { 0, 0, 1,  100 },
        { 0, 0, 0,  100 },
        { 0, 0, 1,  100 },
        { 0, 0, 0,  100 },
        { 0, 0, 1,  100 },
        { 0, 0, 0,  100 },
    };

    int step_index = steps.length;
    long next_step_time = 0;

    // Table-based pattern
    private void led_pattern2()
    {        
        long now = System.currentTimeMillis();
      
        if (now >= next_step_time)
        {
            // Determine index of next step
            step_index = step_index + 1;
            if (step_index >= steps.length)
                step_index = 0;

            // How long does this step last,
            // when do we need to move on?
            next_step_time = now + steps[step_index][3];
        }
        red.set(steps[step_index][0] == 1);
        green.set(steps[step_index][1] == 1);
        blue.set(steps[step_index][2] == 1);
    }

    // PWM pattern
    private void led_pattern3()
    {        
        // Note 'enablePWM' in teleopInit
        long now = System.currentTimeMillis();

        // Every 1000ms == 1 second,
        // define 4 steps (0, 1, 2, 3),
        // and within that step, define a phase 0.0 ... 1.0
        long step = (now / 1000) % 4;
        double phase = (now % 1000) / 1000.0;
        // System.out.println(step + " @ " + phase);
        red.updateDutyCycle  (step == 0 || step == 3  ?  phase : 0.0);
        green.updateDutyCycle(step == 1 || step == 3  ?  phase : 0.0);
        blue.updateDutyCycle (step == 2 || step == 3  ?  phase : 0.0);
    }
}
