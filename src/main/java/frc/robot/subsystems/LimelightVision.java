// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * Source for code taken from:
 * https://docs.limelightvision.io/docs/docs-limelight/getting-started/programming
 */

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightVision extends SubsystemBase {

  // Fields 
  NetworkTable table;

  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;

  double x;
  double y;
  double area;

  /** Creates a new LimelightVision. */
  public LimelightVision() {
    // Creates a new network table object to grab information from limelight
    table = NetworkTableInstance.getDefault().getTable("limelight");

    // Assigns variables to values of displacement from target
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");

    // Checks the values periodically
    x  = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putNumber("LimelightX", getXData());
    SmartDashboard.putNumber("LimelightY", getYData());
    SmartDashboard.putNumber("LimelightArea", getAreaData());

  }

  // Getters to return the values used in targeteing robot
  // in relation to apriltag
  public double getXData() { return x; }
  public double getYData() { return y; }
  public double getAreaData() { return area; }
  
}
