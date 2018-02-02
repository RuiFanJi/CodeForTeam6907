package org.usfirst.frc.team6907.robot.subsystems;

import java.awt.Robot;

import org.usfirst.frc.team6907.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	private static final double
			HEIGHT_TOTAL=1.8,
			HEIGHT_ERROR_BOUND=0.005;
			
	private static final double 
			HEIGHT_PER_ROTATION=0.06,
			GEAR=3,
			GEAR_ENCODER=4,
			SIGNALS_PER_ROTATION=1024;
	private static final int
			MAX_PEAK_CURRENT=5,
			MAX_CONTINUOUS_CURRENT=4;
	
	private static Elevator sElevator;
	
	private WPI_TalonSRX mTalonSRX;
	


	private boolean mCmdRunning;
	private double mTargetHeight;
	
	public static Elevator get() {
		if(sElevator==null) sElevator=new Elevator();
		return sElevator;
	}
	
	public Elevator() {
		mTalonSRX=new WPI_TalonSRX(RobotMap.Elevator.MOTOR_CONTROLLER_DEVICE_ID);
		//Current Control
		//mTalonSRX.configPeakCurrentLimit(MAX_PEAK_CURRENT, 30);
		//mTalonSRX.configContinuousCurrentLimit(MAX_CONTINUOUS_CURRENT, 30);
		//mTalonSRX.configPeakCurrentDuration(50, 30);
		mTalonSRX.setSelectedSensorPosition(0, 0, 0);
		mTalonSRX.setInverted(true);
		mTalonSRX.setSafetyEnabled(true);
		mCmdRunning=false;
	}

	public void teleopPeriodic() {

		if(mCmdRunning) {
			double difference=mTargetHeight-getHeight();
			
			if(Math.abs(difference)<HEIGHT_ERROR_BOUND) {
				setStatic();
			}else if(Math.abs(difference)<0.5){
				mTalonSRX.set(ControlMode.PercentOutput,Math.signum(difference)*0.5);
			}else if(Math.abs(difference)<0.1){
				mTalonSRX.set(ControlMode.PercentOutput, Math.signum(difference)*0.2);
			}else if (Math.abs(difference)<HEIGHT_ERROR_BOUND) {
				mCmdRunning=false;
			}
		
		/*}else if(Math.abs(input)>INPUT_DEADLOCK 
				&& !(isTop() && input>0 || isBottom() && input<0)) {
			mTalonSRX.set(ControlMode.PercentOutput,input);
		*/
		}else  
			setStatic();
		SmartDashboard.putNumber("OutCurrent",mTalonSRX.getOutputCurrent());

		SmartDashboard.putNumber("Pos", getHeight());
	}
	public void gotoPos(double height) {
		mTargetHeight=height;
		mCmdRunning=true;
	}
	private boolean isTop() {
		return getHeight()>=HEIGHT_TOTAL-HEIGHT_ERROR_BOUND;
	}
	private boolean isBottom() {
		return getHeight()<=HEIGHT_ERROR_BOUND;
	}
	private void setStatic() {
		mTalonSRX.set(ControlMode.PercentOutput,0.04);
	}
	private double getHeight() {
		return mTalonSRX.getSelectedSensorPosition(0)*HEIGHT_PER_ROTATION/(GEAR*SIGNALS_PER_ROTATION*GEAR_ENCODER);
	}
	private double getMinAbs(double n1,double n2) {
		if(Math.abs(n1)<Math.abs(n2)) {
			return n1;
		}else {
			return n2;
		}
	}
	private double getMaxSpeed() {
		double height=getHeight();
		
		return 0;
	}
}
