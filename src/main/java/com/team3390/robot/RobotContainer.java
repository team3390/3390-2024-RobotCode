/**
 * @author team3390, canokan
 */

package com.team3390.robot;

import com.team3390.robot.commands.auto.Any_Taxi;
import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.commands.elevator.ElevatorDown;
import com.team3390.robot.commands.elevator.ElevatorUp;
import com.team3390.robot.commands.elevator.ToggleBrake;
import com.team3390.robot.commands.intake.IntakeAxisControl;
import com.team3390.robot.commands.intake.IntakeNote;
import com.team3390.robot.commands.shooter.ManualFeedIn;
import com.team3390.robot.commands.shooter.ManualFeedOut;
import com.team3390.robot.commands.shooter.ManualShoot;
import com.team3390.robot.commands.shooter.ShooterAxisControl;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.ElevatorSubsystem;
import com.team3390.robot.subsystems.IntakeSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  private final Drivetrain drivetrain = Drivetrain.getInstance();
  private final ElevatorSubsystem elevator = ElevatorSubsystem.getInstance();
  private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
  
  private final Joystick leftStick = new Joystick(Constants.JOYSTICK_LEFT_PORT);
  private final Joystick rightStick = new Joystick(Constants.JOYSTICK_RIGHT_PORT);
  private final Joystick gamepad = new Joystick(Constants.JOYSTICK_GAMEPAD_PORT);

  private final Command manualShootCommand = new ManualShoot(shooter);
  private final Command manualFeedInCommand = new ManualFeedIn(shooter, intake);
  private final Command manualFeedOutCommand = new ManualFeedOut(shooter);
  
  private final Command intakeNoteCommand = new IntakeNote(intake);

  private final Compressor comp = new Compressor(PneumaticsModuleType.CTREPCM);

  private final SendableChooser<Integer> autoModeChooser = new SendableChooser<>();
  private Command selectedAuto;

  public RobotContainer() {
    comp.enableDigital();
    drivetrain.setDefaultCommand(new MecanumDrive(
      drivetrain, 
      () -> shooter.isShooterActive(),
      () -> -gamepad.getRawAxis(1),
      () -> gamepad.getRawAxis(2),
      () -> (gamepad.getRawAxis(0) / 2),
      false
    ));
    intake.setDefaultCommand(
      new IntakeAxisControl(
        intake,
        () -> rightStick.getY()
      )
    );
    shooter.setDefaultCommand(
      new ShooterAxisControl(
        shooter,
        () -> leftStick.getY()
    ));

    new Trigger(() -> gamepad.getRawButton(2)).whileTrue(new ElevatorDown(elevator));
    new Trigger(() -> gamepad.getRawButton(4)).whileTrue(new ElevatorUp(elevator));
    new Trigger(() -> gamepad.getRawButton(8)).whileTrue(manualShootCommand);
    new Trigger(() -> gamepad.getRawButton(6)).whileTrue(manualFeedInCommand);
    new Trigger(() -> gamepad.getRawButton(5)).whileTrue(manualFeedOutCommand);

    new Trigger(() -> rightStick.getRawButton(1)).whileTrue(manualShootCommand);
    new Trigger(() -> leftStick.getRawButton(1)).whileTrue(manualFeedInCommand);

    new Trigger(() -> rightStick.getRawButton(5)).whileTrue(intakeNoteCommand);
    new Trigger(() -> rightStick.getRawButton(2)).whileTrue(new ToggleBrake(elevator, false));
    new Trigger(() -> rightStick.getRawButton(3)).whileTrue(new ToggleBrake(elevator, true));


    autoModeChooser.addOption("Nothing", 1);
    autoModeChooser.addOption("Taxi", 2);
    autoModeChooser.addOption("May Be 1 Note", 3);

    SmartDashboard.putData("Auto Mode", autoModeChooser);
  }

  public void updateVars() {

  }

  public Command getAutonomousCommand() {
    int sellected = autoModeChooser.getSelected();
    switch (sellected) {
      case 1:
        selectedAuto = null;
        break;

      case 2:
        selectedAuto = new Any_Taxi(drivetrain, shooter);
        break;

      case 3:
        selectedAuto = null;
        break;

      default:
        selectedAuto = new Any_Taxi(drivetrain, shooter);
    }    
    return selectedAuto;
  }
}
