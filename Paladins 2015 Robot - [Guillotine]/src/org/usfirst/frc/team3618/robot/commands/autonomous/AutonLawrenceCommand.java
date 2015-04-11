package org.usfirst.frc.team3618.robot.commands.autonomous;

import org.usfirst.frc.team3618.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutonLawrenceCommand extends Command {

	private int dir;
	
	public AutonLawrenceCommand(int dir) {
		this.dir = dir;
		requires(Robot.lawrenceSubsystem);
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(dir == 1) {
			// Move lawrence up
			Robot.lawrenceSubsystem.jogShoulder(0.5);
		} else if(dir == 0)	{
			// Move lawrence down
			Robot.lawrenceSubsystem.jogShoulder(-0.5);
		}
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
