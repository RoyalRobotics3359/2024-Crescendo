// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.TransferStation;

/**
 * This command turns on the rollers in the Transfer station to move a
 * note into the Shooter.  It does not care about the TransferStation's
 * limit switch.  That is used to know when a note is fully held by the
 * TransferStation.  Instead this commend runs the TransferStation for
 * a period of time long enough to move a note into the shooter.
 */
public class TurnOnTransferStation extends Command {

  /** Reference to the robot's TransferStation */
  private TransferStation transfer;

  private CANSparkMax roller;

  /** Is the command finished? */
  private boolean done;

  /** Creates a new TurnOnTransferStation. */
  public TurnOnTransferStation(TransferStation t) {
    transfer = t;
    if (Constants.TRANSFER_STATION_EXIST) {
      roller = new CANSparkMax(Constants.Motors.transferStationTop.getId(), MotorType.kBrushless);

      roller.restoreFactoryDefaults();

      roller.setInverted(Constants.Motors.rearIntakeRoller.isReversed());

      roller.setIdleMode(IdleMode.kBrake);
    }
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(transfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
    System.out.println("Transfer: Turn On scheduled");
    done = false;    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!done) {
      transfer.set(Constants.TRANSFER_STATION_SPEED);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    done = true;
    System.out.println("Transfer: Turn On " + (interrupted ? "Interrupted" : "Complete"));
  }

  /**
   * This command turns on the transfer station rollers and immediately exits
   */
  @Override
  public boolean isFinished() {
    return true;
  }
}
