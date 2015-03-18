package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestLiftCommand extends Command {

    public TestLiftCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rightPIDSubsystem);
    	requires(Robot.leftPIDSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.leftPIDSubsystem.bypassPIDJog(.25*Robot.leftPIDSubsystem.upSpeed);
    	Robot.rightPIDSubsystem.bypassPIDJog(.25*Robot.rightPIDSubsystem.upSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(timeSinceInitialized() >= .5)
    		return true;
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rightPIDSubsystem.stop();
    	Robot.leftPIDSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
