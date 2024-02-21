// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.elevator;

import java.util.function.DoubleSupplier;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorControl extends Command {
  /** Creates a new ElevatorControl. */
  private final ElevatorSubsystem elevatorSubsystem;
  private final DoubleSupplier speed;

  public ElevatorControl(ElevatorSubsystem elevatorSubsystem, DoubleSupplier speed) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.speed = speed;
    addRequirements(elevatorSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double calcspeed = Math.abs(speed.getAsDouble()) > Constants.DRIVE_X_DEADBAND ? speed.getAsDouble() : 0.0;
    elevatorSubsystem.setSpeed(calcspeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    elevatorSubsystem.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
