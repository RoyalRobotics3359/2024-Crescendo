// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.TransferStation_Commands.TransferNote;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.TransferStation;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

/**
 * - Deploy intake
 * - Turn on Transfer Station  <== runs until limit switch detects note
 * - Retract Intake
 *
 */
public class IntakeNoteFromFloor extends SequentialCommandGroup {

    private Intake intake;

    private TransferStation transfer;

  /** Creates a new IntakeNoteFromFloor. */
  public IntakeNoteFromFloor(Intake in, TransferStation t) {
    intake = in;
    transfer = t;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DeployIntake(intake),
      new TransferNote(transfer),
      new RetractIntake(intake)
    );
  }

}
