package org.usfirst.frc.team3618.robot.commands.autonomous;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonLiftCommand extends Command {

	private boolean goUp;
	
    public AutonLiftCommand(boolean goUp) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftPIDSubsystem);
    	requires(Robot.rightPIDSubsystem);
    	this.goUp = goUp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(goUp) {
    		Robot.leftPIDSubsystem.jog(0.5);
    		Robot.rightPIDSubsystem.jog(0.5);
    	} else {
    		Robot.leftPIDSubsystem.jog(-0.5);
    		Robot.rightPIDSubsystem.jog(-0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.leftPIDSubsystem.hasReset && Robot.rightPIDSubsystem.hasReset);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftPIDSubsystem.jog(0.0);
    	Robot.rightPIDSubsystem.jog(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
