// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.OperatorConsole;
import frc.robot.subsystems.MecDrive;

public class JoystickDrive extends Command {
  /** Fields */
  private MecDrive mecDrive;
  private OperatorConsole console;

  /** Creates a new JoystickDrive. */
  public JoystickDrive(MecDrive drive, OperatorConsole oc) {
    // Use addRequirements() here to declare subsystem dependencies.
    mecDrive = drive;
    console = oc;
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    // This method intentionally left blank
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double mag = console.getDController().getLeftStickHyp();
    double angle = console.getDController().getLeftStickTheta();
    double turn = console.getDController().getRightStickX();

    // double speed = console.getDController().getLeftStickY();
    // double turn = console.getDController().getRightStickX();
    // double strafe = console.getDController().getLeftStickX();
    mecDrive.setSpeed(mag, angle, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mecDrive.brake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
