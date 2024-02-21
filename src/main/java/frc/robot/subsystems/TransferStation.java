// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TransferStation extends SubsystemBase {

  private CANSparkMax left;
  private CANSparkMax right;

  /** Creates a new TransferStation. */
  public TransferStation() {
    if (Constants.TRANSFER_STATION_EXIST) {
      left = new CANSparkMax(Constants.Motors.transferStationLeft.getId(), MotorType.kBrushless);
      right = new CANSparkMax(Constants.Motors.transferStationRight.getId(), MotorType.kBrushless);

      left.restoreFactoryDefaults();
      right.restoreFactoryDefaults();

      left.setInverted(Constants.Motors.transferStationLeft.isReversed());
      right.setInverted(Constants.Motors.transferStationRight.isReversed());

      left.setIdleMode(IdleMode.kBrake);
      right.setIdleMode(IdleMode.kBrake);      
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
