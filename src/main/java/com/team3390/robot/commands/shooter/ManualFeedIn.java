package com.team3390.robot.commands.shooter;

import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ManualFeedIn extends Command {

  private final ShooterSubsystem shooterSubsystem;

  public ManualFeedIn(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooterSubsystem.feedTorus(0.75);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopFeederMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
