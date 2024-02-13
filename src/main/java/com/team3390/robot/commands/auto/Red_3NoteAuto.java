package com.team3390.robot.commands.auto;

import com.team3390.robot.Constants.INTAKE_POSITIONS;
import com.team3390.robot.Constants.SHOOTER_POSITIONS;
import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.commands.intake.GiveToShooter;
import com.team3390.robot.commands.intake.IntakeNote;
import com.team3390.robot.commands.intake.SetIntakeAngle;
import com.team3390.robot.commands.shooter.SetShooterAngle;
import com.team3390.robot.commands.shooter.Shoot;
import com.team3390.robot.commands.shooter.ShooterAxisControl;
import com.team3390.robot.commands.shooter.TakeNoteFromIntake;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.ElevatorSubsystem;
import com.team3390.robot.subsystems.IntakeSubsystem;
import com.team3390.robot.subsystems.LimelightSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class Red_3NoteAuto extends SequentialCommandGroup {

  /*
   * TODO: shooter active parametresi gereksiz
   * eğer olursa bu sefer timingler kayacak tekrar kontrol edelim
   */
  public Red_3NoteAuto(Drivetrain drivetrain, LimelightSubsystem limelightSubsystem,
  ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem, ElevatorSubsystem elevatorSubsystem) {
    addCommands(
      new InstantCommand(() -> {
        drivetrain.resetGyro();
      }),
      new ParallelDeadlineGroup(
        new WaitCommand(3.5),
        new MecanumDrive(
          drivetrain,
          () -> shooterSubsystem.isShooterActive(),
          () -> 0.35,
          () -> 0.0, 
          () -> 0.0,
          false
        )
      ),
      new InstantCommand(() -> {
        new Trigger(() -> limelightSubsystem.isYAtSetpoint()).whileFalse(new ShooterAxisControl(shooterSubsystem, () -> 0.3));
      }),
      new Shoot(shooterSubsystem),
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.FLOOR),
      new ParallelDeadlineGroup(
        new WaitCommand(1.75),
        new MecanumDrive(
          drivetrain,
          () -> shooterSubsystem.isShooterActive(),
          () -> -0.2,
          () -> 0.4, 
          () -> 0.0,
          false
        )
      ),
      new IntakeNote(intakeSubsystem),
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.ELEVATOR),
      new ParallelDeadlineGroup(
        new WaitCommand(2.5),
        new MecanumDrive(
          drivetrain,
          () -> shooterSubsystem.isShooterActive(),
          () -> 0.38,
          () -> 0.0, 
          () -> -0.07,
          false
        )
      ),
      new SetShooterAngle(shooterSubsystem, SHOOTER_POSITIONS.INTAKE),
      new ParallelCommandGroup(
        new TakeNoteFromIntake(shooterSubsystem),
        new GiveToShooter(intakeSubsystem)
      ),
      new InstantCommand(() -> {
        new Trigger(() -> limelightSubsystem.isYAtSetpoint()).whileFalse(new ShooterAxisControl(shooterSubsystem, () -> 0.3));
      }),
      new Shoot(shooterSubsystem),
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.FLOOR),
      new ParallelDeadlineGroup(
        new WaitCommand(1.25),
        new MecanumDrive(
          drivetrain,
          () -> shooterSubsystem.isShooterActive(),
          () -> -0.3,
          () -> 0.0, 
          () -> 0.0,
          false
        )
      ),
      new IntakeNote(intakeSubsystem),
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.ELEVATOR),
      new ParallelDeadlineGroup(
        new WaitCommand(1.25),
        new MecanumDrive(
          drivetrain,
          () -> shooterSubsystem.isShooterActive(),
          () -> 0.3,
          () -> 0.0, 
          () -> 0.0,
          false
        )
      ),
      new SetShooterAngle(shooterSubsystem, SHOOTER_POSITIONS.INTAKE),
      new ParallelCommandGroup(
        new TakeNoteFromIntake(shooterSubsystem),
        new GiveToShooter(intakeSubsystem)
      ),
      new InstantCommand(() -> {
        new Trigger(() -> limelightSubsystem.isYAtSetpoint()).whileFalse(new ShooterAxisControl(shooterSubsystem, () -> 0.3));
      }),
      new Shoot(shooterSubsystem)
    );
  }
}
