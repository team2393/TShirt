package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/** The 'main' class where Java starts */
public final class Main
{
    public static void main(String... args)
    {
        RobotBase.startRobot(TShirtRobot::new);
    }
}
