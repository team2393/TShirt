package frc.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.FireCommand;

/** TShirt Cannon Robot */
public class TShirtRobot extends TimedRobot
{
    private final Solenoid tube_a = new Solenoid(RobotMap.SOL_A);
    private Command fire_a = new FireCommand(tube_a);

    /** Run once on startup */
    @Override
    public void robotInit()
    {
        // Put some commands onto dashboard
        SmartDashboard.putData("Fire A", fire_a);
    }

    /** Called all the time */
    @Override
    public void robotPeriodic()
    {
        // Trigger scheduler so that commands will "work"
        Scheduler.getInstance().run();
    }

    /** Called in teleop mode */
    @Override
    public void teleopPeriodic()
    {
        // TODO Is there just one tube solenoid for all tubes?
        // Is there a way to select a tube?
        // Or should presssing 'fire' always fire all tubes in some sequence?
        if (OI.isFirePressed())
            fire_a.start();
    }
}
