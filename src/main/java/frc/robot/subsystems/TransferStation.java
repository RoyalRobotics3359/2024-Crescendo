// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TransferStation extends SubsystemBase {

  private TalonSRX left;
  private TalonSRX right;
  private TalonSRX top;

  private DigitalInput limitSwitch;

  // STAGE 1 = top 
  // STAGE 2 = left & right

  /** Creates a new TransferStation. */
  public TransferStation() {
    if (Constants.TRANSFER_STATION_EXIST) {
      left = new TalonSRX(Constants.Motors.transferStationLeft.getId());
      right = new TalonSRX(Constants.Motors.transferStationRight.getId());
      top = new TalonSRX(Constants.Motors.transferStationTop.getId());

      // left.restoreFactoryDefaults();
      // right.restoreFactoryDefaults();
      // top.restoreFactoryDefaults();

      left.setInverted(Constants.Motors.transferStationLeft.isReversed());
      right.setInverted(Constants.Motors.transferStationRight.isReversed());
      top.setInverted(Constants.Motors.transferStationTop.isReversed());

      left.setNeutralMode(NeutralMode.Brake);
      right.setNeutralMode(NeutralMode.Brake);
      top.setNeutralMode(NeutralMode.Brake); 

      limitSwitch = new DigitalInput(Constants.TRANSFER_STATION_LIMIT_SWITCH_CHANNEL);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (Constants.TRANSFER_STATION_EXIST) {
      SmartDashboard.putNumber("Transfer Top Current:", top.getOutputCurrent());
      SmartDashboard.putNumber("Transfer Left Current:", left.getOutputCurrent());
      SmartDashboard.putNumber("Transfer Right Current:", right.getOutputCurrent());
      SmartDashboard.putBoolean("Limit Switch", isSwitchPressed());
    }
  }

  public void setStage1Power() {
    if (Constants.TRANSFER_STATION_EXIST) {
      top.set(TalonSRXControlMode.PercentOutput, Constants.TRANSFER_STATION_SPEED);
    }
  }

  public void setStage1Power(boolean isReversed) {
    if (Constants.TRANSFER_STATION_EXIST && isReversed) {
      top.set(TalonSRXControlMode.PercentOutput, -1.0 * Constants.TRANSFER_STATION_SPEED);
    }
  }

  public void brakeStage1() {
    if (Constants.TRANSFER_STATION_EXIST) {
      top.set(TalonSRXControlMode.PercentOutput, 0);
    }
  }

  // Accepts values between -1 (inclusive) and 1 (inclusive)
  public void setStage2Power() {
    if (Constants.TRANSFER_STATION_EXIST) {
      left.set(TalonSRXControlMode.PercentOutput, Constants.TRANSFER_STATION_SPEED);
      right.set(TalonSRXControlMode.PercentOutput, Constants.TRANSFER_STATION_SPEED);
      // right.set(Constants.TRANSFER_STATION_SPEED);
    }
  }

  public void setStage2Power(boolean isReversed) {
    if (Constants.TRANSFER_STATION_EXIST && isReversed) {
      left.set(TalonSRXControlMode.PercentOutput, -1.0 * Constants.TRANSFER_STATION_SPEED);
      right.set(TalonSRXControlMode.PercentOutput, -1.0 * Constants.TRANSFER_STATION_SPEED);
    }
  }

  public void brakeStage2() {
    if (Constants.TRANSFER_STATION_EXIST) {
      left.set(TalonSRXControlMode.PercentOutput, 0);
      right.set(TalonSRXControlMode.PercentOutput, 0);
    }
  }

  public boolean isSwitchPressed() { 
    if (Constants.TRANSFER_STATION_EXIST) {
      return !limitSwitch.get();
    }
    return false;
  }

}
