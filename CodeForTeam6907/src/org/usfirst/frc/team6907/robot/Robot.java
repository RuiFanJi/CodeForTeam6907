/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6907.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

//To-Do: Figure out the direction of rotation!!!!!!!!!!!!!!!!!! Otherwise this program would fail!!!!!!!!!!!!!!!!!!!!!!!
public class Robot extends IterativeRobot {
	private TalonSRX mTalonSRX=new TalonSRX(0);
	private XboxController mController=new XboxController(0);
	
	private static final double
			HEIGHT_TOTAL=2.0,
			HEIGHT_ERROR_BOUND=0.02;
	private static final double 
			HEIGHT_PER_ROTATION=1.9*Math.PI/100.0,
			GEAR=3,
			SIGNALS_PER_ROTATION=1024;
	private static final int
			MAX_PEAK_CURRENT=5,
			MAX_CONTINUOUS_CURRENT=4;
	private static final double
			INPUT_DEADLOCK=0.02;
	private boolean mCmdRunning=false;
	private double mTargetHeight;
	
	@Override
	public void robotInit() {
		//mTalonSRX.configPeakCurrentLimit(MAX_PEAK_CURRENT, 30);
		//mTalonSRX.configContinuousCurrentLimit(MAX_CONTINUOUS_CURRENT, 30);
		//mTalonSRX.configPeakCurrentDuration(50, 30);
		mTalonSRX.setSelectedSensorPosition(0, 0, 0);
		mTalonSRX.setInverted(true);
		mTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
	}
	@Override
	public void testPeriodic() {
		mTalonSRX.set(ControlMode.PercentOutput, 0.5);
	}
	@Override
	public void teleopInit() {
		
	}
	@Override
	public void teleopPeriodic() {
		mTalonSRX.set(ControlMode.PercentOutput, 0.-1);//mController.getY(GenericHID.Hand.kLeft);
		SmartDashboard.putNumber(   "Current", mTalonSRX.getOutputCurrent());
		SmartDashboard.putNumber(   "V", mTalonSRX.getMotorOutputVoltage());
		SmartDashboard.putNumber( "R", mTalonSRX.getSelectedSensorPosition(0));
	}
	/*	if(mController.getBackButton()) {
			mCmdRunning=false;
		}
		if(mCmdRunning) {
			double difference=mTargetHeight-getHeight();
			i f(Math.abs(difference)<HEIGHT_ERROR_BOUND) {
				setStatic();
			}else if(Math.abs(difference)<0.5){
				mTalonSRX.set(ControlMode.Velocity,Math.signum(difference)*10);
			}else if(Math.abs(difference)<0.1){
				mTalonSRX.set(ControlMode.Velocity, Math.signum(difference)*5);
			}
		}else if(Math.abs(input)>INPUT_DEADLOCK 
				&& !(isTop() && input>0 || isBottom() && input<0)) {
			mTalonSRX.set(ControlMode.PercentOutput,input*0.8);
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
		mTalonSRX.set(ControlMode.PercentOutput,0.03);
	}
	private double getHeight() {
		return mTalonSRX.getSelectedSensorPosition(0)*HEIGHT_PER_ROTATION/(GEAR*SIGNALS_PER_ROTATION);
	}*/}

