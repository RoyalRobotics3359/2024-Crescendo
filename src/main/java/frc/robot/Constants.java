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
  
  public enum Motors {
    leftFront(4, true), // FIX ME
    leftBack(6, true), // FIX ME
    rightFront(3, false), // FIX ME
    rightBack(1, false), // FIX ME
    torusFlywheel(5, false); // FIX ME

    private int id;
    private boolean isReversed;

    private Motors(int id, boolean isReversed) {
      this.id = id;
      this.isReversed = isReversed;
    }

    public int getId() { return id; }
    
    public boolean getReversed() { return isReversed; }
  }

  /** Robot Subsystem Existence */
  public static final boolean MECANUM_DRIVE_EXISTS = true;
  public static final boolean TANK_DRIVE_EXISTS = false;
  public static final boolean TORUS_FLYWHEEL_EXISTS = false;

  public static final boolean DRIVE_MOTION_CONTROL_EXISTS = false;

  public static final double MAX_VOLTAGE = 10;
  public static final double MAX_MEC_SPEED = 1.0;
  public static final double BANG_BANG_TOLERANCE = 0.5;
  public static final double  JOYSTICK_DEADBAND = 0.1; 
}
