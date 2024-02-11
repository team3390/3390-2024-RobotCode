/**
 * @author team3390, canokan
 */

package com.team3390.robot;

import com.team3390.robot.commands.auto.Red_3NoteAuto;
import com.team3390.robot.commands.drive.MecanumDrive;
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
  
  private final Joystick driver_gamepad = new Joystick(Constants.JOYSTICK_DRIVER_GAMEPAD_PORT);
  private final Joystick helper_gamepad = new Joystick(Constants.JOYSTICK_HELPER_GAMEPAD_PORT);

  private final Command activateTargetLock = new InstantCommand(() -> {
    shooter.setTargetLock(true);
  });
  private final Command deactivateTargetLock = new InstantCommand(() -> {
    shooter.setTargetLock(false);
  });

  public RobotContainer() {
    drivetrain.setDefaultCommand(new MecanumDrive(
      drivetrain, 
      () -> shooter.isShooterActive(),
      () -> -driver_gamepad.getRawAxis(1),
      () -> driver_gamepad.getRawAxis(2),
      () -> (driver_gamepad.getRawAxis(0) / 3),
      false
    ));
    shooter.setDefaultCommand(new ShooterAxisControl(
      shooter, 
      () -> helper_gamepad.getRawAxis(3)
    ));

    new Trigger(() -> driver_gamepad.getRawButton(1)).whileTrue(activateTargetLock);
    new Trigger(() -> driver_gamepad.getRawButton(3)).whileTrue(deactivateTargetLock);
    new Trigger(() -> driver_gamepad.getRawButton(2)).whileTrue(new ElevatorDown(elevator));
    new Trigger(() -> driver_gamepad.getRawButton(4)).whileTrue(new ElevatorUp(elevator));
    new Trigger(() -> driver_gamepad.getRawButton(8)).whileTrue(new ManualShoot(shooter));
    new Trigger(() -> driver_gamepad.getRawButton(6)).whileTrue(new ManualFeed(shooter));
  }

  public void updateVars() {

  }

  public Command getAutonomousCommand() {
    return new Red_3NoteAuto(drivetrain,limelight,shooter,intake,elevator);
  }
}
