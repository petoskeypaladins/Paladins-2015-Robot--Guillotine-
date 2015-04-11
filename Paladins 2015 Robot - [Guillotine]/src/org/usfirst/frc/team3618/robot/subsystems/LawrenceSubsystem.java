package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LawrenceSubsystem extends Subsystem {
    
	public Talon lawrenceMotor;

	public static final double MAX_ELBOW_SPEED = 0.78;
	
	public LawrenceSubsystem() {
		lawrenceMotor = new Talon(RobotMap.LAWRENCE_MOTOR);
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

	@Override
	protected void initDefaultCommand() {
		
	}
    
}

