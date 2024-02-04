// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.shooter;

import com.team3390.robot.Constants.SHOOTER_POSITIONS;
import com.team3390.robot.subsystems.ElevatorSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class SetToIntake extends Command {

  private final ShooterSubsystem shooterSubsystem;
  private final ElevatorSubsystem elevatorSubsystem;
  private final SHOOTER_POSITIONS pos;
  /** Creates a new SetToIntake. */
  public SetToIntake(ShooterSubsystem shooterSubsystem, ElevatorSubsystem elevatorSubsystem, SHOOTER_POSITIONS pos) {
    this.shooterSubsystem = shooterSubsystem;
    this.elevatorSubsystem = elevatorSubsystem;
    this.pos = pos;
    addRequirements(shooterSubsystem, elevatorSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevatorSubsystem.setSpeed(0.15);
    shooterSubsystem.setShooterAngle(pos);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return shooterSubsystem.isShooterAtSetpoint();
  }
}
