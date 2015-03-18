package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftToteArmUpCommand extends Command {

    public LeftToteArmUpCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.toteArmSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.toteArmSubsystem.leftToteArmUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
