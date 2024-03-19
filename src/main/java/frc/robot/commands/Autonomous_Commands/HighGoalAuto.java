// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous_Commands;

import java.io.PushbackInputStream;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Pause;
import frc.robot.commands.Intake_Commands.DeployIntake;
import frc.robot.commands.Shooter_Commands.BringShooterUpToSpeed;
import frc.robot.commands.Shooter_Commands.ShootHighGoal;
import frc.robot.commands.Shooter_Commands.TurnOffShooter;
import frc.robot.commands.TransferStation_Commands.TurnOffTransfer;
import frc.robot.commands.TransferStation_Commands.TurnOnTransfer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MecDrive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TransferStation;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HighGoalAuto extends SequentialCommandGroup {

  private MecDrive drive;
  private TransferStation transfer;
  private Shooter shooter;
  private Intake intake;

  /** Creates a new HighGoalAuto. */
  public HighGoalAuto(MecDrive d, TransferStation t, Shooter s, Intake i) {

    drive = d;
    transfer = t;
    shooter = s;
    intake = i;

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new BringShooterUpToSpeed(shooter, true), // Turn on shooter
      new SimpleAuto(drive, 1.0),           // Drive backwards and turn towards speaker
      new TurnOnTransfer(transfer),           // Move note into shooter
      new Pause(2.0),                         // Wait until note shoots
      new TurnOffShooter(shooter),
      new TurnOffTransfer(transfer),
      new DeployIntake(intake),
      new SimpleAuto(drive, 1.0)              // Drive backwards accross the line
      );
  }
}
