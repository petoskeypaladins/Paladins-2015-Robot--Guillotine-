package org.usfirst.frc.team3618.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    
	//PWM
	public static int LEFT_FRONT_DRIVE_MOTOR = 0;
	public static int LEFT_REAR_DRIVE_MOTOR = 1;
	public static int LAWRENCE_SHOULDER_MOTOR = 7;
	public static int LEFT_LIFT_MOTOR = 3;
	public static int RIGHT_REAR_DRIVE_MOTOR = 4;
	public static int RIGHT_FRONT_DRIVE_MOTOR = 5;
	public static int RIGHT_LIFT_MOTOR = 6;
	public static int LAWRENCE_ELBOW_MOTOR = 2;
	
		
	//Solenoids
		public static int CLAMP_CLOSED =1;
		public static int CLAMP_OPEN =0;
		//Not sure which is out or in^
		public static int LEFT_TOTE_ARM_OUT = 4;
		public static int LEFT_TOTE_ARM_IN = 5;
		
	//DIO
		
		//Limit Switches
	public static int BOTTOM_LEFT_LIMIT = 5;
	public static int BOTTOM_RIGHT_LIMIT = 4;
		//Encoders
	public static int FRONT_LEFT_WHEEL_A =20;
	public static int FRONT_LEFT_WHEEL_B =19;
	public static int FRONT_RIGHT_WHEEL_A = 13;
	public static int FRONT_RIGHT_WHEEL_B =14;
	public static int REAR_LEFT_WHEEL_A = 18;  
	public static int REAR_LEFT_WHEEL_B = 17;
	//RightRear switched A and B so that the encoder is forward
	public static int REAR_RIGHT_WHEEL_A = 15;
	public static int REAR_RIGHT_WHEEL_B = 16;
	public static int LAWRENCE_SHOULDER_A = 7;
	public static int LAWRENCE_SHOULDER_B = 6;
	public static int LAWRENCE_ELBOW_A = 9;
	public static int LAWRENCE_ELBOW_B = 8;
	public static int LEFT_LIFT_A = 0;
	public static int LEFT_LIFT_B = 1;
	public static int RIGHT_LIFT_A = 3;
	public static int RIGHT_LIFT_B = 2;
	
	
	public static final double DRIVE_WHEEL_DIAMETER_FEET  = 0.5;
    public static final double MOTOR_TO_WHEEL_GEAR_RATIO  = 0.773;
    public static final int ENCODER_PULSES_PER_REVOLUTION = 250;
		
}
