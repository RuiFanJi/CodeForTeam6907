package org.usfirst.frc.team6907.robot.subsystems;

import org.usfirst.frc.team6907.robot.RobotMap;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class handles all input given by the operator.
 * In FRC 2018 Team 6907, the Operator handles the Intaker and elevator
 * */

public class OperateOI {
	public static int 
			MODE_INTAKE=0,
			MODE_CLIMB=1;
	
	private static OperateOI sOperateOI;
	
	private XboxController mXboxController;
	
	public static OperateOI get() {
		if (sOperateOI==null) {
			sOperateOI=new OperateOI();
		}
		return sOperateOI;
	}
	
	public OperateOI() {
		mXboxController=new XboxController(RobotMap.OI.OPERATE);
	}
	
	public int getMode() {
		if(mXboxController.getTriggerAxis(GenericHID.Hand.kLeft)>0.1
				&&mXboxController.getTriggerAxis(GenericHID.Hand.kRight)>0.1){
			return MODE_CLIMB;
		}else {
			return MODE_INTAKE;
		}
	}
	
	/**
	 * Write OI status to SmartDashboard
	 * */
	public void log() {
		SmartDashboard.putString("OI.Operate.Mode",
				getMode()==MODE_CLIMB?"Climb":"Intake" );
	}
	
}
