package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/** Operator interface: Which joystick axis, which button does what?  */
public class OI
{
    public static final XboxController joystick = new XboxController(0);

    /** @return Is the 'up' button pushed? */
    public static boolean isTiltUpPushed()
    {
        return joystick.getPOV() == 0;
    }

    /** @return Is the 'down' button pushed? */
    public static boolean isTiltDownPushed()
    {
        return joystick.getPOV() == 180;
    }
    
    /** @return Forward/backward speed value -1..1 */
    public static double getSpeed()
    {
        return -joystick.getRawAxis(1);
    }
    
    /** @return Left/right value -1..1 */
    public static double getTurn()
    {
        return joystick.getRawAxis(4);
    }
    
    public static boolean isTopPressed() 
    {
        return joystick.getYButton();
    }

    public static boolean isLeftPressed() 
    {
        return joystick.getXButton();
    }

    public static boolean isRightPressed() 
    {
        return joystick.getBButton();
    }
    
    // Idea: Use one 'safe' fire trigger,
    // require holding both bumpers to fire.
    // Robot code then needs to figure our which tube to use
    public static boolean isFirePressed()
    {
        // Require holding right button and pressing the left to 'fire'
        return joystick.getBumper(Hand.kLeft) &&
               joystick.getBumper(Hand.kRight);
    }
}
