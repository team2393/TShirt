package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


/** TShirt Cannon Robot */
public class TShirtRobot extends TimedRobot
{
    // Motors
    private final VictorSP tilt_motor = new VictorSP(RobotMap.PWM_TILT);
    private double tilt_speed = 0.5;
    private final VictorSP left_motor = new VictorSP(RobotMap.PWM_LEFT);
    private final VictorSP right_motor = new VictorSP(RobotMap.PWM_RIGHT);
    DifferentialDrive drive = new DifferentialDrive(left_motor, right_motor);

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
        // Handle tilt
        if (OI.isTiltUpPushed())
            tilt_motor.set(tilt_speed);
        else if (OI.isTiltDownPushed())
            tilt_motor.set (-tilt_speed);
        else
            tilt_motor.set (0);

        // Drive
        drive.arcadeDrive(OI.getSpeed(), OI.getTurn());
    }
}
