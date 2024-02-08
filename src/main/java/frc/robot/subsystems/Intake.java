// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.Constants.Pneumatics;

/***
 * Intake is a subsystem that picks up a note (game piece) from the floor.
 * To do that it has a pair of pneumatic cylinders that can extend or retract
 * the intake from the stored position (inside the frame perimeter at start of
 * the match) to the extended poisition (to pickup a note from the floor).
 */
public class Intake extends SubsystemBase {

  private DoubleSolenoid left;
  private DoubleSolenoid right;


  /** Creates a new Intake. */
  public Intake() {
    left = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Pneumatics.intakeLeftForward.getId(), Pneumatics.intakeLeftReverse.getId());
    right = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Pneumatics.intakeRightForward.getId(), Pneumatics.intakeRightReverse.getId());
  }

  /***
   * This method is called 20 times a second to control the intake's hardware
   */
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * it retuns the intake to its starting posotion(Inside the frame perimeter.)
   * 
   */
  public void retract() { 
    left.set(DoubleSolenoid.Value.kReverse);
    right.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * It deploys the intake so that it is in a posation where it can pick up notes(game peaces)
   */
  public void extend() {
    left.set(DoubleSolenoid.Value.kForward);
    right.set(DoubleSolenoid.Value.kForward);
  }
}
