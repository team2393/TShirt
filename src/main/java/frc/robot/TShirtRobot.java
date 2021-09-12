package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/** TShirt Cannon Robot */
public class TShirtRobot extends TimedRobot
{
    // Motors
    private final VictorSP tilt_motor = new VictorSP(RobotMap.PWM_TILT);

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

    /** Called in teleop mode */
    @Override
    public void teleopPeriodic()
    {
        if (OI.isTiltUpPushed())
            tilt_motor.set(0.3);
        else if (OI.isTiltDownPushed())
            tilt_motor.set (-0.3);
        else
            tilt_motor.set (0);
    }
}
