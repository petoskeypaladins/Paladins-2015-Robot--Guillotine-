package org.usfirst.frc.team3618.robot.subsystems;
import org.usfirst.frc.team3618.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ToteArmSubsystem extends Subsystem {
    DoubleSolenoid leftTArm = new DoubleSolenoid(RobotMap.LEFT_TOTE_ARM_IN,RobotMap.LEFT_TOTE_ARM_OUT);

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void leftToteArmUp(){
    	leftTArm.set(DoubleSolenoid.Value.kReverse);
    }
    public void leftToteArmDown(){
    	leftTArm.set(DoubleSolenoid.Value.kForward);
    }
}

