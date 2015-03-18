package org.usfirst.frc.team3618.robot.commands.autonomous;

import org.usfirst.frc.team3618.robot.commands.ClampCommand;
import org.usfirst.frc.team3618.robot.commands.MoveToLevelCommand;
import org.usfirst.frc.team3618.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team3618.robot.commands.UnclampCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand(int selection) {
		addSequential(new ResetGyroCommand());
		
		addParallel(new AutonTimerCommand());
		
    	if(selection == 1) {
    		addSequential(new AutonDriveOrientedCommand(0.75, 0.0, 11.0), 5.0);
    	} else if(selection == 2) {
        	addSequential(new AutonLiftCommand(false), 0.25);
    		addSequential(new ClampCommand());
    		addParallel(new MoveToLevelCommand(1));
    		// THIRD TOTE EVENTUALLY
    		addSequential(new AutonDriveOrientedCommand(0.75, 90.0, 11.0));
    		addSequential(new UnclampCommand());
    		addSequential(new AutonDriveOrientedCommand(0.5, 180, 2.0));
    	} else if(selection == 3) {
    		addParallel(new AutonLiftCommand(false), 0.25);
        	// First TOTE
        	addSequential(new ClampCommand());
    		addParallel(new MoveToLevelCommand(2));
    		addSequential(new AutonDriveOrientedCommand(0.5, 90.0, 3.0));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 3.85));
    		addSequential(new AutonDriveOrientedCommand(0.5, -90.0, 3.7));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 2.0));
    		addSequential(new TestWaitCommand(), 0.125);
    		addSequential(new UnclampCommand());
    		addSequential(new MoveToLevelCommand(0));
    		// Second TOTE
    		addSequential(new ClampCommand());
    		addParallel(new MoveToLevelCommand(2));
    		// Start strafing to the right
    		addSequential(new AutonDriveOrientedCommand(0.65, 90.0, 8.5));
    		addSequential(new UnclampCommand());
    		addSequential(new AutonDriveOrientedCommand(0.75, 180, 0.4));
    	} else if(selection == 4) {
        	addParallel(new AutonLiftCommand(false), 0.25);
        	// First TOTE
        	addSequential(new ClampCommand());
    		addParallel(new MoveToLevelCommand(2));
    		addSequential(new AutonDriveOrientedCommand(0.5, 90.0, 3.0));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 3.85));
    		addSequential(new AutonDriveOrientedCommand(0.5, -90.0, 3.7));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 2.0));
    		addSequential(new TestWaitCommand(), 0.125);
    		addSequential(new UnclampCommand());
    		addSequential(new MoveToLevelCommand(0));
    		// Second TOTE
    		addSequential(new ClampCommand());
    		addParallel(new MoveToLevelCommand(2));
    		addSequential(new AutonDriveOrientedCommand(0.4, 0.0, 6.5));
    		//addSequential(new UnclampCommand());
    		//addSequential(new MoveToLevelCommand(0));
    		
    		// Third tote
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 0.0, 90));
    		addParallel(new ResetGyroCommand());
    		addSequential(new AutonDriveOrientedCommand(0.8, 0.0, 7.9));
    		addSequential(new TestWaitCommand(), 0.25);
    		addSequential(new UnclampCommand());
    		addParallel(new MoveToLevelCommand(3));
    		addSequential(new AutonDriveOrientedCommand(0.75, 180, 0.4));
    	}
    		
    }
}
