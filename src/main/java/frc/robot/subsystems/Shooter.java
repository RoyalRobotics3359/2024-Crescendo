// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.math.controller.BangBangController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /** Fields */
  private CANSparkMax flywheelLeft;
  private CANSparkMax flywheelRight;
  private RelativeEncoder encoder;
  private BangBangController controller;

  /** Creates a new TorusFlywheel. */
  public Shooter() {
    if (Constants.TORUS_FLYWHEEL_EXISTS) {
      flywheelLeft = new CANSparkMax(Constants.Motors.torusFlywheelLeft.getId(), MotorType.kBrushless);
      flywheelLeft.restoreFactoryDefaults();
      flywheelLeft.setInverted(Constants.Motors.torusFlywheelLeft.isReversed());
      flywheelLeft.setIdleMode(IdleMode.kCoast);
      
      flywheelRight = new CANSparkMax(Constants.Motors.torusFlywheelRight.getId(), MotorType.kBrushless);
      flywheelRight.restoreFactoryDefaults();
      flywheelRight.setInverted(Constants.Motors.torusFlywheelRight.isReversed());
      flywheelRight.setIdleMode(IdleMode.kCoast);

      controller = new BangBangController();
      controller.setTolerance(Constants.BANG_BANG_TOLERANCE);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setPower(double power) {
    flywheelLeft.set(power);
    flywheelRight.set(power);
  }

  public void setVelocity(double setpoint) {
    flywheelLeft.set(controller.calculate(encoder.getVelocity(), setpoint));
    flywheelRight.set(controller.calculate(encoder.getVelocity(), setpoint));
  }
}
