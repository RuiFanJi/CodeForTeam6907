/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6907.robot;

import com.kauailabs.navx.frc.AHRS;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * Since all mapping belong to corresponding modules, we put all mapping within 
 * the same module into the same subclass.
 */
public class RobotMap {
	
	//Power Distribution Panel
	public static class PDP{
		public static final int CHANNEL=0;
		public static final int
				DRIVE_MOTOR_LEFT=0,
				DRIVE_MOTOR_RIGHT=1;
	}
	
	//Operator Input
	public static class OI{
		public static final int
				DRIVE=0,
				OPERATE=1;
	}
	
	public static class Drive{
		public static final int 
				MOTOR_LEFT=0,
				MOTOR_RIGHT=1;
		
	}
	
	public static class Climber{
		public static final int CHANNEL=3;
	}
	
	public static class Elevator{
		public static final int 
				ENCODER=0,
				MOTOR_CONTROLLER_DEVICE_ID=0;
	}
	
	public static class Intaker{

	}
}
