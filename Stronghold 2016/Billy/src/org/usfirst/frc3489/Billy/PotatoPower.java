package org.usfirst.frc3489.Billy;

public class PotatoPower {
	public static double pos5 = 0.280; //Low Bar and Boulder Pickup
	public static double pos9 = .637; //Low Goal Shoot 
	public static double pos10 = 1.913; //End of Match
	public static double pos12 = 1.6; //High Goal Shoot
	public static double pos7 = 2.415; //Scale and Human Station
	public static double pos3 = 0; //Stop Motor
	public static double posAuto = 1.232; //Autonomous
	public static double m5stopRange = 0.01; //Range above and below points when string pot is reading
	public static double sHardStop = 0.281;  //Hard Stop
	public static double kickerTime = 0.5; //Controls the kick time for the kicker in the cKick command
	public static boolean goBall; //Variable for kicker
	//public static boolean inAuto; 
	
	
	public PotatoPower() {
		//pos5 = .975; //Low Bar and Boulder Pickup
		//pos9 = 1.484; //Low Goal Shoot
		//pos12 = 1.770; //High Goal Shoot
		//pos7 = 2.347; //Scale and Human Station
		//pos3 = 0; //Stop Motor
		//m5stopRange = 0.001; // range above and below set point to allow
		// for over or under when motor is moving faster
			// than we are reading the sting pot
		goBall= false;
		//inAuto = false;
	}
}