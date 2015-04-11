package org.usfirst.frc.team3618.robot.commands.autonomous;

import org.usfirst.frc.team3618.robot.commands.ClampCommand;
import org.usfirst.frc.team3618.robot.commands.LeftToteArmDownCommand;
import org.usfirst.frc.team3618.robot.commands.LeftToteArmUpCommand;
import org.usfirst.frc.team3618.robot.commands.MoveToLevelCommand;
import org.usfirst.frc.team3618.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team3618.robot.commands.UnclampCommand;
import org.usfirst.frc.team3618.robot.subsystems.ToteArmSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand(int selection) {
		addSequential(new ResetGyroCommand());
		
		addParallel(new AutonTimerCommand());
		
    	if(selection == 1) {
    		addSequential(new AutonDriveOrientedCommand(0.75, 0.0, 6.0), 5.0);
    	} else if(selection == 2) {
        	addSequential(new AutonLiftCommand(false), 0.25);
    		addSequential(new ClampCommand());
    		addParallel(new MoveToLevelCommand(1));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 0.0, 90));
    		addParallel(new ResetGyroCommand());
    		addSequential(new AutonDriveOrientedCommand(0.8, 0.0, 7.9));
    		addSequential(new UnclampCommand());
    		addSequential(new AutonDriveOrientedCommand(0.5, 180, 1.0));
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
        	addSequential(new AutonLiftCommand(false), 0.1);
        	// First TOTE
        	addSequential(new ClampCommand());
    		//addParallel(new MoveToLevelCommand(1));
        	addParallel(new MoveToLevelCommand(2));
    		addSequential(new AutonDriveOrientedCommand(0.75, 105.0, 4.4));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 4.5));
    		//addParallel(new MoveToLevelCommand(2));
    		addSequential(new AutonDriveOrientedCommand(0.5, -90.0, 3.3));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 1.75));
    		addSequential(new TestWaitCommand(), 0.125);
    		addSequential(new UnclampCommand());
    		addSequential(new MoveToLevelCommand(0));
    		// Second TOTE
    		addSequential(new ClampCommand());
    		addParallel(new MoveToLevelCommand(2));
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 6.25));
    		// Third TOTE
    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 0.0, 90));
    		addParallel(new ResetGyroCommand());
    		addSequential(new AutonDriveOrientedCommand(0.6, 0.0, 6.0));
    		addSequential(new AutonDriveOrientedCommand(0.35, 0.0, 2.0, false));
    		addSequential(new TestWaitCommand(), 0.125);
    		addSequential(new UnclampCommand());
    		addParallel(new MoveToLevelCommand(3));
    		addSequential(new AutonDriveOrientedCommand(0.75, 180, 0.4));
    	} else if(selection == 5) {
    		// Lawrence autonomous
    		// Turn 90 degrees to the left
    		// Backup for a certain distance
    		// Lower lawrence
    		// Shake the robot/lawrence
    		// Drive forward
    		addSequential(new ResetGyroCommand());
    		addSequential(new AutonDriveOrientedCommand(0.2, 0.0, .25), 1.0);    	
    		addSequential(new TestWaitCommand(), 0.5);
    		addSequential(new LeftToteArmUpCommand());
    		addSequential(new TestWaitCommand(), 2.0);
    		addSequential(new AutonDriveOrientedCommand(0.4, 180.0, 6.5), 5.0);    	
    		addSequential(new LeftToteArmDownCommand());
//    		addSequential(new AutonLawrenceCommand(0), 1.0);
//    		addSequential(new AutonZDriveCommand(), 3.0);
//    		addSequential(new AutonDriveOrientedCommand(0.5, 0.0, 5.0));
    	} else if(selection == 6) {
    		// WE SIT THERE, AND CRY TEARS OF SADNESS
    	
    		addSequential(new ClampCommand());
        	addSequential(new AutonLiftCommand(false), 0.25);
    		addSequential(new MoveToLevelCommand(1));
    		addSequential(new MoveToLevelCommand(2));
    		addSequential(new MoveToLevelCommand(3));
    	}else if(selection == 7){
    		addSequential(new AutonDriveOrientedCommand(0.75, 0.0, 10.0), 5.0);

    	}
    		
    }
}
