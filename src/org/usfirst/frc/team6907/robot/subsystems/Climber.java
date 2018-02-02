package org.usfirst.frc.team6907.robot.subsystems;

import org.usfirst.frc.team6907.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;

public class Climber {
	private static Climber sClimber;
	
	private Spark mSpark;
	
	public static Climber get() {
		if(sClimber==null)sClimber=new Climber();
		return sClimber;
	}
	
	public Climber() {
		mSpark=new Spark(RobotMap.Climber.CHANNEL);
	}
}
