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
    leftFront(4, false), 
    leftBack(3, false), 
    rightFront(1, true), 
    rightBack(2, true), 
    transferStationTop(6, false), 
    transferStationLeft(5, false), 
    transferStationRight(9, true), 
    torusFlywheelLeft(8, false), 
    torusFlywheelRight(12, true), 
    IntakeRoller(7, true); 

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
    intakeLeftForward(12),
    intakeLeftReverse(13),
    intakeRightForward(1),
    intakeRightReverse(2),
    climberLeftUp(10),
    climberLeftDown(14),
    climberRightUp(3),
    climberRightDown(4);

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

  public enum LED_COLORS {
    RED(0.61),
    BLUE(0.87),
    GREEN(0.77),
    ORANGE(0.65),
    PURPLE(0.91),
    YELLOW(0.69),
    RAINBOW(-0.99),
    TEAM(0.53); // This is the power level for switching between two teams

    private final double color;

    private LED_COLORS(double clr) {
      color = clr;
    }
    
    public double getColor() {
      return color;
    }
  }



  /** Robot Subsystem Existence */

  public static final boolean MECANUM_DRIVE_EXISTS = true;
  public static final boolean TORUS_FLYWHEEL_EXISTS = true;
  public static final boolean INTAKE_ROLLERS_EXIST = true;
  public static final boolean TRANSFER_STATION_EXIST = true;
  public static final boolean LED_LIGHTS_EXIST = false;

  public static final boolean DRIVE_MOTION_CONTROL_EXISTS = false;

  // Specific constants for operating manipulators on the robot

  public static final double MAX_VOLTAGE = 10;
  public static final double MAX_MEC_SPEED = 1.0;
  public static final double ROLLER_SPEED = 1.0;
  public static final double TRANSFER_STATION_SPEED = 0.5;
  public static final double SHOOT_HIGH_GOAL_SPEED = 1.0;
  public static final double SHOOT_LOW_GOAL_SPEED = 0.4;

  public static final double BANG_BANG_TOLERANCE = 10.0;
  public static final double INTAKE_DEPLOY_TIME = 2.0;

  // Miscilaneous
  
  public static final int PNEUMATIC_HUB_CANID = 13;
  public static final int TRANSFER_STATION_LIMIT_SWITCH_CHANNEL = 0;
  public static final double ROBOT_TRACK_WIDTH_IN_INCES = 22.25;
  public static final double ROBOT_WIDTH_IN_INCHES = 29.0;
  public static final double ROBOT_LENGTH = 30.3125;

  public static final int DRIVE_MOTOR_CURRENT_LIMIT = 40;
  public static final int SHOOTER_MOTOR_CURRENT_LIMIT = 25; 
}
