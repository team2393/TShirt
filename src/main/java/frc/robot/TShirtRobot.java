package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.FireCommand;

/** TShirt Cannon Robot */
public class TShirtRobot extends TimedRobot
{
    //Define Tubes
    private final DigitalOutput tube_a = new DigitalOutput(RobotMap.TUBE_A);
    private final DigitalOutput tube_b = new DigitalOutput(RobotMap.TUBE_A);
    private final DigitalOutput tube_c = new DigitalOutput(RobotMap.TUBE_A);
    private Command[] fire_commands = new Command[]
    {
        new FireCommand(tube_a),
        new FireCommand(tube_b),
        new FireCommand(tube_c)
    };

    private int active_tube = 0;
    
    /** Run once on startup */
    @Override
    public void robotInit()
    {
        // Put some commands onto dashboard
        SmartDashboard.putData("Fire A", fire_commands[0]);
        SmartDashboard.putData("Fire B", fire_commands[1]);
        SmartDashboard.putData("Fire C", fire_commands[2]);
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
        // Is there a way to select a tube?
        // Or should presssing 'fire' always fire all tubes in some sequence?
        if (OI.isFirePressed())
        {
            // Fire the active command
            fire_commands[active_tube].start();
            // Prepare to cycle to next tube
            active_tube = (active_tube + 1) % 3;
        }
    }
}
