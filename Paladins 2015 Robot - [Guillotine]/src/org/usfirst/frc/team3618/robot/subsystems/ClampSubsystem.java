package org.usfirst.frc.team3618.robot.subsystems;

import org.usfirst.frc.team3618.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClampSubsystem extends Subsystem {
    
	DoubleSolenoid clamp = new DoubleSolenoid(RobotMap.CLAMP_OPEN,RobotMap.CLAMP_CLOSED);
	
	    // Put methods for controlling this subsystem
    // here. Call these from Commands
	
    public void initDefaultCommand() {
		//double firstTote = toteHeight[1];
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
       
    
    public void clamp() {
    	clamp.set(DoubleSolenoid.Value.kForward);
    }
    
    public void unClamp() {
    	clamp.set(DoubleSolenoid.Value.kReverse);
    }
    
    
    

}

