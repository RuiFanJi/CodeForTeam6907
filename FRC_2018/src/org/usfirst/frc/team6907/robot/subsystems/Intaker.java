package org.usfirst.frc.team6907.robot.subsystems;

public class Intaker {
	private static Intaker sIntaker;
	public static Intaker get() {
		if(sIntaker==null) sIntaker=new Intaker();
		return sIntaker;
	}
}
