package org.usfirst.frc.team3618.robot;

import org.usfirst.frc.team3618.robot.commands.ClampCommand;
import org.usfirst.frc.team3618.robot.commands.LawrenceDeployCommand;
import org.usfirst.frc.team3618.robot.commands.LeftToteArmDownCommand;
import org.usfirst.frc.team3618.robot.commands.LeftToteArmUpCommand;
import org.usfirst.frc.team3618.robot.commands.ResetGyroCommand;
import org.usfirst.frc.team3618.robot.commands.StowLawrenceCommand;
import org.usfirst.frc.team3618.robot.commands.UnclampCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public Joystick stick = new Joystick (1);
	public Joystick DrewsXBoxController = new Joystick (2);

//	public Button pushIn = new JoystickButton(stick2, 2);    Being removed from robot
//	public Button pushOut = new JoystickButton(stick2, 3);   Being removed from robot
	public Button unclamp = new JoystickButton(DrewsXBoxController, 2);
	public Button clamp = new JoystickButton(DrewsXBoxController, 1);
	public Button leftToteArmUp = new JoystickButton(stick, 2);
	public Button leftToteArmDown = new JoystickButton(stick, 1);
	public Button resetGyro = new JoystickButton(stick, 3);
	public Button shoulderUp = new JoystickButton(DrewsXBoxController,5); // used in LawrenceDefaultCommand
	public Button shoulderDown = new JoystickButton(DrewsXBoxController,6); // used in LawrenceDefaultCommand
	public Button deployLawrence = new JoystickButton(DrewsXBoxController,3);
	public Button stowLawrence = new JoystickButton(DrewsXBoxController, 4);
	public Button toggleZAxis = new JoystickButton(stick, 11);
		
	// Drew wants stick2, button 2 to be toggle for tote clamping
	// Drew wants stick2, button 3 to be toggle for tote pushing
	
	
	// manual in/out for Elbow/Shoulder (4 buttons)
	// XBox buttons:            Functions
	//*   1  A                    Clamp
	//*   2  B                    UnClamp
	//*   3  X                    Deploy Lawrence
	//*   4  Y                    Stow Lawrence
	//*   5  LeftBumper           Shoulder Up (spool)
	//*   6  RightBumper          Shoulder Down (unspool)
	//    7  BACK                 
	//    8  Start                
	//    9  LeftStickClick
	//   10  RightStickClick
	//  POV
	//*   0  POV 'up'             Increment Level
	//* 180  POV 'down'           Decrement Level
	// -1      neutral
	// Axes:
	//  0  Left X Axes
	//* 1  Left Y Axes          Left lift jog Up/Down
	//* 2  Left Trigger         Elbow Up (spool)
	//* 3  Right Trigger        Elbow Down (unspool)
	//  4  Right X Axes
	//* 5  Right Y Axes         Right lift jog Up/Down
	
public OI(){
	
//	pushIn.whenPressed(new TotePusherInCommand());
//	pushOut.whenPressed(new TotePusherOutCommand());
	unclamp.whenPressed(new UnclampCommand());
	clamp.whenPressed(new ClampCommand());
	leftToteArmUp.whenPressed(new LeftToteArmUpCommand());
	leftToteArmDown.whenPressed(new LeftToteArmDownCommand());
	resetGyro.whenPressed(new ResetGyroCommand());
	deployLawrence.whenPressed(new LawrenceDeployCommand());
	stowLawrence.whenPressed(new StowLawrenceCommand());
}
	
	
	
}

