// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
  /**
   * These are the attributes of each motor controller.  Each entry has
   * its CAN ID number and if the motor is reversed.
   */
  public enum Motors {
    leftFront(4, false), // FIX ME
    leftBack(6, false), // FIX ME
    rightFront(9, true), // FIX ME
    rightBack(1, true), // FIX ME
    transferRoller(8, false), // FIX ME
    torusFlywheelLeft(5, false), // FIX ME
    torusFlywheelRight(7, false), // FIX ME
    shooterRearLeft(2, false), // FIX ME
    shooterRearRight(3, false), // FIX ME
    frontIntakeRoller(10, false), // FIX ME
    rearIntakeRoller(11, false); // FIX ME

    /** CAN ID of the motor controller */
    private int id;
    /** Is the motor reversed? */
    private boolean isReversed;

    /**
     * Constructor.  Initializes a new Motors enum entry
     * 
     * @param id - CAN ID of the motor controller
     * @param isReversed - true if the motor runs reversed, false otherwise
     */
    private Motors(int id, boolean isReversed) {
      this.id = id;
      this.isReversed = isReversed;
    }

    /**
     * Return the CAN ID of the Motors enum entry.
     * 
     * @return int - CAN ID
     */
    public int getId() { return id; }
    
    /**
     * Return if the motor runs reversed.
     * 
     * @return - true if the motor runs reversed, false otherwise
     */
    public boolean isReversed() { return isReversed; }
  }

  /**
   * These are the channel numbers assigned to each pneumatic solenoid
   */
  public enum Pneumatics {
    intakeLeftForward(0),
    intakeLeftReverse(1),
    intakeRightForward(2),
    intakeRightReverse(3),
    climberLeftUp(4),
    climberLeftDown(5),
    climberRightUp(6),
    climberRightDown(7);

    /**
     * Constructor.  Initializes a new Pneumatics enum entry
     * 
     * @param id - Pneumatics Control Module channel of the solenoid
     */
    Pneumatics(int id) {
      this.id = id;
    }

    /**
     * Pneumatics Control Module channel number
     */
    private int id;

    /**
     * Getter to return the Pneumatics Control Module channel number
     * @return - Pneumatics Control Module channel number
     */
    public int getId() {
      return id;  
    }
  }



  /** Robot Subsystem Existence */
  public static final boolean MECANUM_DRIVE_EXISTS = true;
  public static final boolean TANK_DRIVE_EXISTS = false;
  public static final boolean TORUS_FLYWHEEL_EXISTS = false;

  public static final boolean DRIVE_MOTION_CONTROL_EXISTS = false;

  public static final double MAX_VOLTAGE = 10;
  public static final double MAX_MEC_SPEED = 1.0;
  public static final double ROLLER_SPEED = 0.25;
  public static final double BANG_BANG_TOLERANCE = 0.5;
  public static final double INTAKE_DEPLOY_TIME = 2.0; 
  // public static final double  JOYSTICK_DEADBAND = 0.6; 
}
