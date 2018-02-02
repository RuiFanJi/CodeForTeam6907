package org.usfirst.frc.team6907.robot.subsystems;

import org.usfirst.frc.team6907.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * In FRC 2018, our team uses DifferentialDrive with Spark as 
 * motor controller, so changes shall be made were other teams
 * to utilize this class under other circumstances.
 * 
 * Copyright (c) 2018 Team 6907 (The G.O.A.T.)
 * */
public class Drive{
	public static final int LEFT=0,RIGHT=1;

	private static Drive sDriveInstance;
	
	private DifferentialDrive mDifferentialDrive;
	private Spark mSparkL,mSparkR;
	
	private boolean mCmdRunning;
	
	public static Drive get() {
		if(sDriveInstance==null) sDriveInstance=new Drive();
		return sDriveInstance;
	}
	
	public Drive() {
		mSparkL=new Spark(RobotMap.Drive.MOTOR_LEFT);
		mSparkR=new Spark(RobotMap.Drive.MOTOR_RIGHT);
		mDifferentialDrive=new DifferentialDrive(mSparkL,mSparkR);
		mDifferentialDrive.setSafetyEnabled(true);
		mSparkL.setSafetyEnabled(true);
		mSparkR.setSafetyEnabled(true);
		mCmdRunning=false;
	}
	
	public void drive(double speed,double turn) {
		mDifferentialDrive.arcadeDrive(speed, turn);
	}

	/**
	 * Write Current and Speed to SmartDashboard
	 * */
	public void log() {
		SmartDashboard.putNumber("Drive.Current.L",
				PDP.get().getCurrent(RobotMap.PDP.DRIVE_MOTOR_LEFT));
		SmartDashboard.putNumber("Drive.Current.R",
				PDP.get().getCurrent(RobotMap.PDP.DRIVE_MOTOR_RIGHT));
		SmartDashboard.putNumber("Drive.Speed.L",
				mSparkL.getSpeed());
		SmartDashboard.putNumber("Drive.Speed.R",
				mSparkR.getSpeed());
	}	
}
