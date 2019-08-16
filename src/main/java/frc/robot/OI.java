package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.util.PDPController;

/** Operator interface: Which joystick axis, which button does what?  */
public class OI
{
    private static final Joystick stick = new Joystick(0);

    // TODO What stick/button should to what?
    
    public static boolean isFirePressed()
    {
        // Require holding right button and pressing the left to 'fire'
        return stick.getRawButton(PDPController.RIGHT_FRONT_BUTTON)  &&
               stick.getRawButtonPressed(PDPController.LEFT_FRONT_BUTTON);
    }

    public static double getSpeed()
    {
        return stick.getRawAxis(PDPController.RIGHT_STICK_VERTICAL);
    }

    public static double getTurn()
    {
        return stick.getRawAxis(PDPController.RIGHT_STICK_HORIZONTAL);
    }

    public static double getTilt()
    {
        return stick.getRawAxis(PDPController.LEFT_STICK_VERTICAL);
    }
}
