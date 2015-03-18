
package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ChassisSubsystem extends Subsystem {
	RobotDrive 	myRobotDrive =new RobotDrive (RobotMap.LEFT_FRONT_DRIVE_MOTOR,
											  RobotMap.LEFT_REAR_DRIVE_MOTOR,
											  RobotMap.RIGHT_FRONT_DRIVE_MOTOR,
											  RobotMap.RIGHT_REAR_DRIVE_MOTOR);
	public Gyro firstGyro = new Gyro(0);
	
	private double Kp = 0.0825;
		
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	 public ChassisSubsystem() {
	    	myRobotDrive.setInvertedMotor(MotorType.kRearRight, true);
	    	myRobotDrive.setInvertedMotor(MotorType.kFrontRight, true);
	    	firstGyro.initGyro();
	    	firstGyro.reset();
	    	
	    	frontRight.reset();
	    	backRight.reset();
	    	frontLeft.reset();
	    	backLeft.reset();
	}
	public Encoder frontRight = new Encoder(RobotMap.FRONT_RIGHT_WHEEL_A,RobotMap.FRONT_RIGHT_WHEEL_B, false,Encoder.EncodingType.k4X);  
	public Encoder backRight = new Encoder(RobotMap.REAR_RIGHT_WHEEL_A,RobotMap.REAR_RIGHT_WHEEL_B, false,Encoder.EncodingType.k4X);
	public Encoder frontLeft = new Encoder(RobotMap.FRONT_LEFT_WHEEL_A,RobotMap.FRONT_LEFT_WHEEL_B, false,Encoder.EncodingType.k4X);
	public Encoder backLeft = new Encoder(RobotMap.REAR_LEFT_WHEEL_A,RobotMap.REAR_LEFT_WHEEL_B, false,Encoder.EncodingType.k4X); 
	
	 public void resetEncoders() {
		 frontRight.reset();
		 backRight.reset();
		 frontLeft.reset();
		 backLeft.reset();
	 }
	 
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveCommand());
    }
    
    public void DriveMe (Joystick stick) { 
    	double max = 0.75;
    	double x = stick.getX();
    	double y = stick.getY();
    	double z = stick.getZ()*max; // always 1/2 again
    	myRobotDrive.mecanumDrive_Cartesian(x,y,z,0); 	   	
    }
    public void DriveMe (Joystick stick, double limit) { 
    	double max = 0.75;
    	double x = stick.getX();
    	double y = stick.getY();
    	double z = stick.getZ()*max*limit; // always 1/2 again
    	
    	myRobotDrive.mecanumDrive_Cartesian(x,y,z,0); 	
    }
    
    public void AutoDrive(double speed, double direction, double angle) {
    	myRobotDrive.mecanumDrive_Polar(speed, direction, -firstGyro.getAngle()*Kp);
    }
    
    public void StopMe () {
    	myRobotDrive.drive(0, 0);
    }
    
    public double getEncoders(){
    	double avg = (Math.abs(frontRight.get())+Math.abs(frontLeft.get())+Math.abs(backRight.get())+Math.abs(backLeft.get()))/4;
    	int divisor = 4;
    	if (Math.abs(frontRight.get()) / avg < 0.2)
    		divisor--;
    	if (Math.abs(frontLeft.get()) / avg < 0.2)
    		divisor--;
    	if (Math.abs(backRight.get()) / avg < 0.2)
    		divisor--;
    	if (Math.abs(backLeft.get()) / avg < 0.2)
    		divisor--;
    	if (divisor > 0)
    		return (avg*4)/divisor;
    	else
    		return 0;
    }

    public double getTicksFromFeet(double distance){
		return (RobotMap.ENCODER_PULSES_PER_REVOLUTION/RobotMap.MOTOR_TO_WHEEL_GEAR_RATIO)/(Math.PI*RobotMap.DRIVE_WHEEL_DIAMETER_FEET) * distance;
    	
    }
    public double getFeetFromTicks(double distance){
    	return ((Math.PI*RobotMap.DRIVE_WHEEL_DIAMETER_FEET)/RobotMap.ENCODER_PULSES_PER_REVOLUTION)*distance*RobotMap.MOTOR_TO_WHEEL_GEAR_RATIO;
    	
    }
    
    public void TestDriveMeSideways(){
    	myRobotDrive.mecanumDrive_Cartesian(.2,0,0,0);
    }
    
    public double accel(double speed, double time, double ramp) {
    	double curSpeed = (speed*time)/ramp;
    	if(curSpeed >= speed) {
    		curSpeed = speed;
    	}
		return curSpeed;   
    }
 
    public void rotate(double angle, double power) {
    	myRobotDrive.mecanumDrive_Polar(0, 0, power);
    }
    
}


