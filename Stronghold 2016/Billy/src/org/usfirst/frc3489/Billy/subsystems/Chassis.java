// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3489.Billy.subsystems;

import org.usfirst.frc3489.Billy.RobotMap;
import org.usfirst.frc3489.Billy.commands.cDrive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Chassis extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon mFL1 = RobotMap.chassismFL1;
    private final CANTalon mBR4 = RobotMap.chassismBR4;
    private final CANTalon mBL3 = RobotMap.chassismBL3;
    private final CANTalon mFR2 = RobotMap.chassismFR2;
 

    
    private final RobotDrive robotDrive41 = RobotMap.chassisRobotDrive41;
    private final AnalogGyro analogGyro = RobotMap.chassisAnalogGyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new cDrive());
        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    public void JoystickInputs(Joystick left, Joystick right) {
    	robotDrive41.tankDrive(left, right);
    }
    public void stop() {
    	robotDrive41.drive(0, 0);	
    }	
    public void ADrive(double speed) {
    	robotDrive41.tankDrive(speed, speed);
    	
    }
    
}

