package org.usfirst.frc.team3618.robot.subsystems;


import org.usfirst.frc.team3618.robot.Robot;
import org.usfirst.frc.team3618.robot.RobotMap;
import org.usfirst.frc.team3618.robot.commands.LeftPIDDefaultCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LeftPIDSubsystem extends PIDSubsystem {

	Talon leftLiftTalon = new Talon(RobotMap.LEFT_LIFT_MOTOR);
	public Encoder leftLiftEncoder = new Encoder(RobotMap.LEFT_LIFT_A,
			 RobotMap.LEFT_LIFT_B);
	//public DigitalInput tLimitSwitch = new DigitalInput(RobotMap.TOP_LEFT_LIMIT);
	public DigitalInput bLimitSwitch = new DigitalInput(RobotMap.BOTTOM_LEFT_LIMIT);
	
	public boolean hasReset;
	public boolean isMyEncoderAwful = false;
	
//	public double upSpeed = 0.385;
//	public double downSpeed = -0.43;
	
	public double upSpeed = 0.97;
	public double downSpeed = -1.0;
	
	private double lastCount = 0;
	private double lastTime = 0.0;
	
    // Initialize your subsystem here
    public LeftPIDSubsystem() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("LeftLiftSubsystem", 0.005, 0.0001, 0.0);
    	
    	setAbsoluteTolerance(0.5*Robot.countsPerInch);
    	setInputRange(0.0, 41.0*Robot.countsPerInch);
    	
    	hasReset = false;
    }
    
  
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LeftPIDDefaultCommand()); 
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return leftLiftEncoder.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	if(hasReset || !getPIDController().isEnable()) {
	    	double MaxOutput = 1.0;
	    	if(output < 0 && bLimitSwitch.get()) {
	    		output = 0;
	    		leftLiftEncoder.reset();
	    		Robot.currentLevel = 0;
	    		hasReset = true;
	    		isMyEncoderAwful = false;
	    	}
	    	if (output > MaxOutput) {
	    		output = MaxOutput;    		
	    	} else if (output < -MaxOutput) {
	    		output = -MaxOutput;
	    	}
	    	leftLiftTalon.set(output);
    	}
    }
    
    public void jog(double output){
    	disable();
    	usePIDOutput(output);
    }
    
    //Testing method that should NOT be used in the code
    public void bypassPIDJog(double output) {
    	leftLiftTalon.set(output);
    }
    
    public void stop(){
    	leftLiftTalon.set(0);
    }
    
    public void levelUp(){
    	if (Robot.currentLevel < 4) {
    		Robot.currentLevel++;   
    	}
    }
    
    public void levelDown(){
    	if (Robot.currentLevel > 0) {
    		Robot.currentLevel--;
    	}
    }
    
    @Override
    public boolean onTarget() {
    	double error = getSetpoint() - leftLiftEncoder.get();
    	
    	if(Math.abs(error) <= 0.4*Robot.countsPerInch) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean isDeadEncoder(double time) {
    	boolean isAuto = SmartDashboard.getBoolean("autonomous");
    	if(!isAuto) {
	    	int curCount = leftLiftEncoder.get();
	    	if(curCount != lastCount) {
	    		lastTime = time;
	    		lastCount = curCount;
	    	}
	    	if ((time - lastTime) >= 1.0)
	    		isMyEncoderAwful = true;
	    	return isMyEncoderAwful;
    	} else {
    		return false;
    	}
    }
    
}
