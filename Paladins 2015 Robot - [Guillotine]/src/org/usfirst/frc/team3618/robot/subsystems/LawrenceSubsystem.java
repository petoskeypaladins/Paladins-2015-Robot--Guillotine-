package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.LawrenceDefaultCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LawrenceSubsystem extends Subsystem {
    
	public Talon elbowMotor;
	public Talon shoulderMotor;
	public Encoder shoulderEncoder = new Encoder(RobotMap.LAWRENCE_SHOULDER_A,RobotMap.LAWRENCE_SHOULDER_B);
	// shoulderEncoder increments as it extends
	// initial condition (collapsed) should be zero
	// up to ~110 degrees we get a reading of 69

	public Encoder elbowEncoder = new Encoder(RobotMap.LAWRENCE_ELBOW_A,RobotMap.LAWRENCE_ELBOW_B);
	// elbowEncoder returns values from 0 (collapsed) up to 115 ("straight out")
	
	// Lawrence cannot exceed 6' 6" (78 inches) at any time!

	public static final double MAX_ELBOW_SPEED = 0.78;
	
	public LawrenceSubsystem() {
		elbowMotor = new Talon(RobotMap.LAWRENCE_ELBOW_MOTOR);
		shoulderMotor = new Talon(RobotMap.LAWRENCE_SHOULDER_MOTOR);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LawrenceDefaultCommand());
    }
    
    public void jogElbow(double output) {
    	elbowMotor.set(output);
    }
    
    public void jogShoulder(double output) {
    	shoulderMotor.set(output);
    }
    
    public void stopMotors() {
    	elbowMotor.set(0);
    	shoulderMotor.set(0);
    }
    
    public void stopElbow() {
    	elbowMotor.set(0);
    }
    
    public void stopShoulder() {
    	shoulderMotor.set(0);
    }
    
}

