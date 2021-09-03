package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/** Command that 'fires' T-Shirt */
public class FireCommand extends CommandBase
{
    protected Timer m_timer = new Timer();

    private final DigitalOutput sol;

    public FireCommand(final DigitalOutput sol)
    {
        // Remember which output to use
        this.sol = sol;
    }

    @Override
    public void initialize()
    {
        m_timer.reset();
        m_timer.start();
        // Fire!!
        sol.set(true);
    }
  
    @Override
    public boolean isFinished()
    {
        // Keep solenoid open for 1 second
        return m_timer.hasElapsed(1);
    }

    @Override
    public void end(boolean interrupted)
    {
        // Assume we fired, stop wasting air
        sol.set(false);
    }
}
