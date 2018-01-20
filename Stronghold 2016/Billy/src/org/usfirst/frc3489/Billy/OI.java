// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3489.Billy;

import org.usfirst.frc3489.Billy.commands.cGrab;
import org.usfirst.frc3489.Billy.commands.cHighGC;
import org.usfirst.frc3489.Billy.commands.cIntake;
import org.usfirst.frc3489.Billy.commands.cKick;
import org.usfirst.frc3489.Billy.commands.cLowBar;
import org.usfirst.frc3489.Billy.commands.cLowGshoot;
import org.usfirst.frc3489.Billy.commands.cReverseIntake;
import org.usfirst.frc3489.Billy.commands.cScaleDown;
import org.usfirst.frc3489.Billy.commands.cScalePos;
import org.usfirst.frc3489.Billy.commands.cScaleUp;
import org.usfirst.frc3489.Billy.commands.cSpin;
import org.usfirst.frc3489.Billy.commands.cEndPos;
import org.usfirst.frc3489.Billy.commands.cStopMotor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick rightJoystick;
    public Joystick leftJoystick;
    public JoystickButton bKick;
    public JoystickButton bSpin;
    public JoystickButton bStopMotor;
    public JoystickButton bGrab;
    public JoystickButton bIntake;
    public JoystickButton bReverseIntake;
    public JoystickButton bLowBar;
    public JoystickButton bHighGC;
    public JoystickButton bLowGshoot;
    public JoystickButton bScalePos;
    public JoystickButton bEndPos;
    public JoystickButton bScaleUp;
    public JoystickButton bScaleDown;
    public Joystick maniJoy;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        maniJoy = new Joystick(3);
        
        bHighGC = new JoystickButton(maniJoy, 12);
        bHighGC.whenPressed(new cHighGC());
        bScaleDown = new JoystickButton(maniJoy, 11);
        bScaleDown.whileHeld(new cScaleDown());
        bEndPos = new JoystickButton(maniJoy, 10);
        bEndPos.whenPressed(new cEndPos());
        bLowGshoot = new JoystickButton(maniJoy, 9);
        bLowGshoot.whenPressed(new cLowGshoot());
        bScalePos = new JoystickButton(maniJoy, 8);
        bScalePos.whenPressed(new cScalePos());
        bScaleUp = new JoystickButton(maniJoy, 7);
        bScaleUp.whileHeld(new cScaleUp());
        bReverseIntake = new JoystickButton(maniJoy, 6);
        bReverseIntake.whileHeld(new cReverseIntake());
        bIntake = new JoystickButton(maniJoy, 5);// move to intake position
        bIntake.whenPressed(new cIntake());
        bGrab = new JoystickButton(maniJoy, 4);
        bGrab.whileHeld(new cGrab());
        bStopMotor = new JoystickButton(maniJoy, 3);
        bStopMotor.whenPressed(new cStopMotor());
        bSpin = new JoystickButton(maniJoy, 2);
        bSpin.whileHeld(new cSpin());
        bKick = new JoystickButton(maniJoy, 1);
        bKick.whenPressed(new cKick());
        leftJoystick = new Joystick(2);
        
        rightJoystick = new Joystick(1);
        


        // SmartDashboard Buttons

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getrightJoystick() {
        return rightJoystick;
    }

    public Joystick getleftJoystick() {
        return leftJoystick;
    }

    public Joystick getmaniJoy() {
        return maniJoy;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

