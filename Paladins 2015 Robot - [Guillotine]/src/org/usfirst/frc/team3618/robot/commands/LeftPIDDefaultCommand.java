package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LeftPIDDefaultCommand extends Command {

    public LeftPIDDefaultCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftPIDSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// axis upsidedown
    	if(Robot.oi.DrewsXBoxController.getRawAxis(1) < -0.5)
    		Robot.leftPIDSubsystem.jog(Robot.leftPIDSubsystem.upSpeed);
    		//Robot.leftPIDSubsystem.bypassPIDJog(0.5);
    	else if(Robot.oi.DrewsXBoxController.getRawAxis(1) > 0.5)
    		Robot.leftPIDSubsystem.jog(Robot.leftPIDSubsystem.downSpeed);
    		//Robot.leftPIDSubsystem.bypassPIDJog(-0.5);
    	else Robot.leftPIDSubsystem.stop();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
