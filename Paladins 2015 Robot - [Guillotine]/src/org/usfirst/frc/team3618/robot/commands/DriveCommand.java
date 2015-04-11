
package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassisSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = Robot.oi.stick.getX();
    	double y = Robot.oi.stick.getY();
    	double z = Robot.oi.stick.getZ();
    	double limit = 1.0;
    	
    	if(Robot.oi.toggleZAxis.get()) {
    		z = 0.0;
    	}
    	if(Robot.oi.toggleYAxis.get()) {
    		y = 0.0;
    	}
    	if(Robot.oi.toggleXAxis.get()) {
    		x = 0.0;
    	}
    	
    	if(Robot.oi.toggleFirstSpeed.get()) {
    		// 75% power on all axis
    		limit = 0.4;
    	} else if(Robot.oi.toggleSecondSpeed.get()) {
    		// 25% power on all axis
    		limit = 0.25;
    	}
    	
    	Robot.chassisSubsystem.DriveMe(x*limit, y*limit, z*limit);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassisSubsystem.StopMe();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassisSubsystem.StopMe();
    }
}
