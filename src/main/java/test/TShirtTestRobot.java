package test;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/** TShirt Cannon Robot */
public class TShirtTestRobot extends TimedRobot
{
    private final DigitalOutput top = new DigitalOutput(1);

    /** Run once on startup */
    @Override
    public void robotInit()
    {
        System.out.println("\n\n\n\n");
        System.out.println("*******************");
        System.out.println("** TShirtTestRobot robot **");
        System.out.println("*******************");
    }

    /** Time [ms] until which 'top' tube should fire */
    private long fire_top_time = 0;

    /** Time [ms] after which we might try to fire again */
    private long remain_off = 0;

    /** Called in teleop mode */
    @Override
    public void teleopPeriodic()
    {
        final long now = System.currentTimeMillis();

        // Is the button pressed, and it's OK to fire again?
        if (RobotController.getUserButton()  &&  now > remain_off)
        {
            // Fire top tube from now until ... ms
            fire_top_time = now + 40;
            // Then remain off until 2 seconds from now
            remain_off = now + 2000;
        }

        top.set(now < fire_top_time);
    }
}
