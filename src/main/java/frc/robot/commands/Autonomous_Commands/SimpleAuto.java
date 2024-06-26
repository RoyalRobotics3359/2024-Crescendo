// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous_Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.MecDrive;

public class SimpleAuto extends Command {

  private MecDrive drive;
  private Timer timer;
  private double time;

  private double speed;
  private double turn;
  private double crab;

  /** Creates a new SimpleAuto. */
  public SimpleAuto(MecDrive d, double t, double s, double tu, double c) {

    drive = d;
    time = t;

    speed = s;
    turn = tu;
    crab = c;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drive.setSpeedBasic(speed, turn, crab);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.brake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timer.hasElapsed(time))
      return true;
    return false;
  }
}
