package frc.robot;

/** Information about all the RoboRIO ports
 *  and what devices are conneced to them
 */
public class RobotMap
{    
    // Motor to raise/lower the tubes
    // There is no limit switch,
    // operator needs to check when fully up/down
    public static final int PWM_TILT = 2;

    // Drive motors on PWM outputs
    // The two 'left' motors are both on one port,
    // as are the two 'right' motors
    public static final int PWM_LEFT = 0;
    public static final int PWM_RIGHT = 1;
   
    // Pressure sensors for the 3 tubes on DIO ports
    // Indicates if there is sufficient pressure in a tube
    public static final int DIO_TOP_SENSOR = 1;
    public static final int DIO_LEFT_SENSOR = 2;
    public static final int DIO_RIGHT_SENSOR = 3;

    // Signal to Arduino which controls the LEDs,
    // allows switching between two LED patterns
    public static final int DIO_LED_MODE = 6;

    // Pneumatic Control Module channels for the 3 tubes
    // 12V from PCM goes to relay,
    // output of relay provides 20..24V to sprinkler valve
    public static final int TOP_SOLENOID = 0;
    public static final int LEFT_SOLENOID = 1;
    public static final int RIGHT_SOLENOID = 2;
}
