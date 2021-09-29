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
    private DifferentialDrive drive = new DifferentialDrive(left_motor, right_motor);
    
    // Pressure sensors
    private DigitalInput top_sensor = new DigitalInput(RobotMap.DIO_TOP_SENSOR);
    private DigitalInput left_sensor = new DigitalInput(RobotMap.DIO_LEFT_SENSOR);
    private DigitalInput right_sensor = new DigitalInput(RobotMap.DIO_RIGHT_SENSOR);

    private Solenoid top = new Solenoid(RobotMap.TOP_SOLENOID);
    private Solenoid left = new Solenoid(RobotMap.LEFT_SOLENOID);
    private Solenoid right = new Solenoid(RobotMap.RIGHT_SOLENOID);

    private long fire_top_time = 0;
    private long fire_left_time = 0;
    private long fire_right_time = 0;

    private long remain_off = 0;

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

        // Tubes
        SmartDashboard.putBoolean("Top Sensor", top_sensor.get());
        SmartDashboard.putBoolean("Right Sensor", right_sensor.get());
        SmartDashboard.putBoolean("Left Sensor", left_sensor.get());

        left.set(OI.isLeftPressed());
        right.set(OI.isRightPressed());

        final long now = System.currentTimeMillis();

        // Is the button pressed, and it's OK to fire again?
        if (OI.isTopPressed()  &&  now > remain_off)
        {
            // Fire top tube from now until ... ms
            fire_top_time = now + 500;
            // Then remain off until 2 seconds from now
            remain_off = now + 2000;
        }
    
        if (OI.isLeftPressed()  &&  now > remain_off)
        {
            // Fire top tube from now until ... ms
            fire_left_time = now + 500;
            // Then remain off until 2 seconds from now
            remain_off = now + 2000;
        }
        
        if (OI.isRightPressed()  &&  now > remain_off)
        {
            // Fire top tube from now until ... ms
            fire_right_time = now + 500;
            // Then remain off until 2 seconds from now
            remain_off = now + 2000;
        }
        
        top.set(now < fire_top_time);
        left.set(now < fire_left_time);
        right.set(now < fire_right_time);
    }
}
