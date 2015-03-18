package org.usfirst.frc.team3618.robot.commands;

import org.usfirst.frc.team3618.robot.commands.autonomous.TestWaitCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestCommand extends CommandGroup {
    
    public  TestCommand() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	 //addSequential(new TestDriveCommand());
    	 addSequential(new TestWaitCommand());
    	 
         //addSequential(new TestTurnCommand());
         addSequential(new SelfTestWaitCommand());
         
         addSequential(new TestSideDriveCommand());
         addSequential(new SelfTestWaitCommand());         
         
         addSequential(new TestClampCommand());
         addSequential(new SelfTestWaitCommand());
         
         addSequential(new TestUnclampCommand());
         addSequential(new SelfTestWaitCommand());
         
         addSequential(new TestToteArmCommand());
         addSequential(new SelfTestWaitCommand());
         
         addSequential(new TestLiftCommand());
         addSequential(new SelfTestWaitCommand());
         
         addSequential(new TestLiftDownCommand());
         end();  
    }
}
