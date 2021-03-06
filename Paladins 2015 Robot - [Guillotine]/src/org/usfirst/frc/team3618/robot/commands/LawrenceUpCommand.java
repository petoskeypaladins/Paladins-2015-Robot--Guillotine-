package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LawrenceUpCommand extends Command {

    public LawrenceUpCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lawrenceSubsystem);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lawrenceSubsystem.jogShoulder(-0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lawrenceSubsystem.isTooVertical();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lawrenceSubsystem.jogShoulder(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lawrenceSubsystem.jogShoulder(0);
    }
    
}
