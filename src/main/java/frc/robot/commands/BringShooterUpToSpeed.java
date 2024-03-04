// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class BringShooterUpToSpeed extends Command {

  private boolean shootHighGoal;
  private Shooter shooter;

  /** Creates a new BringShooterUpToSpeed. */
  public BringShooterUpToSpeed(Shooter s, boolean shootHighGoal) {
    this.shooter = s;
    this.shootHighGoal = shootHighGoal;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s);
  }

  public BringShooterUpToSpeed(Shooter s) {
    this (s, false);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
