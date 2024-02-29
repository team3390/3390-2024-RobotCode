package com.team3390.robot.commands.intake;

import com.team3390.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class GiveToShooter extends Command {

  private final IntakeSubsystem intakeSubsystem;

  public GiveToShooter(IntakeSubsystem intakeSubsystem) {
    this.intakeSubsystem = intakeSubsystem;
    addRequirements(intakeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intakeSubsystem.setIntakeMotor(-0.75);
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.stopIntakeMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
