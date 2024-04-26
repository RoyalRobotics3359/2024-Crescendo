// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Intake_Commands.RetractIntake;
import frc.robot.commands.TransferStation_Commands.TurnOffTransfer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TransferStation;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class StopShooting extends ParallelCommandGroup {
  /** Creates a new StopShooting. */

  private Shooter shooter;
  private TransferStation transfer;
  private Intake intake;

  public StopShooting(Shooter s, TransferStation t, Intake i) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());

    shooter = s;
    transfer = t;
    intake = i;

    addCommands(
      new TurnOffShooter(shooter),
      new TurnOffTransfer(transfer),
      new RetractIntake(intake)
    );
  }
}
