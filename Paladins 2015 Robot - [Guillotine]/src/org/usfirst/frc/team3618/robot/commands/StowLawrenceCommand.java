package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.LawrenceSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StowLawrenceCommand extends Command {

    public StowLawrenceCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lawrenceSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if((Robot.lawrenceSubsystem.elbowEncoder.get() <= 0) ||
    		(Robot.lawrenceSubsystem.elbowEncoder.get() <= Robot.lawrenceSubsystem.shoulderEncoder.get()/3))
    		Robot.lawrenceSubsystem.stopElbow();
    	else Robot.lawrenceSubsystem.jogElbow(-LawrenceSubsystem.MAX_ELBOW_SPEED);
    	
    	if(Robot.lawrenceSubsystem.shoulderEncoder.get() <= 0)
    		Robot.lawrenceSubsystem.stopShoulder();
    	else Robot.lawrenceSubsystem.jogShoulder(-0.35);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.lawrenceSubsystem.elbowEncoder.get() <= 0 && Robot.lawrenceSubsystem.shoulderEncoder.get() <= 0)
        	return true;
        else return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lawrenceSubsystem.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
