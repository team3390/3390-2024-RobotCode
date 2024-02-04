// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.auto;

import com.team3390.robot.commands.shooter.Reload;
import com.team3390.robot.subsystems.ElevatorSubsystem;
import com.team3390.robot.subsystems.IntakeSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class AutonomeReload extends Command {
  private final ShooterSubsystem shooterSubsystem;
  private final ElevatorSubsystem elevatorSubsystem;
  private final IntakeSubsystem intakeSubsystem;
  /** Creates a new AutonomeReload. */
  public AutonomeReload(ShooterSubsystem shooterSubsystem, ElevatorSubsystem elevatorSubsystem,
  IntakeSubsystem intakeSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    this.elevatorSubsystem = elevatorSubsystem;
    this.intakeSubsystem = intakeSubsystem;
    addRequirements(shooterSubsystem,elevatorSubsystem,intakeSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    new Reload(shooterSubsystem, elevatorSubsystem, intakeSubsystem);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopTrigerMotor();
    elevatorSubsystem.stopMotors();
    intakeSubsystem.stopIntakeMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooterSubsystem.reloaded();
  }
}
