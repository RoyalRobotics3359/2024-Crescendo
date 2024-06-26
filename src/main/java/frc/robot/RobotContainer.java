// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.Constants.OperatorConstants;
// import frc.robot.commands.Autos;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Climber_Commands.DeployClimber;
import frc.robot.commands.Climber_Commands.RetractClimber;
import frc.robot.commands.Intake_Commands.DeployIntake;
import frc.robot.commands.Intake_Commands.IntakeNoteFromFloor;
import frc.robot.commands.Intake_Commands.LoadHumanNote;
import frc.robot.commands.Intake_Commands.OutakeNote;
import frc.robot.commands.Intake_Commands.RetractIntake;
import frc.robot.commands.Intake_Commands.ReverseRollers;
import frc.robot.commands.Intake_Commands.turnOnRoller;
import frc.robot.commands.Shooter_Commands.BringShooterUpToSpeed;
import frc.robot.commands.Shooter_Commands.ShootHighGoal;
import frc.robot.commands.Shooter_Commands.ShootLowGoal;
import frc.robot.commands.Shooter_Commands.StopShooting;
import frc.robot.commands.Shooter_Commands.TurnOffShooter;
import frc.robot.commands.TransferStation_Commands.ReverseTransfer;
import frc.robot.commands.TransferStation_Commands.TurnOffTransfer;
import frc.robot.commands.TransferStation_Commands.TurnOnTransfer;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.MecDrive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.TransferStation;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed

  // Default Classes
  private OperatorConsole console;

  // Subsystems
  private MecDrive drive;
  private Intake intake;
  private Shooter shooter;
  private TransferStation transfer;
  private Climb climb;

  // Commands

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(OperatorConsole oc, MecDrive d, Intake i, Shooter s, TransferStation t, Climb c) {

    console = oc;
    drive = d;
    intake = i;
    shooter = s;
    transfer = t;
    climb = c;
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    // FIX ME: CHANGE TO GAME CONTROLLER
    console.getGController().leftBumper().onTrue(new DeployIntake(intake));
    console.getGController().rightBumper().onTrue(new RetractIntake(intake));
    // console.getGController().rightBumper().onTrue(new IntakeNoteFromFloor(intake, transfer));
    
    console.getGController().leftTrigger().onTrue(new TurnOffShooter(shooter));
    console.getGController().rightTrigger().toggleOnTrue(new ShootHighGoal(shooter, transfer, intake));
    console.getGController().rightTrigger().toggleOnFalse(new StopShooting(shooter, transfer, intake));

    console.getGController().a().onTrue(new DeployClimber(climb, intake));
    console.getGController().b().onTrue(new RetractClimber(climb, intake));

    console.getGController().x().onTrue(new TurnOnTransfer(transfer));
    console.getGController().y().onTrue(new TurnOffTransfer(transfer));

    console.getGController().dPadUp().onTrue(new ReverseRollers(intake));
    console.getGController().dPadDown().onTrue(new turnOnRoller(intake));
    // console.getGController().dPadDown().onTrue(new ReverseTransfer(transfer));
    
    console.getGController().leftStickButton().whileTrue(new LoadHumanNote(intake));
    console.getGController().rightStickButton().onTrue(new OutakeNote(intake, transfer));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An example command will be run in autonomous
  //   return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
