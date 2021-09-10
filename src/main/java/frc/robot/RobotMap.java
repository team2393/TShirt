package frc.robot;

/** Information about all the RoboRIO ports
 *  and what devices are conneced to them
 */
public class RobotMap
{
    // TODO Determine what hardware is connected to the RoboRIO
    
    // Drive motors on PWM outputs
    public static final int LEFT_MASTER = 2;
    public static final int LEFT_SLAVE = 2;
    public static final int RIGHT_MASTER = 3;
    public static final int RIGHT_SLAVE = 4;

    // Motor to raise/lower the tubes
    public static final int PWM_TILT = 2;

    // Does the tilt motor have an encoder? Or limit switches?

    // Sprinkler valves for the 3 tubes on DIO ports
    public static final int TUBE_A = 0;
    public static final int TUBE_B = 1;
    public static final int TUBE_C = 2;
    
    // TODO Is there a tilt encoder?
    // Are there limit switches?
}
