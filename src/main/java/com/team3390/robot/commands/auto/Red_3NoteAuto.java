package com.team3390.robot.commands.auto;

import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Red_3NoteAuto extends SequentialCommandGroup {

  /*
   * TODO: shooter active parametresi gereksiz
   * eğer olursa bu sefer timingler kayacak tekrar kontrol edelim
   */
  public Red_3NoteAuto(Drivetrain drivetrain, ShooterSubsystem shooterSubsystem) {
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
      )
    );
  }
}
