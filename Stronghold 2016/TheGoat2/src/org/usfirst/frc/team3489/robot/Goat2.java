
package org.usfirst.frc.team3489.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * 
 * 03/07/16code that will speed the code up
 */
public class Goat2 extends IterativeRobot {
    Joystick testJoy;
    AnalogInput sPot1; 
    CANTalon mFL1, mBR4, mBL3, mFR2;  //Chassis Motors
    CANTalon mArm5; //Arm motor
    RobotDrive mChassis;
    Joystick maniJoy, rightJoystick, leftJoystick;
    AnalogGyro gyro;
    Talon mKicker9;
    RobotDrive mShooter; //Shooter drive 
    CANTalon mIntake6;
    CANTalon mShooterL7, mShooterR8;
    CANTalon DDhanger;
    DigitalInput bDART  = new DigitalInput(9);  //bottom of dart 
    DigitalInput aDrive = new DigitalInput(10);// changed this from 4 to 10
    DigitalInput tDART = new DigitalInput(8);  //top of dart
    DigitalInput gBall = new DigitalInput(1);
    //Auto
    
     boolean armMoving = false;
	 double armPos = 0;
   // final int sPotVChn = 1; //Analog input 
    int sPot1Ch = 1; //analog input pin
	    //In place of the counter (ExPotMStop) we will be stopping the motor based on
	    //the output of the potentiometer.  
	    //TODO: these are the set points to stop the potentiometer
    double s1a = 0.260; // first stop point under low bar
	double s2a = 0.260; // second stop point Boulder pickup
	double s3a = 0.491; //third stop point low goal shoot
	double s4a = 1.770; //fourth stop point high goal shoot far
	double s5a = 1.770; // fifth stop point high goal shoot close
	double s6a = 2.345; //sixth stop point ready to hang
	double s7a = 2.347; //seventh stop point Human Station
	//02-27-16 12:25pm Alex John Changed the starting position from 1.33 to 1.285
	double s8a = 0; //Stop Motor
	double m5stopRange = 0.05; // range above and below set point to allow
									// for over or under when motor is moving faster
	    								// than we are reading the sting pot 
	double sHardStop = 0.191;
	double myPot = 0.0; //Read display Pot when manually moving arm
	double armSpeed = 0;
	boolean toDART = false;
	boolean boDART = false;
	boolean goBall = false;
	boolean tGoUp = false;
	boolean tGoDown = false;
	//double sPot1V = .005;
	//double jCurrent;    //For Shooter
	    //TODO: Assign button on the testJoy that are easy to get to
    int b7 = 7;  //
    int bIntake = 5;  //Boulder Pickup (Intake)
    int bLowGshoot = 9;  //Low Goal Shoot
    int bMoat = 8;  //puts the arm on the right position to cross moat.
    int bHighGC = 12; //High Goal Shoot Close
    int b11 = 11; //
    int bStartPos = 10; //Starting Position on pit stick
    int bStopMotor = 3;  //Stop Motor
    int bReverseIntake = 6;  //Reverses the intake wheels
    int bSpin = 2; //Spin the wheels out to be ready to shoot
    int bGrab = 4; //Grabbing the ball 
    int bKick = 1; //Shoots the ball with kicker
    int mRob;
    
    double testM;
	double lValue; //Left Joystick Y axis value
	double rValue; //right Joystick Y axis value
	double sPot1V; 
    
    //Assign a value to variables that will change in the program
    //value is assigned to avoid NULL errors
   
	double dTime1; // Drive forward 
    double dTime2; // Cross D 1
    double dTime3; // Turn Right
    double dTime4; // Drive to D 3
    double dTime5; // Turn Left
    double startTime;
    double cTime;
    double speedbDefense1;
    double speedD1;
    double speedD2;
    double speedB1;
    double speedB2;
    int cstage;
    
    
	//Getting the voltage
	
	//sPot1 = new AnalogInput(1);	
	
	
	
