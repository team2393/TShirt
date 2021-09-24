package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    DigitalInput top_tube = new DigitalInput(RobotMap.TOPsensor);
    DigitalInput left_tube = new DigitalInput(RobotMap.LEFTsensor);
    DigitalInput right_tube = new DigitalInput(RobotMap.RIGHTsensor);
    Solenoid top = new Solenoid(RobotMap.TOP);

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

        //TUBES
        if (OI.isTopPressed())
            System.out.println("TOP!!");
         top.set(OI.isTopPressed());
        // left_tube.set(OI.isLeftPressed());
        // right_tube.set(OI.isRightPressed());


        SmartDashboard.putBoolean("Top Sensor", top_tube.get());
        SmartDashboard.putBoolean("Right Sensor", right_tube.get());
        SmartDashboard.putBoolean("Left Sensor", left_tube.get());
    }
}
