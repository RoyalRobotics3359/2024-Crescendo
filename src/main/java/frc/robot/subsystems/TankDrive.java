// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankDrive extends SubsystemBase {
  /** Fields */
  private CANSparkMax leftFront;
  private CANSparkMax leftBack;
  private CANSparkMax rightFront;
  private CANSparkMax rightBack;

  private MotorControllerGroup leftMotors, rightMotors;

  private DifferentialDrive drive;

  /** Creates a new TankDrive. */
  public TankDrive() {
    
    if (Constants.TANK_DRIVE_EXISTS) {
      leftFront = new CANSparkMax(Constants.Motors.leftFront.getId(), MotorType.kBrushless);
      leftBack = new CANSparkMax(Constants.Motors.leftBack.getId(), MotorType.kBrushless);
      rightFront = new CANSparkMax(Constants.Motors.rightFront.getId(), MotorType.kBrushless);
      rightBack = new CANSparkMax(Constants.Motors.rightBack.getId(), MotorType.kBrushless);

      leftFront.restoreFactoryDefaults();
      leftBack.restoreFactoryDefaults();
      rightFront.restoreFactoryDefaults();
      rightBack.restoreFactoryDefaults();

      leftBack.follow(leftFront);
      rightBack.follow(rightFront);

      leftFront.setInverted(Constants.Motors.leftFront.getReversed());
      leftBack.setInverted(Constants.Motors.leftBack.getReversed());
      rightFront.setInverted(Constants.Motors.rightFront.getReversed());
      rightBack.setInverted(Constants.Motors.rightBack.getReversed());

      leftFront.setIdleMode(IdleMode.kBrake);
      leftBack.setIdleMode(IdleMode.kBrake);
      rightFront.setIdleMode(IdleMode.kBrake);
      rightBack.setIdleMode(IdleMode.kBrake);

      leftMotors = new MotorControllerGroup(leftFront, leftBack);
      rightMotors = new MotorControllerGroup(rightFront, rightBack);

      drive = new DifferentialDrive(leftMotors, rightMotors); 
    }
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setVoltage(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts * Constants.MAX_VOLTAGE);
    rightMotors.setVoltage(rightVolts * Constants.MAX_VOLTAGE);
    drive.feed();
  }

  public void brake() {
    leftMotors.set(0);
    rightMotors.set(0);
  }
}