package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.FireCommand;


/** TShirt Cannon Robot */
public class TShirtRobot extends TimedRobot
{
    //motors
    VictorSP tilt_motor = new VictorSP(RobotMap.PWM_TILT);

    //Define Tubes
    // private final DigitalOutput tube_a = new DigitalOutput(RobotMap.TUBE_A);
    // private CommandBase fire_a = new FireCommand(tube_a);
    // private final DigitalOutput tube_b = new DigitalOutput(RobotMap.TUBE_A);
    // private CommandBase fire_b = new FireCommand(tube_b);
    // private final DigitalOutput tube_c = new DigitalOutput(RobotMap.TUBE_A);
    // private CommandBase fire_c = new FireCommand(tube_c);

    // private CommandBase fire = fire_a;

    /** Run once on startup */
    @Override
    public void robotInit()
    {
        System.out.println("T-shirt robot");
        // Put some commands onto dashboard
        // SmartDashboard.putData("Fire A", fire_a);
        // SmartDashboard.putData("Fire B", fire_b);
        // SmartDashboard.putData("Fire C", fire_c);
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
        if (OI.isTiltUp())
            tilt_motor.set(0.3);
        else if (OI.isTiltDown())
            tilt_motor.set (-0.3);
        else
            tilt_motor.set (0);


        // TODO Is there just one tube solenoid for all tubes?
        // Is there a way to select a tube?
        // Or should presssing 'fire' always fire all tubes in some sequence?
        // if (OI.isFirePressed())
        // {
        //     // Fire the active command
        //     fire.schedule();
        //     // Prepare to cycle to next tube
        //     if (fire == fire_a)
        //         fire = fire_b;
        //     else if (fire == fire_b)
        //         fire = fire_c;
        //     else
        //         fire = fire_a;
        // }
    }
}
