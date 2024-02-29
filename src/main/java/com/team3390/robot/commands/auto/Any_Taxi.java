package com.team3390.robot.commands.auto;

import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class Any_Taxi extends SequentialCommandGroup {
  public Any_Taxi(Drivetrain drivetrain, ShooterSubsystem shooterSubsystem) {
    addCommands(
      new ParallelDeadlineGroup(
        new WaitCommand(3.5),
        new MecanumDrive(
          drivetrain,
          () -> shooterSubsystem.isShooterActive(),
          () -> -0.35,
          () -> 0.0, 
          () -> 0.0,
          false
        )
      )
    );
  }
}
