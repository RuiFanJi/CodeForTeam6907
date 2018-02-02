package org.usfirst.frc.team6907.robot.subsystems;

import org.usfirst.frc.team6907.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;

public class DriveOI {
	private static final double 
			DEADBAND=0.02, //in [0,1)
			SLOW_GEAR=0.2,
			TURN_SENSITIVITY=0.3;
	
	private static DriveOI sDriveOI;
	
	private XboxController mController;
	
	/**
	 * @return The singular instance of this class,
	 * create one if no instance exists
	 * */
	public static DriveOI get() {
		if (sDriveOI==null) {
			sDriveOI=new DriveOI();
		}
		return sDriveOI;
	}
	
	public DriveOI() {
		mController=new XboxController(RobotMap.OI.DRIVE);
	}
	
	public double getSpeed() {
		double speed=eliminateDeadband(mController.getY(Hand.kLeft));
		return speed*(isSlowMode()?SLOW_GEAR:1.0);
	}
	
	public double getTurn() {
		double turn=eliminateDeadband(mController.getX(Hand.kRight));
		return turn*TURN_SENSITIVITY;
	}
	
	/**
	 * If Button A is Pressed then turn 180 degrees
	 * This command cannot be activated if Back Button is Pressed
	 * */
	public boolean getTurnBack() {
		return !getAbortCmd() && mController.getAButton();
	}
	
	public boolean getAbortCmd() {
		return mController.getBackButton();
	}
	
	/**
	 * The slow mode is activated iff either bumper is pressed
	 * */
	private boolean isSlowMode() {
		return mController.getBumper(Hand.kLeft)
				||mController.getBumper(Hand.kRight);
	}
	
	/**
	 * This function eliminates the deadband by setting input to 0 
	 * if smaller than deadband; if the input is bigger than the
	 * deadband, then scale it so that the output can reach all values
	 * in [-1.0,1.0]
	 * */
	private double eliminateDeadband(double i) {
		if (Math.abs(i)<DEADBAND) {
			return 0;
		}else {
			return (i-DEADBAND*Math.signum(i))/(1-DEADBAND);
		}
	}
	
	/**
	 * Write OI status to SmartDashboard
	 * */
	public void log() {
		SmartDashboard.putNumber("OI.Drive.Y.L.Raw", mController.getY(Hand.kLeft));
		SmartDashboard.putNumber("OI.Drive.X.R.Raw", mController.getX(Hand.kRight));
		SmartDashboard.putBoolean("OI.Drive.Slow",isSlowMode());
		SmartDashboard.putBoolean("OI.Drive.TurnBack",getTurnBack());
		SmartDashboard.putBoolean("OI.Drive.AbortCmd",getAbortCmd());
		SmartDashboard.putNumber("OI.Drive.Speed",getSpeed());
		SmartDashboard.putNumber("OI.Drive.Turn",getTurn());
	}
}
