// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.Nat;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.LinearPlantInversionFeedforward;
import edu.wpi.first.math.controller.LinearQuadraticRegulator;
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

  private final double kV = 0.01; // volts per (radian per second)
  private final double kA = 0.01; // volts per (radian per second squared)
  private final double stateDev = 0.01;
  private final double measurementDev = 0.01;
  private final double velErrTolerance = 0.01;
  private final double voltageTolerance = 12.0;

  private final LinearSystem<N1,N1,N1> drivePlant = LinearSystemId.identifyVelocitySystem(kV, kA);
  private final KalmanFilter filter = new KalmanFilter<>(Nat.N1(), Nat.N1(), drivePlant,
                                      VecBuilder.fill(stateDev), VecBuilder.fill(measurementDev), 0.02);
  private final LinearQuadraticRegulator<N1,N1,N1> controller = new LinearQuadraticRegulator<>(drivePlant, VecBuilder.fill(velErrTolerance), 
                                                                VecBuilder.fill(voltageTolerance), 0.02);

  /** Creates a new MecanumDrive. */
  public MecDrive() {

    if (Constants.MECANUM_DRIVE_EXISTS) {
      gyro = new ADIS16470_IMU();
      gyro.calibrate();

      leftFront = new CANSparkMax(Constants.Motors.leftFront.getId(), MotorType.kBrushless);
      leftBack = new CANSparkMax(Constants.Motors.leftBack.getId(), MotorType.kBrushless);
      rightFront = new CANSparkMax(Constants.Motors.rightFront.getId(), MotorType.kBrushless);
      rightBack = new CANSparkMax(Constants.Motors.rightBack.getId(), MotorType.kBrushless);

      leftFront.restoreFactoryDefaults();
      leftBack.restoreFactoryDefaults();
      rightFront.restoreFactoryDefaults();
      rightBack.restoreFactoryDefaults();

      leftFront.setInverted(Constants.Motors.leftFront.getReversed());
      leftBack.setInverted(Constants.Motors.leftBack.getReversed());
      rightFront.setInverted(Constants.Motors.rightFront.getReversed());
      rightBack.setInverted(Constants.Motors.rightBack.getReversed());

      leftFront.setIdleMode(IdleMode.kBrake);
      leftBack.setIdleMode(IdleMode.kBrake);
      rightFront.setIdleMode(IdleMode.kBrake);
      rightBack.setIdleMode(IdleMode.kBrake);
      
    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double magnitude, double theta, double turn) {
    double gamma = theta - Math.toRadians(gyro.getAngle(ADIS16470_IMU.IMUAxis.kYaw)); // <- This enables field-centric drive
    /**
     * theta is is the angle of the joystick
     * magnitude is equivalent to the hypotnuse created by the x and y vector of the joystick
     * 
     * Front-left and back-right wheel speed: sin(theta - pi/4) * magnitude + turn
     * Front-right and back-left wheel speed: sin(theta + pi/4) * magnitude + turn
     * 
     */

    double FL_BR_pow = Math.sin(gamma - Math.PI/4) * magnitude + turn;
    double FR_BL_pow = Math.sin(gamma + Math.PI/4) * magnitude + turn;
    /**
     * This scales down the values of the speeds if any's absolute value exceeds 1
     */
    if (Math.abs(FL_BR_pow) > 1 || Math.abs(FR_BL_pow) > 1) {
      //double max = Math.max(Math.abs(FL_BR_pow), Math.abs(FR_BL_pow));
      double max = Math.max(FL_BR_pow, FR_BL_pow);
      leftFront.set(FL_BR_pow/max * Constants.MAX_MEC_SPEED);
      leftBack.set(FR_BL_pow/max * Constants.MAX_MEC_SPEED);
      rightFront.set(FR_BL_pow/max * Constants.MAX_MEC_SPEED);
      rightBack.set(FL_BR_pow/max  * Constants.MAX_MEC_SPEED);
    } else {
      leftFront.set(FL_BR_pow * Constants.MAX_MEC_SPEED);
      leftBack.set(FR_BL_pow * Constants.MAX_MEC_SPEED);
      rightFront.set(FR_BL_pow * Constants.MAX_MEC_SPEED);
      rightBack.set(FL_BR_pow * Constants.MAX_MEC_SPEED);
    }
  }

  public void setSpeedBasic(double speed, double turn, double strafe) {
    double scale = Math.max(speed + turn + strafe, 1.0);

    double leftFrontPower = ((speed + turn + strafe) / scale);
    double leftBackPower = ((speed + turn - strafe) / scale);
    double rightFrontPower = ((speed - turn - strafe) / scale);
    double rightBackPower = ((speed - turn + strafe) / scale);

    leftFront.set(leftFrontPower);
    leftBack.set(leftBackPower);
    rightFront.set (rightFrontPower);
    rightBack.set (rightBackPower);
  
    SmartDashboard.putNumber("LF Power", leftFrontPower);
    SmartDashboard.putNumber("LB Power", leftBackPower); 
    SmartDashboard.putNumber("RF Power", rightFrontPower);
    SmartDashboard.putNumber("RB Power", rightBackPower);
  }

  public void brake() {
    leftFront.set(0);
    leftBack.set(0);
    rightFront.set(0);
    rightBack.set(0);
  }

}
