package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LawrenceDeployCommand extends Command {

	private int elbowStopPoint = 86;
	private int shoulderStopPoint = 44; //63;
	private int lastElbow = 0;
	
    public LawrenceDeployCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lawrenceSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.lawrenceSubsystem.shoulderEncoder.get() < 0)
    		Robot.lawrenceSubsystem.shoulderEncoder.reset(); // must have been up when we started
    	if (Robot.lawrenceSubsystem.elbowEncoder.get() < 0)
    		Robot.lawrenceSubsystem.elbowEncoder.reset(); // must have been up when we started
    	// start the motors moving
		Robot.lawrenceSubsystem.jogShoulder(0.67);
		Robot.lawrenceSubsystem.jogElbow(1.0); // unspool
		lastElbow = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	int thisElbow = Robot.lawrenceSubsystem.elbowEncoder.get();
    	if ((thisElbow >= elbowStopPoint) ||
    	    (thisElbow < lastElbow-5 )) {	
    		Robot.lawrenceSubsystem.stopElbow();
    	}
   		lastElbow = Math.max(thisElbow,lastElbow);
    	
    	if (Robot.lawrenceSubsystem.shoulderEncoder.get() >= shoulderStopPoint) {
    		Robot.lawrenceSubsystem.stopShoulder();
    	}
    	else if (Robot.lawrenceSubsystem.shoulderEncoder.get() >= shoulderStopPoint/3) {
    		if (Robot.lawrenceSubsystem.elbowEncoder.get() > elbowStopPoint*0.85)
    			Robot.lawrenceSubsystem.jogShoulder(0.5); // but can be faster towards the end
    		else if (Robot.lawrenceSubsystem.elbowEncoder.get() >= elbowStopPoint*.35)
    			Robot.lawrenceSubsystem.jogShoulder(0.43); // cut speed once we get moving
    		else Robot.lawrenceSubsystem.stopShoulder();
    	}
    	else Robot.lawrenceSubsystem.jogShoulder(0.67);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (timeSinceInitialized() > 11.0) {
    		return true; // should never take more than this to get done
    	}
    	if (Robot.lawrenceSubsystem.elbowEncoder.get() >= elbowStopPoint &&
    		Robot.lawrenceSubsystem.shoulderEncoder.get() >= shoulderStopPoint) {
    		return true; // the tests in execute() actually shut both motors off
    	}
        return false;
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
