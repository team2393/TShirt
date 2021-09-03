package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/** Operator interface: Which joystick axis, which button does what?  */
public class OI
{
    public static final XboxController joystick = new XboxController(0);

    // TODO What stick/button should to what?
    
    public static boolean isFirePressed()
    {
        // Require holding right button and pressing the left to 'fire'
        return joystick.getRawButton(XboxController.Button.kBumperLeft.value) &&
               joystick.getRawButton(XboxController.Button.kBumperRight.value);
    }

    public static double getSpeed()
    {
        return joystick.getY(Hand.kRight);
    }

    public static double getTurn()
    {
        return joystick.getX(Hand.kRight);
    }

    public static double getTilt()
    {
        return joystick.getY(Hand.kLeft);
    }
}
