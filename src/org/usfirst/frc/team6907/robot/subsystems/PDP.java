package org.usfirst.frc.team6907.robot.subsystems;

import org.usfirst.frc.team6907.robot.RobotMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class PDP {
	private static PowerDistributionPanel sPDP;
	public static PowerDistributionPanel get() {
		if (sPDP==null) sPDP=new PowerDistributionPanel(RobotMap.PDP.CHANNEL);
		return sPDP;
	}
}
