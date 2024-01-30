// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.elevator;

import com.team3390.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorUp extends Command {

  private final ElevatorSubsystem elevatorSubsystem;
  /** Creates a new ElevatorControl. */
  public ElevatorUp(ElevatorSubsystem elevatorSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    addRequirements(elevatorSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    elevatorSubsystem.setSpeed(0.7);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevatorSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return elevatorSubsystem.IsElevatorUp();
  }
}
