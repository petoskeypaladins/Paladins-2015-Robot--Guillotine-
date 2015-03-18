package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightPIDDefaultCommand extends Command {

    public RightPIDDefaultCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rightPIDSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// axis upsidedown
    	if(Robot.oi.DrewsXBoxController.getRawAxis(5) < -0.5)
    		Robot.rightPIDSubsystem.jog(Robot.rightPIDSubsystem.upSpeed);
    		//Robot.rightPIDSubsystem.bypassPIDJog(-0.5);
    	else if(Robot.oi.DrewsXBoxController.getRawAxis(5) > 0.5)
    		Robot.rightPIDSubsystem.jog(Robot.rightPIDSubsystem.downSpeed);
    		//Robot.rightPIDSubsystem.bypassPIDJog(0.5);
    	else Robot.rightPIDSubsystem.stop();
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
