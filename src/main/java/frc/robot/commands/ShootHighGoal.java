// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TransferStation;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

/**
 * - Brings shooter up to speed
 * - Turn on transfer station to bring note into shooter
 * - turn off shooter
 */
public class ShootHighGoal extends SequentialCommandGroup {

  private Shooter shooter;
  private TransferStation transfer;

  /** Creates a new ShootHighGoal. */
  public ShootHighGoal(Shooter s, TransferStation t) {

    shooter = s;
    transfer = t;
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new BringShooterUpToSpeed(shooter, true), 
      new TurnOnTransferStation(transfer), 
      new TurnOffShooter(shooter));
  }
}
