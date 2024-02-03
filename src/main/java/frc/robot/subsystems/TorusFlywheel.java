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


public class TorusFlywheel extends SubsystemBase {
  /** Fields */
  private CANSparkMax flywheel;
  private RelativeEncoder encoder;
  private BangBangController controller;

  /** Creates a new TorusFlywheel. */
  public TorusFlywheel() {
    if (Constants.TORUS_FLYWHEEL_EXISTS) {
      flywheel = new CANSparkMax(Constants.Motors.torusFlywheel.getId(), MotorType.kBrushless);
      flywheel.restoreFactoryDefaults();
      flywheel.setInverted(Constants.Motors.torusFlywheel.getReversed());
      flywheel.setIdleMode(IdleMode.kCoast);

      controller = new BangBangController();
      controller.setTolerance(Constants.BANG_BANG_TOLERANCE);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void power(double power) {
    flywheel.set(power);
  }

  public void setToSetpoint(double setpoint) {
    flywheel.set(controller.calculate(encoder.getVelocity(), setpoint));
  }
}
