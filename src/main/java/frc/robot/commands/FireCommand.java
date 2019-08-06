package frc.robot.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.TimedCommand;

/** Command that 'fires' T-Shirt */
public class FireCommand extends TimedCommand
{
    private final Solenoid sol;

    public FireCommand(final Solenoid sol)
    {
        // Run TimedCommand for 2 seconds
        super(2.0);
        // Remember which solenoid to use
        this.sol = sol;
    }

    @Override
    protected void execute()
    {
        sol.set(true);
    }

    @Override
    protected void end()
    {
        sol.set(false);
    }
}
