package com.team3390.robot.commands.auto;

import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Red_3NoteAuto extends SequentialCommandGroup {

  public Red_3NoteAuto(Drivetrain drivetrain) {
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
      new WaitCommand(2),
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
      new ParallelDeadlineGroup(
        new WaitCommand(1.25),
        new MecanumDrive(
          drivetrain,
          () -> 0.3,
          () -> 0.0, 
          () -> 0.0,
          false
        )
      )
    );
  }
}
