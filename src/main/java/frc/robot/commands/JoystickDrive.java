// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.OperatorConsole;
import frc.robot.subsystems.MecDrive;
import frc.robot.subsystems.TankDrive;

public class JoystickDrive extends Command {
  /** Fields */
  private MecDrive mecDrive;
  private TankDrive tankDrive;
  private OperatorConsole console;
  private String mode;

  /** Creates a new JoystickDrive. */
  public JoystickDrive(MecDrive drive, OperatorConsole oc, String m) {
    // Use addRequirements() here to declare subsystem dependencies.
    mecDrive = drive;
    console = oc;
    this.setMode(m);
    addRequirements(drive);
  }

  public JoystickDrive(TankDrive drive, OperatorConsole oc, String m) {
    tankDrive = drive;
    console = oc;
    this.setMode(m);
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
    double leftPower = console.getDController().getLeftStickY() * Constants.MAX_VOLTAGE;
    double rightPower = console.getDController().getRightStickY() * Constants.MAX_VOLTAGE;
    switch (getMode()) {
      case "mec":
        // mecDrive.setSpeed(
        //   console.getDController().getLeftStickHyp(), 
        //   console.getDController().getLeftStickTheta(), 
        //   console.getDController().getRightStickX());
        // double speed = console.getDController().getLeftStickY();
        // double turn = console.getDController().getRightStickX();
        // double strafe = console.getDController().getLeftStickX();

        double mag = console.getDController().getLeftStickHyp();
        double angle = console.getDController().getLeftStickTheta();
        double turn = console.getDController().getRightStickX();

        // if (speed < Constants.JOYSTICK_DEADBAND && speed > -1.0 * Constants.JOYSTICK_DEADBAND) {
        //   speed = 0.0; 
        // }
        // if (turn < Constants.JOYSTICK_DEADBAND && turn > -1.0 * Constants.JOYSTICK_DEADBAND) {
        //   turn = 0.0; 
        // }
        // if (strafe < Constants.JOYSTICK_DEADBAND && strafe > -1.0 * Constants.JOYSTICK_DEADBAND) {
        //   strafe = 0.0; 
        // }

        // mecDrive.setSpeedBasic(speed, turn, strafe);
        
        // mecDrive.setSpeed(mag, angle, turn);
        mecDrive.setSpeedPID(mag, angle, turn);
        break;
      case "tank":
        tankDrive.setVoltage(leftPower, rightPower);
        break;
      default:
        tankDrive.setVoltage(leftPower, rightPower);
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (getMode().equals("mec")){
      mecDrive.brake();
    } else {
      tankDrive.brake();
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private void setMode(String mode) {
    this.mode = mode;
  }

  private String getMode() { return mode; }
}
