// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake_Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

/**
 * It extentds the intake to pick up notes from the floor.
 */
public class DeployIntake extends Command {

  private Intake intake; 
  private Timer timer; 
  private boolean done;

  /** Creates a new DeployIntake Command. */
  public DeployIntake(Intake in) {
    intake = in;
    done = false;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("Intake: Deploy scheduled");
    timer = new Timer();
    timer.start();
    done = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!done) {
      intake.extend();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      done = true;
    }
    System.out.println("Intake: Deploy " + (interrupted ? "Interrupted" : "Complete"));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean timerExpired = timer.hasElapsed(Constants.INTAKE_DEPLOY_TIME);
    if (timerExpired) {
      done = true;
    }
    return done;
  }
}
