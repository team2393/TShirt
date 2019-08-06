package frc.robot;

/** Information about all the RoboRIO ports
 *  and what devices are conneced to them
 */
public class RobotMap
{
    // TODO What _is_ connected to the RoboRIO?
    
    // Assuming there are PMW drive motors
    public static final int PWM_LEFT = 1;
    public static final int PWM_RIGHT = 2;

    // Motor to raise/lower the tubes
    public static final int PMW_TILT = 3;

    // Does the tilt motor have an encoder? Or limit switches?

    // Solenoids for the 3 tubes
    public static final int SOL_A = 0;
    public static final int SOL_B = 1;
    public static final int SOL_C = 2;

    // Compressor on/off? Pressure switch?
    // LED on/off?
}
