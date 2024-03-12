// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter_Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class BringShooterUpToSpeed extends Command {

  private boolean shootHighGoal;
  private Shooter shooter;

  /** Creates a new BringShooterUpToSpeed. */
  public BringShooterUpToSpeed(Shooter s, boolean shootHighGoal) {
    this.shooter = s;
    this.shootHighGoal = shootHighGoal;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Shooter: Bring Up To Speed Scheduled");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (shootHighGoal) {
      shooter.setPower(Constants.SHOOT_HIGH_GOAL_SPEED);
    } else {
      shooter.setPower(Constants.SHOOT_LOW_GOAL_SPEED);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // Leave the shooter running when this command finishes
    System.out.println("Shooter: Bring Up To Speed  " + (interrupted ? "Interrupted" : "Complete"));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooter.isUpToSpeed();
  }
}
