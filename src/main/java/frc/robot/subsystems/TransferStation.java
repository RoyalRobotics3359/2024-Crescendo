// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TransferStation extends SubsystemBase {

  private CANSparkMax left;
  private CANSparkMax right;
  private CANSparkMax top;

  private DigitalInput limitSwitch;

  // STAGE 1 = top 
  // STAGE 2 = left & right

  /** Creates a new TransferStation. */
  public TransferStation() {
    if (Constants.TRANSFER_STATION_EXIST) {
      left = new CANSparkMax(Constants.Motors.transferStationLeft.getId(), MotorType.kBrushless);
      right = new CANSparkMax(Constants.Motors.transferStationRight.getId(), MotorType.kBrushless);
      top = new CANSparkMax(Constants.Motors.transferStationTop.getId(), MotorType.kBrushed);

      left.restoreFactoryDefaults();
      right.restoreFactoryDefaults();
      top.restoreFactoryDefaults();

      left.setInverted(Constants.Motors.transferStationLeft.isReversed());
      right.setInverted(Constants.Motors.transferStationRight.isReversed());
      top.setInverted(Constants.Motors.transferStationTop.isReversed());

      left.setIdleMode(IdleMode.kCoast);
      right.setIdleMode(IdleMode.kCoast);
      top.setIdleMode(IdleMode.kCoast); 

      limitSwitch = new DigitalInput(Constants.TRANSFER_STATION_LIMIT_SWITCH_CHANNEL);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setStage1Power() {
    if (Constants.TRANSFER_STATION_EXIST) {
      top.set(Constants.TRANSFER_STATION_SPEED);
    }
  }

  public void brakeStage1() {
    if (Constants.TRANSFER_STATION_EXIST) {
      top.set(0);
    }
  }

  // Accepts values between -1 (inclusive) and 1 (inclusive)
  public void setStage2Power() {
    if (Constants.TRANSFER_STATION_EXIST) {
      left.set(Constants.TRANSFER_STATION_SPEED);
      right.set(0.0);
      // right.set(Constants.TRANSFER_STATION_SPEED);
    }
  }

  public void brakeStage2() {
    if (Constants.TRANSFER_STATION_EXIST) {
      left.set(0);
      right.set(0);
    }
  }

  public boolean switchPressed() { 
    if (Constants.TRANSFER_STATION_EXIST) {
      return limitSwitch.get();
    }
    return false;
  }

}
