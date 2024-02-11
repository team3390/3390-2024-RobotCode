/**
 * @author team3390, canokan
 */

package com.team3390.robot;

import com.team3390.robot.commands.auto.Red_3NoteAuto;
import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.commands.elevator.ElevatorControl;
import com.team3390.robot.commands.elevator.ElevatorDown;
import com.team3390.robot.commands.elevator.ElevatorUp;
import com.team3390.robot.commands.shooter.ManualFeed;
import com.team3390.robot.commands.shooter.ManualShoot;
import com.team3390.robot.commands.shooter.ShooterAxisControl;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.ElevatorSubsystem;
import com.team3390.robot.subsystems.IntakeSubsystem;
import com.team3390.robot.subsystems.LimelightSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  private final Drivetrain drivetrain = Drivetrain.getInstance();
  private final ElevatorSubsystem elevator = ElevatorSubsystem.getInstance();
  private final LimelightSubsystem limelight = LimelightSubsystem.getInstance();
  private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
  
  private final Joystick leftStick = new Joystick(Constants.JOYSTICK_LEFT_PORT);
  private final Joystick rightStick = new Joystick(Constants.JOYSTICK_RIGHT_PORT);
  private final Joystick gamepad = new Joystick(Constants.JOYSTICK_GAMEPAD_PORT);

  private final Command activateTargetLock = new InstantCommand(() -> {
    shooter.setTargetLock(true);
  });
  private final Command deactivateTargetLock = new InstantCommand(() -> {
    shooter.setTargetLock(false);
  });
  private final Command manualShootCommand = new ManualShoot(shooter);
  private final Command manualFeedCommand = new ManualFeed(shooter);

  public RobotContainer() {
    /* 
    drivetrain.setDefaultCommand(new MecanumDrive(
      drivetrain, 
      () -> shooter.isShooterActive(),
      () -> -leftStick.getY(),
      () -> rightStick.getX(),
      () -> (leftStick.getX() / 3),
      false
    ));
    */
    /* 
    shooter.setDefaultCommand(new ShooterAxisControl(
      shooter, 
      () -> gamepad.getRawAxis(3)
    ));
    */
    drivetrain.setDefaultCommand(new MecanumDrive(
      drivetrain, 
      () -> shooter.isShooterActive(),
      () -> -gamepad.getRawAxis(1),
      () -> gamepad.getRawAxis(2),
      () -> (gamepad.getRawAxis(0) / 3),
      false
    ));
    elevator.setDefaultCommand(
      new ElevatorControl(
      elevator,
      () -> -rightStick.getY()
    ));
    shooter.setDefaultCommand(
      new ShooterAxisControl(
        shooter,
        () -> leftStick.getY()
    ));

    new Trigger(() -> gamepad.getRawButton(1)).whileTrue(activateTargetLock);
    new Trigger(() -> gamepad.getRawButton(3)).whileTrue(deactivateTargetLock);
    new Trigger(() -> gamepad.getRawButton(2)).whileTrue(new ElevatorDown(elevator));
    new Trigger(() -> gamepad.getRawButton(4)).whileTrue(new ElevatorUp(elevator));
    new Trigger(() -> gamepad.getRawButton(8)).whileTrue(manualShootCommand);
    new Trigger(() -> gamepad.getRawButton(6)).whileTrue(manualFeedCommand);

    new Trigger(() -> rightStick.getRawButton(1)).whileTrue(manualShootCommand);
    new Trigger(() -> leftStick.getRawButton(1)).whileTrue(manualFeedCommand);
  }

  public void updateVars() {

  }

  public Command getAutonomousCommand() {
    return new Red_3NoteAuto(drivetrain,limelight,shooter,intake,elevator);
  }
}
