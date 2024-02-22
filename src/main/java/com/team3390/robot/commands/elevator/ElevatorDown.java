package com.team3390.robot.commands.elevator;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ElevatorDown extends Command {

  private final ElevatorSubsystem elevatorSubsystem;

  public ElevatorDown(ElevatorSubsystem elevatorSubsystem) {
    this.elevatorSubsystem = elevatorSubsystem;
    addRequirements(elevatorSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevatorSubsystem.setSpeed(Constants.ELEVATOR_DOWN_SPEED);
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
