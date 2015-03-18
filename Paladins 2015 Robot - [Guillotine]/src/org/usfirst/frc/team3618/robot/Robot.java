
package org.usfirst.frc.team3618.robot;

import org.usfirst.frc.team3618.robot.commands.MoveToLevelCommand;
import org.usfirst.frc.team3618.robot.commands.TestCommand;
import org.usfirst.frc.team3618.robot.commands.autonomous.AutonomousCommand;
import org.usfirst.frc.team3618.robot.subsystems.ChassisSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.LawrenceSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.LeftPIDSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.ClampSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.RightPIDSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.ToteArmSubsystem;
import org.usfirst.frc.team3618.robot.subsystems.VisionSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ChassisSubsystem chassisSubsystem = new ChassisSubsystem();
	public static final ClampSubsystem liftSubsystem = new ClampSubsystem();
	public static final VisionSubsystem visionSubsystem = new VisionSubsystem();
	public static final ToteArmSubsystem toteArmSubsystem = new ToteArmSubsystem();
	public static final RightPIDSubsystem rightPIDSubsystem = new RightPIDSubsystem();
	public static final LeftPIDSubsystem leftPIDSubsystem = new LeftPIDSubsystem();
	public static final LawrenceSubsystem lawrenceSubsystem = new LawrenceSubsystem();
	public static OI oi;
	
	
	public static int currentLevel = 0;
	public static double countsPerInch = 102.7492;
	

	// Power Distribution Panel instance for seeing current
	private PowerDistributionPanel pdp;
	private int lastPress = -1; // no press active
	
	private SendableChooser autoChooser;
	private Command autonomousCommand;
	private Command testCommand;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
   	
	public void robotInit() {
		// Creating instances of objects
    	oi = new OI();
    	pdp = new PowerDistributionPanel(); 
    	
    	
    	
    	autoChooser = new SendableChooser();
    	autoChooser.addDefault("Drive Forward Only", 1);
    	autoChooser.addObject("1 Tote and score", 2);
    	autoChooser.addObject("2 Totes and score", 3);
    	autoChooser.addObject("3 Totes and score", 4);
    	autoChooser.addObject("Lawrence", 5);
    	SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
    }
	
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	autonomousCommand = new AutonomousCommand((int) autoChooser.getSelected());
    	autonomousCommand.start();
    	SmartDashboard.putBoolean("autonomous", true);
     
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Left Rear Encoder", Robot.chassisSubsystem.backLeft.get());
        SmartDashboard.putNumber("Right Rear Encoder", Robot.chassisSubsystem.backRight.get());
        SmartDashboard.putNumber("Left Front Encoder", Robot.chassisSubsystem.frontLeft.get());
        SmartDashboard.putNumber("Right Front Encoder", Robot.chassisSubsystem.frontRight.get());
        SmartDashboard.putBoolean("Awful Left Encoder?", Robot.leftPIDSubsystem.isMyEncoderAwful);
        SmartDashboard.putBoolean("Awful Right Encoder?", Robot.rightPIDSubsystem.isMyEncoderAwful);
        SmartDashboard.putNumber("Gyro Angle", Robot.chassisSubsystem.firstGyro.getAngle());
        SmartDashboard.putNumber("PID Current Level", currentLevel);
        SmartDashboard.putNumber("PID Setpoint", leftPIDSubsystem.getSetpoint());
		SmartDashboard.putNumber("Drive Encoder Distance (ft)", Robot.chassisSubsystem.getFeetFromTicks(Robot.chassisSubsystem.getEncoders()));

    }

    public void teleopInit() {
//		Compressor c = new Compressor();
//		
//		c.stop();
    	SmartDashboard.putBoolean("autonomous", false);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	SmartDashboard.putBoolean("autonomous", false);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        // PDP Values are being sent to SmartDashboard constantly
        SmartDashboard.putNumber("PDP Total Current", pdp.getTotalCurrent());
        SmartDashboard.putNumber("Motor 2 Current (Elbow)", pdp.getCurrent(0));
        SmartDashboard.putNumber("Motor 3 Current (Left Lift)", pdp.getCurrent(1));
        SmartDashboard.putNumber("Motor 0 Current (LF Drive)", pdp.getCurrent(2));
        SmartDashboard.putNumber("Motor 1 Current (LR Drive)", pdp.getCurrent(3));
        SmartDashboard.putNumber("Motor 5 Current (RF Drive)", pdp.getCurrent(12));
        SmartDashboard.putNumber("Motor 4 Current (RR Drive)", pdp.getCurrent(13));
        SmartDashboard.putNumber("Motor 7 Current (Shoulder)", pdp.getCurrent(14));
        SmartDashboard.putNumber("Motor 6 Current (Right Lift)", pdp.getCurrent(15));
        
        SmartDashboard.putNumber("PDP Voltage", pdp.getVoltage());  
        SmartDashboard.putBoolean(
        		"BottomLeftLimit", Robot.leftPIDSubsystem.bLimitSwitch.get());
        SmartDashboard.putBoolean(
        		"BottomRightLimit", Robot.rightPIDSubsystem.bLimitSwitch.get());
        SmartDashboard.putNumber(
        		"LeftEncoder", Robot.leftPIDSubsystem.leftLiftEncoder.get());
        SmartDashboard.putNumber(
        		"RightEncoder", Robot.rightPIDSubsystem.rightLiftEncoder.get());
        
        SmartDashboard.putNumber("PID Current Level", currentLevel);
        SmartDashboard.putNumber("PID Setpoint", leftPIDSubsystem.getSetpoint());
        //Periodic methods are called once every 20ms (50Hz) and that equates to 50 cycles per 1 second
        
        SmartDashboard.putData("Left Lift", leftPIDSubsystem.getPIDController());
        SmartDashboard.putData("Right  Lift", rightPIDSubsystem.getPIDController());
        
        SmartDashboard.putNumber("ShoulderEncoder", Robot.lawrenceSubsystem.shoulderEncoder.get());
        SmartDashboard.putNumber("Elbow Encoder", Robot.lawrenceSubsystem.elbowEncoder.get());
        
        SmartDashboard.putBoolean("Awful Left Encoder?", Robot.leftPIDSubsystem.isMyEncoderAwful);
        SmartDashboard.putBoolean("Awful Right Encoder?", Robot.rightPIDSubsystem.isMyEncoderAwful);
        SmartDashboard.putNumber("Gyro Angle", Robot.chassisSubsystem.firstGyro.getAngle());
        
        
       SmartDashboard.putNumber("Left Rear Encoder", Robot.chassisSubsystem.backLeft.get());
       SmartDashboard.putNumber("Right Rear Encoder", Robot.chassisSubsystem.backRight.get());
       SmartDashboard.putNumber("Left Front Encoder", Robot.chassisSubsystem.frontLeft.get());
       SmartDashboard.putNumber("Right Front Encoder", Robot.chassisSubsystem.frontRight.get());
       
        
        int thisPress = oi.DrewsXBoxController.getPOV();
        SmartDashboard.putNumber("This press", thisPress);
        if(thisPress != lastPress) {
        	Command move;
        	if (thisPress == 0) {
        		move = new MoveToLevelCommand(true);
        		move.start();
        	}else if(thisPress == 180){
	        	move = new MoveToLevelCommand(false);
	        	move.start();
        	}
            lastPress = thisPress;
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        testCommand = new TestCommand();
    	testCommand.start();
    	 SmartDashboard.putNumber("Left Rear Encoder", Robot.chassisSubsystem.backLeft.get());
         SmartDashboard.putNumber("Right Rear Encoder", Robot.chassisSubsystem.backRight.get());
         SmartDashboard.putNumber("Left Front Encoder", Robot.chassisSubsystem.frontLeft.get());
         SmartDashboard.putNumber("Right Front Encoder", Robot.chassisSubsystem.frontRight.get());
         SmartDashboard.putNumber("LeftEncoder", Robot.leftPIDSubsystem.leftLiftEncoder.get());
         SmartDashboard.putNumber("RightEncoder", Robot.rightPIDSubsystem.rightLiftEncoder.get());
    	             
    }
}
