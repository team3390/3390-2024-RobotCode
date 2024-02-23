package com.team3390.robot.commands.elevator;

import com.team3390.robot.subsystems.ElevatorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ToggleBrake extends Command {

  private final ElevatorSubsystem elevatorSubsystem;
  private final boolean on;

  public ToggleBrake(ElevatorSubsystem elevatorSubsystem, boolean on) {
    this.elevatorSubsystem = elevatorSubsystem;
    this.on = on;
  }

  @Override
  public void initialize() {
    elevatorSubsystem.setBrake(on);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
