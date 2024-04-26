// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.Constants.Pneumatics;

/***
 * Intake is a subsystem that picks up a note (game piece) from the floor.
 * To do that it has a pair of pneumatic cylinders that can extend or retract
 * the intake from the stored position (inside the frame perimeter at start of
 * the match) to the extended poisition (to pickup a note from the floor).
 * when extended, the intake must run the rollers forward. when retracted
 * the intake must run the rolers to transfer the note (game piece) into the hopper. 
 */
public class Intake extends SubsystemBase {

  private DoubleSolenoid left;
  private DoubleSolenoid right;
  private TalonSRX roller;

  private Robot robot;

  /** Creates a new Intake. */
  public Intake(Robot r) {
    if (Constants.INTAKE_ROLLERS_EXIST) {
      // left = new DoubleSolenoid(PneumaticsModuleType.REVPH, Pneumatics.intakeLeftForward.getId(), Pneumatics.intakeLeftReverse.getId());
      // right = new DoubleSolenoid(PneumaticsModuleType.REVPH, Pneumatics.intakeRightForward.getId(), Pneumatics.intakeRightReverse.getId());

      robot = r;

      left = robot.getPneumaticHub().makeDoubleSolenoid(Pneumatics.intakeLeftForward.getId(), Pneumatics.intakeLeftReverse.getId());
      right = robot.getPneumaticHub().makeDoubleSolenoid(Pneumatics.intakeRightForward.getId(), Pneumatics.intakeRightReverse.getId());

      roller = new TalonSRX(Constants.Motors.IntakeRoller.getId());

      // roller.restoreFactoryDefaults();

      roller.setInverted(Constants.Motors.IntakeRoller.isReversed());

      roller.setNeutralMode(NeutralMode.Brake);

      retract();
    }
  }

  /***
   * This method is called 20 times a second to control the intake's hardware
   */
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Intake Current:", roller.getOutputCurrent());
  }

  /**
   * it retuns the intake to its starting position(Inside the frame perimeter.)
   * 
   */
  public void retract() { 
    left.set(DoubleSolenoid.Value.kReverse);
    right.set(DoubleSolenoid.Value.kReverse);
    turnOffRoller();
  }

  /**
   * It deploys the intake so that it is in a position where it can pick up notes(game pieces)
   */
  public void extend() {
    left.set(DoubleSolenoid.Value.kForward);
    right.set(DoubleSolenoid.Value.kForward);   
    turnOnRoller(); 
  }

  /**
   * Turns the rollers on in a forward direction to pull note from the floor into the intake
   */
  public void turnOnRoller() {
    if (Constants.INTAKE_ROLLERS_EXIST) {
      roller.set(TalonSRXControlMode.PercentOutput, Constants.ROLLER_SPEED);
    }
  }

  /**
   * Turns the rollers off
   */
  public void turnOffRoller() {
    if (Constants.INTAKE_ROLLERS_EXIST) {
      roller.set(TalonSRXControlMode.PercentOutput, 0.0);
    }
  }

  public void reverseRoller() {
    if (Constants.INTAKE_ROLLERS_EXIST) {
      roller.set(TalonSRXControlMode.PercentOutput, 1.0 * Constants.ROLLER_SPEED);
    }
  }

}
