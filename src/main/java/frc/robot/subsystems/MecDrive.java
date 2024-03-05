// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.EncoderType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.LinearPlantInversionFeedforward;
import edu.wpi.first.math.controller.LinearQuadraticRegulator;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.KalmanFilter;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.system.LinearSystem;
import edu.wpi.first.math.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.ADIS16470_IMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.ADIS16448_IMU.IMUAxis;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MecDrive extends SubsystemBase {
  /** Fields */
  private CANSparkMax leftFront;
  private CANSparkMax leftBack;
  private CANSparkMax rightFront;
  private CANSparkMax rightBack;
  private ADIS16470_IMU gyro;

  private PIDController controller;

  private RelativeEncoder fl_encoder, bl_encoder, fr_encoder, br_encoder;

  private final double kV = 0.01; // volts per (radian per second)
  private final double kA = 0.01; // volts per (radian per second squared)
  private final double stateDev = 0.01;
  private final double measurementDev = 0.01;
  private final double velErrTolerance = 0.01;
  private final double voltageTolerance = 12.0;

  /** Creates a new MecanumDrive. */
  public MecDrive() {

    if (Constants.MECANUM_DRIVE_EXISTS) {
      // Creates new gyroscope used in field oriented drive and autonomous
      gyro = new ADIS16470_IMU();
      gyro.calibrate();

      // Defines all of the motorcontrollers used. Then it resets them to factory deafaults to account
      // for any bugs that may arise. Furthermore, it assigns if it is reversed or not, and it sets up
      // its idle mode (in this case always brake).
      leftFront = new CANSparkMax(Constants.Motors.leftFront.getId(), MotorType.kBrushless);
      leftBack = new CANSparkMax(Constants.Motors.leftBack.getId(), MotorType.kBrushless);
      rightFront = new CANSparkMax(Constants.Motors.rightFront.getId(), MotorType.kBrushless);
      rightBack = new CANSparkMax(Constants.Motors.rightBack.getId(), MotorType.kBrushless);

      leftFront.restoreFactoryDefaults();
      leftBack.restoreFactoryDefaults();
      rightFront.restoreFactoryDefaults();
      rightBack.restoreFactoryDefaults();

      leftFront.setInverted(Constants.Motors.leftFront.isReversed());
      leftBack.setInverted(Constants.Motors.leftBack.isReversed());
      rightFront.setInverted(Constants.Motors.rightFront.isReversed());
      rightBack.setInverted(Constants.Motors.rightBack.isReversed());

      leftFront.setIdleMode(IdleMode.kBrake);
      leftBack.setIdleMode(IdleMode.kBrake);
      rightFront.setIdleMode(IdleMode.kBrake);
      rightBack.setIdleMode(IdleMode.kBrake);

      fl_encoder = leftFront.getEncoder();
      bl_encoder = leftBack.getEncoder();
      fr_encoder = rightFront.getEncoder();
      br_encoder = rightBack.getEncoder();

      

      // Defines PID Controller
      controller = new PIDController(0, 0, 0);
    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // ALL mecanum code based on information form this video: https://www.youtube.com/watch?v=gnSW2QpkGXQ

  public void setSpeed(double magnitude, double theta, double turn) {
    double gamma = theta - Math.toRadians(gyro.getAngle(ADIS16470_IMU.IMUAxis.kYaw)); // <- This enables field-centric drive
    // double gamma = theta;
    /**
     * theta is is the angle of the joystick
     * magnitude is equivalent to the hypotnuse created by the x and y vector of the joystick
     * 
     * Front-left and back-right wheel speed: sin(theta - pi/4) * magnitude + turn
     * Front-right and back-left wheel speed: sin(theta + pi/4) * magnitude + turn
     * 
     */

    double sin = Math.sin(gamma - Math.PI/4);
    double cos = Math.cos(gamma - Math.PI/4);
    
    double max = Math.max(Math.abs(sin), Math.abs(cos));

    if (Constants.MECANUM_DRIVE_EXISTS) {
      leftFront.set(magnitude * cos/max + turn);
      leftBack.set(magnitude * sin/max + turn);
      rightFront.set(magnitude * sin/max - turn);
      rightBack.set(magnitude * cos/max - turn);
    }

    /**
     * This scales down the values of the speeds if any's absolute value exceeds 1
     */

    if (Constants.MECANUM_DRIVE_EXISTS && (magnitude + Math.abs(turn)) > 1) {
      leftFront.set(magnitude + turn);
      leftBack.set(magnitude + turn);
      rightFront.set(magnitude + turn);
      rightBack.set(magnitude + turn);
    }

  }

  // Sets field-oriented with PID Loop
  public void setSpeedPID(double magnitude, double theta, double turn) {
    double gamma = theta - Math.toRadians(gyro.getAngle(ADIS16470_IMU.IMUAxis.kYaw)); // <- This enables field-centric drive
    // double gamma = theta;
    /**
     * theta is is the angle of the joystick
     * magnitude is equivalent to the hypotnuse created by the x and y vector of the joystick
     * 
     * Front-left and back-right wheel speed: sin(theta - pi/4) * magnitude + turn
     * Front-right and back-left wheel speed: sin(theta + pi/4) * magnitude + turn
     * 
     */

    double sin = Math.sin(gamma - Math.PI/4);
    double cos = Math.cos(gamma - Math.PI/4);
    
    double max = Math.max(Math.abs(sin), Math.abs(cos));

    if (Constants.MECANUM_DRIVE_EXISTS) {
      leftFront.set(controller.calculate(magnitude * cos/max + turn));
      leftBack.set(controller.calculate(magnitude * sin/max + turn));
      rightFront.set(controller.calculate(magnitude * sin/max - turn));
      rightBack.set(controller.calculate(magnitude * cos/max - turn));
    }

    /**
     * This scales down the values of the speeds if any's absolute value exceeds 1
     */

    if (Constants.MECANUM_DRIVE_EXISTS && (magnitude + Math.abs(turn)) > 1) {
      leftFront.set(controller.calculate(magnitude + turn));
      leftBack.set(controller.calculate(magnitude + turn));
      rightFront.set(controller.calculate(magnitude + turn));
      rightBack.set(controller.calculate(magnitude + turn));
    }

  }

  // Mecanum code without field oriented drive
  public void setSpeedBasic(double speed, double turn, double strafe) {
    double scale = Math.max(speed + turn + strafe, 1.0);

    double leftFrontPower = ((speed + turn + strafe) / scale);
    double leftBackPower = ((speed + turn - strafe) / scale);
    double rightFrontPower = ((speed - turn - strafe) / scale);
    double rightBackPower = ((speed - turn + strafe) / scale);

    if (Constants.MECANUM_DRIVE_EXISTS) {
      leftFront.set(leftFrontPower);
      leftBack.set(leftBackPower);
      rightFront.set (rightFrontPower);
      rightBack.set (rightBackPower);
    }
  
    // Add values to the smart dashboard
    SmartDashboard.putNumber("LF Power", leftFrontPower);
    SmartDashboard.putNumber("LB Power", leftBackPower); 
    SmartDashboard.putNumber("RF Power", rightFrontPower);
    SmartDashboard.putNumber("RB Power", rightBackPower);
  }

  public void brake() {
    if (Constants.MECANUM_DRIVE_EXISTS) {
      leftFront.set(0);
      leftBack.set(0);
      rightFront.set(0);
      rightBack.set(0);
    }
  }

  // Converts the velocity from the encoder to the velocity with the gear ratio
  private double getGearedVelocity(double encoderVelocity) {
    return ((encoderVelocity / 5676) * 5.8125) / 10.71;
  }

  // converts the rpm to voltage
  private double rpmToVoltage(double rpm) {
    return (12/5767.0) * rpm;
  }


}
