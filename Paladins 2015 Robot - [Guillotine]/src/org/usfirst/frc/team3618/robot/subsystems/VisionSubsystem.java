package org.usfirst.frc.team3618.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private CameraServer camera;
	private Image frame;
	
	public VisionSubsystem() {
		camera = CameraServer.getInstance();
		
	}
	
	public void init() {
		camera.setQuality(75);
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		camera.setImage(frame);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

