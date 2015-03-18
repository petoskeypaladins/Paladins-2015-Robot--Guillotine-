package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.subsystems.LawrenceSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LawrenceDefaultCommand extends Command {

    public LawrenceDefaultCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lawrenceSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.shoulderUp.get())
    		Robot.lawrenceSubsystem.jogShoulder(0.67);
    	else if (Robot.oi.shoulderDown.get())
    		Robot.lawrenceSubsystem.jogShoulder(-0.2); // spring assisted
    	else
    		Robot.lawrenceSubsystem.stopShoulder();

    	// let Drew press harder to move faster (beyond the 50% trigger point, anyway)
    	if (Robot.oi.DrewsXBoxController.getRawAxis(2) > 0.5) // Left Trigger
    		Robot.lawrenceSubsystem.jogElbow(LawrenceSubsystem.MAX_ELBOW_SPEED*Robot.oi.DrewsXBoxController.getRawAxis(2)); // spool up
    	else if (Robot.oi.DrewsXBoxController.getRawAxis(3) > 0.5) // Right Trigger
    		Robot.lawrenceSubsystem.jogElbow(-LawrenceSubsystem.MAX_ELBOW_SPEED*Robot.oi.DrewsXBoxController.getRawAxis(3)); // unspool down
    	else 
    		Robot.lawrenceSubsystem.stopElbow();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
