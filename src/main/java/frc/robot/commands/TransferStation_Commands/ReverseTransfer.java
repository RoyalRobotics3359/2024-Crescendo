// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.TransferStation_Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TransferStation;

public class ReverseTransfer extends Command {

  private TransferStation transfer;

  /** Creates a new ReverseTransfer. */
  public ReverseTransfer(TransferStation t) {

    transfer = t;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(transfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    transfer.setStage1Power(true);
    transfer.setStage2Power(true);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transfer.brakeStage1();
    transfer.brakeStage2();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
