package com.team3390.robot.commands.auto;

import com.team3390.robot.Constants.INTAKE_POSITIONS;
import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.commands.intake.IntakeNote;
import com.team3390.robot.commands.intake.SetIntakeAngle;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.ElevatorSubsystem;
import com.team3390.robot.subsystems.IntakeSubsystem;
import com.team3390.robot.subsystems.LimelightSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Red_3NoteAuto extends SequentialCommandGroup {

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
          () -> 0.35,
          () -> 0.0, 
          () -> 0.0,
          false
        )
      ),
      new AutonomeShoot(shooterSubsystem, limelightSubsystem),
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.FLOOR),
      new ParallelDeadlineGroup(
        new WaitCommand(1.75),
        new MecanumDrive(
          drivetrain,
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
          () -> 0.38,
          () -> 0.0, 
          () -> -0.07,
          false
        )
      ),
      new AutonomeReload(shooterSubsystem, elevatorSubsystem, intakeSubsystem),
      new AutonomeShoot(shooterSubsystem, limelightSubsystem),
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.FLOOR),
      new ParallelDeadlineGroup(
        new WaitCommand(1.25),
        new MecanumDrive(
          drivetrain,
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
          () -> 0.3,
          () -> 0.0, 
          () -> 0.0,
          false
        )
      ),
      new AutonomeReload(shooterSubsystem, elevatorSubsystem, intakeSubsystem),
      new AutonomeShoot(shooterSubsystem, limelightSubsystem)
    );
  }
}
