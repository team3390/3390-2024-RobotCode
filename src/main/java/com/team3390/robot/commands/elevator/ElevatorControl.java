package com.team3390.robot.commands.elevator;

import java.util.function.DoubleSupplier;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorControl extends Command {

  private final ElevatorSubsystem elevatorSubsystem;
  private final DoubleSupplier speed;

  public ElevatorControl(ElevatorSubsystem elevatorSubsystem, DoubleSupplier speed) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.speed = speed;
    addRequirements(elevatorSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double calcspeed = Math.abs(speed.getAsDouble()) > Constants.DRIVE_X_DEADBAND ? speed.getAsDouble() : 0.0;
    elevatorSubsystem.setSpeed(calcspeed);
  }

  @Override
  public void end(boolean interrupted) {
    elevatorSubsystem.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
