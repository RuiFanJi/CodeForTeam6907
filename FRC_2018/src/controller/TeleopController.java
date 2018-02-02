package controller;

import org.usfirst.frc.team6907.robot.subsystems.Drive;
import org.usfirst.frc.team6907.robot.subsystems.DriveOI;
import org.usfirst.frc.team6907.robot.subsystems.OperateOI;

public class TeleopController {
	DriveOI mDriveOI;
	Drive mDrive;
	OperateOI mOperateOI;
	
	public TeleopController() {
		mDriveOI=DriveOI.get();
		mDrive=Drive.get();
		mOperateOI=OperateOI.get();
	}
	
	public void run() {
		controlDrive();
		mOperateOI.log();
		mDriveOI.log();
	}
	
	private void controlDrive() {
		mDrive.drive(mDriveOI.getSpeed(), mDriveOI.getTurn());
	}
}
