package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LawrenceSubsystem extends Subsystem {
    
	public Talon lawrenceMotor;
	public DigitalInput upperLimit;
	public DigitalInput lowerLimit;
	
	public static final double MAX_ELBOW_SPEED = 0.78;
	
	public LawrenceSubsystem() {
		lawrenceMotor = new Talon(RobotMap.LAWRENCE_MOTOR);
		upperLimit = new DigitalInput(RobotMap.LAWRENCE_UP_LIMIT);
		lowerLimit = new DigitalInput(RobotMap.LAWRENCE_DOWN_LIMIT);
	}
	
    public void jogShoulder(double output) {
    	lawrenceMotor.set(output);
    }
    
    public void stopMotors() {
    	lawrenceMotor.set(0);
    }
    
    public void stopShoulder() {
    	lawrenceMotor.set(0);
    }

    public boolean isTooVertical() {
    	return upperLimit.get();
    }
    
    public boolean isTooHorizontal() {
    	return lowerLimit.get();
    }
    
	@Override
	protected void initDefaultCommand() {
		
	}
    
}

