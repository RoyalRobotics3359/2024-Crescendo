// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Climb extends SubsystemBase {

  private DoubleSolenoid left;
  private DoubleSolenoid right;

  private Robot robot;

  /** Creates a new Climb. */
  public Climb(Robot r) {
 
    robot = r;

    left = robot.getPneumaticHub().makeDoubleSolenoid(Constants.Pneumatics.climberLeftUp.getId(), Constants.Pneumatics.climberLeftDown.getId());
    right = robot.getPneumaticHub().makeDoubleSolenoid(Constants.Pneumatics.climberRightUp.getId(), Constants.Pneumatics.climberRightDown.getId());

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void deploy() {
    left.set(DoubleSolenoid.Value.kForward);
    right.set(DoubleSolenoid.Value.kForward);
  }

  public void retract() {
    left.set(DoubleSolenoid.Value.kReverse);
    right.set(DoubleSolenoid.Value.kReverse);
  }
}
