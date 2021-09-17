package frc.robot;

/** Information about all the RoboRIO ports
 *  and what devices are conneced to them
 */
public class RobotMap
{
    // TODO Determine what hardware is connected to the RoboRIO
    
    // Motor to raise/lower the tubes
    public static final int PWM_TILT = 2;

    // Drive motors on PWM outputs
    public static final int PWM_LEFT = 0;
    public static final int PWM_RIGHT = 1;
   
    // TODO Does the tilt motor have an encoder? Or limit switches?

    // Sprinkler valves for the 3 tubes on DIO ports
    public static final int TUBE_A = 0;
    public static final int TUBE_B = 1;
    public static final int TUBE_C = 2;
}