	int session;
    Image frame;
    //TODO:this is where you move the circle around
    NIVision.Rect rect = new NIVision.Rect(60, 200, 200, 200);  //WiT
    									//(x-axis, y-axis, height, width)
    public void robotInit() {
        
        SmartDashboard.putNumber("String Pot 1", 0);
        SmartDashboard.putBoolean("Got Ball", false);
        //SmartDashboard.putNumber("testing motor", 0);   
       // Dart = new AnalogInput(2);
        rightJoystick = new Joystick(1);
        leftJoystick = new Joystick(2);
        maniJoy = new Joystick(3);
     
        try{
        	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // the camera name (ex "cam0
        //	") can be found through the roborio web interface
        	session = NIVision.IMAQdxOpenCamera("cam0",  //TODO: Change CAM# to match RoboRio
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        	NIVision.IMAQdxConfigureGrab(session);
        }catch (Exception e) {
        	//System.out.println("Failed Vision");
        }
        
    	//SmartDashboard.putNumber("testing motor", 0); 
    	//SmartDashboard.putNumber("Amps From Motor",0);
    	
    	mFL1 = new CANTalon(22); //front left motor
        mBR4 = new CANTalon(24); // back right motor
        mBL3 = new CANTalon(21);//back left motor
        mFR2 = new CANTalon(23); //front right motor
        mArm5 = new CANTalon(28); //Arm motor
        mKicker9 = new Talon(1);
        mIntake6 = new CANTalon(27);
        mShooterL7 = new CANTalon(25);
        mShooterR8 = new CANTalon(26);
        sPot1 = new AnalogInput(1);
        gyro = new AnalogGyro(0);
        DDhanger = new CANTalon(20);
        mShooter = new RobotDrive(mShooterL7, mShooterR8);
        mFL1.setInverted(true);
    	mFR2.setInverted(true);
        //03-04-16 6:59pm Alex John Rob UNCOMMENT INVERSIONS FOR THE COMPETITION BOT!!!!!
        //02-25-16 2:13pm John Alex Moved mChassis from TeleopInit to RobotInit
        mChassis = new RobotDrive(mBL3,mFL1,mBR4,mFR2);
    }
    public void autonomousInit() {
		   ///02-26-16 @ 11:58pm 
    		///dTime1 = 2.9; // Drive forward 
    	   dTime1 = 2.5; // Drive forward 
	       dTime2 = 8.0; // Cross D 1
	       dTime3 = 9.0; // Turn Right
	       dTime4 = 12.0; // Drive to D 3
	       dTime5 = 15.0; // Turn Left
	       startTime = Timer.getFPGATimestamp();
	       cTime = Timer.getFPGATimestamp();
	      cstage = 1; //current stage
	      //02-25-16 2:04pm John Alex Safety Enabled to True
	      mChassis.setSafetyEnabled(false);
	      mShooter.setSafetyEnabled(false);
	      //SmartDashboard.putString("Drive Stage 1", "pending");
	      //SmartDashboard.putString("Drive Stage 2", "pending");
	      //SmartDashboard.putString("Drive Stage 3", "pending");
	      //SmartDashboard.putString("Drive Stage 4", "pending");
	      //SmartDashboard.putString("Drive Stage 5", "pending");
	      gyro.reset();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() { 	
    	//System.out.println(aDrive.get());
    	if(aDrive.get() == false){
        	cTime = Timer.getFPGATimestamp(); 
       	  if(cstage == 1) {   //Stage 1, drive forward
   	    	  mChassis.tankDrive(1, 1);
   	    	  System.out.println("Drive Stage 1");
   	    	  //SmartDashboard.putString("Drive Stage 1", "doing it");
       	  }
       	  if(cstage==1 && cTime > startTime + dTime1) {
       		  cstage= 2;
       		  //SmartDashboard.putString("Drive Stage 2", "starting it");
       		  System.out.println("Go To Stage 2");
       	  }
       	  
   	      //stage 2, Stop
   	      if(cstage==2){
   	    		mChassis.tankDrive(0, 0);
   	    		//SmartDashboard.putString("Drive Stage 2", "doing it");
   	      }
   	      if(cstage==2 && cTime > startTime + dTime2) {
   	    		  cstage= 3;
   	    		 // System.out.println("Go To Stage 3");
   	    		  //SmartDashboard.putString("Drive Stage 3", "starting it");
   	      }     
    	}else{
    	//System.out.println("Dunn");
    	mChassis.tankDrive(0, 0);
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() {
        //TODO: Safety
    	mRob = 0;
        mShooter.setSafetyEnabled(false);
        mChassis.setSafetyEnabled(false);
    	try {
    		NIVision.IMAQdxStartAcquisition(session);
    	} catch (Exception e) {
    		//System.out.println("Vision Error");
    	}
    }
    public void teleopPeriodic() {
    	//03-09-16,6:23pm
    	//phoenix/john
    	//got rid of the counter that counts number of times per second that code updates
    	//mRob++;//goes up by one
    	//System.out.println(mRob+","+Timer.getFPGATimestamp());
        //Jake
    	rValue = rightJoystick.getY();
        lValue= leftJoystick.getY();
        mChassis.tankDrive(lValue, rValue);
        
        try{
        	sPot1V = sPot1.getVoltage();
        }catch (Exception e) {
        	//System.out.println("Failed Potatoes");
        	sPot1V = -1;  ///Use > 0 in IF 
        }
        try{
		    NIVision.IMAQdxGrab(session, frame, 1);
		    NIVision.imaqDrawShapeOnImage(frame, frame, rect,
		            DrawMode.PAINT_INVERT, ShapeMode.SHAPE_OVAL, 0.0f);
		    CameraServer.getInstance().setImage(frame);
        }catch (Exception e) {
        	//System.out.println("Failed Vision");
        }
        //Shooter 
        //jCurrent = maniJoy.getY();
        //System.out.println("da Y " + jCurrent);  
        if (maniJoy.getRawButton(bReverseIntake)) {
         mIntake6.set(-1);
        }
        else{
        	mIntake6.set(0);
        }
    	if(maniJoy.getRawButton(bGrab)){  
    		if(goBall){
    			//System.out.println("got ball stop motors");
    			SmartDashboard.putBoolean("Got Ball", true);
    			mIntake6.set(0);
        		mShooter.tankDrive(0, 0);	
    		} else{
    			mIntake6.set(1);
        		mShooter.tankDrive(.5, .5);
         		//System.out.println("Intake");
         		if(gBall.get() == false){
         			goBall = true;
         		}
         		//goBall = gBall.get();
         		//02-26-16 1:30pm John Alex Rob
         		//Changed from normally closed to normally open
    		}
     		
     	} else {
     		goBall = false;
     		//mIntake6.set(0);
            //System.out.println("No Intake");
     	}
    	
    	
     	if (maniJoy.getRawButton(bKick)){
     		mKicker9.set(1);
     		SmartDashboard.putBoolean("Got Ball", false);
     		//System.out.println("shoot");
     	}
     	else{
     		mKicker9.set(0);
     	}
     	if(maniJoy.getRawButton(bSpin)) {
     		mShooter.tankDrive(-1, -1);
     		//System.out.println("charging");
     	}
        if(maniJoy.getRawButton(bGrab)==false && maniJoy.getRawButton(bSpin)==false){
     		mShooter.tankDrive(0,0); 
        }


    
    if(maniJoy.getRawButton(b7) == true) {
		armMoving = true;
		armPos = s1a;
		//System.out.println("goto Low Bar");
	}
    if(maniJoy.getRawButton(bIntake) == true) {
		armMoving = true;
		armPos = s2a;
		//System.out.println("goto Intake");
	}
    if(maniJoy.getRawButton(bLowGshoot) == true) {
		armMoving = true;
		armPos = s3a;
		//System.out.println("goto Low Goal Shoot");
	}
    if(maniJoy.getRawButton(b11) == true) {
		armMoving = true;
		armPos = s4a;
		//System.out.println("goto High Goal Far");
	}
    if(maniJoy.getRawButton(bHighGC) == true) {
		armMoving = true;
		armPos = s5a;
		//System.out.println("goto High Goal Close");
	}
    //if(maniJoy.getRawButton(??) == true) {
		//armMoving = true;
		//armPos = s6a;
		//System.out.print("goto Scale");
	//}
    if(maniJoy.getRawButton(bStartPos) == true) {
		armMoving = true;
		armPos = s7a;
		//System.out.print("goto Start Pos");
	}
	if(maniJoy.getRawButton(bStopMotor) == true) {
		armMoving = false;
		armPos = s8a; 
		mArm5.set(0);
		//System.out.println("Stop Motor");
	}
	
	//nathan
	
	//if(maniJoy.getRawButton(bManual) == true) {
		armSpeed =maniJoy.getY();
		
		//System.out.println(armSpeed);
		//System.out.println(bDART.get());
		if(bDART.get() == false && armSpeed > .02) { //boDART will stop moving down if Limit Switch is false
		//	armSpeed = 0;
			boDART = true;
			//System.out.println("Bottom of DART");
		}
		if(tDART.get() == false && armSpeed < -.02 ) { //toDART will stop moving up if Limit Switch is false
		//	armSpeed = 0;
			toDART = true;
			//System.out.println("Top of DART");
			
			 //Negative is extend. Positive is retracting
		} 
		if(armSpeed > .02) {toDART = false;}
		if(armSpeed < -.02) {boDART = false;}
		if(sPot1V > 0){
			if(sPot1V < sHardStop && armSpeed > 0.02){
				armSpeed = 0;
				//System.out.println("Potato Stopped The Arm");
			} 
		}
		if(sPot1V < sHardStop && armSpeed > 0.02){ //Can we remove this???
			armSpeed = 0;
		}
		
		if(boDART == true && armSpeed > 0.02) {
			armSpeed = 0; 
		} 
		if(toDART == true && armSpeed < -0.02){
			armSpeed = 0;
		}
		//if(armSpeed < 0.02 || armSpeed > -0.02) { armSpeed = 0.00;}
		//System.out.println(armSpeed + " " + boDART + " " + toDART);
	
		//armSpeed = aStick.getY();
		//Date 02.25.2016/ Time 12:24. Mr. Rob.
		//Changed speed from .375 to .6
		if(armSpeed < -0.02) { armSpeed = -0.375; } 
		if(armSpeed > 0.02) { armSpeed = 0.375; } 
		//TODO: Poor Man's PID - slow the arm during down motion
		///02-25-16 @ 11:58pm 
		///John & ROb
		///Slow down before bottom out to help in reading the limit switch
		if(sPot1V < 0.2 && armSpeed > 0.02) { //only under .2 resistance and moving down
			armSpeed = sPot1V; 	//Set motor speed to the resistance returned from the pot
		}
		mArm5.set(armSpeed);
		myPot = sPot1.getVoltage();
		SmartDashboard.putDouble("Volts", myPot);
		
		/* Set the motor's output to half power.
	   This takes a number from -1 (100% speed in reverse) to +1 (100% speed
	   going forward) */
	//}
	
	//Molly
		if(armMoving == true) {  //if the arm is moving then read the voltage of 
			//the string potentiometer and move motor forwards of backwards.
			if(sPot1V < armPos + m5stopRange) {
				if(toDART == true) {
					mArm5.set(0);
				} else {
					mArm5.set(-0.375);
				} 
				//System.out.println("Moving UP from " + sPot1V + " to " + armPos );
				tGoUp = true;
			} else if(sPot1V > armPos - m5stopRange) {
				if(boDART == true) {
					mArm5.set(0);
				} else {
					mArm5.set(0.375);
				}
				//System.out.println("Moving DOWN");
				tGoDown = true;
			} else {
				mArm5.set(0);
				//System.out.println("Moving NOT");
				armMoving = false;
			}
			if(tGoUp && tGoDown){
				mArm5.set(0);
				armMoving = false;
				tGoUp = false;
				tGoDown = false;
				//System.out.println("Enough");
			}
		}
    }
    
    
    
    public void testInit() {
    	mFL1.setSafetyEnabled(false);
    	mFR2.setSafetyEnabled(false);
    	mBL3.setSafetyEnabled(false);
    	mBR4.setSafetyEnabled(false);
    	
    	Joystick joystck = new Joystick(0);
    	
    	

    	
    }
    
    public void disabledInit() {
    	///Stop moving parts when disabled
    	mArm5.set(0);
    	armMoving = false;
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	

    	/*
    	testM = SmartDashboard.getNumber("testing motor", 0); 
    	if(testM == 3 ){
    		mBL3.set(joystck.getY());
    		
    		SmartDashboard.putNumber("Amps From Motor",mBL3.getOutputCurrent());
    	}
    	if(testM == 1 ){
    		mFL1.set(joystck.getY()); 
    		SmartDashboard.putNumber("Amps From Motor",mFL1.getOutputCurrent());
    	}
    	if(testM == 2 ){
    		mBR2.set(joystck.getY()); 
    		SmartDashboard.putNumber("Amps From Motor",mBR2.getOutputCurrent());
    	}
    	if(testM == 4 ){
    		mFR4.set(joystck.getY()); 
    		SmartDashboard.putNumber("Amps From Motor",mFR4.getOutputCurrent());
    	}
    	*/
    }
    
}
