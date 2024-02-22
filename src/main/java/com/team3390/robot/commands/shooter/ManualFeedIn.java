package com.team3390.robot.commands.shooter;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.IntakeSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ManualFeedIn extends Command {

  private final ShooterSubsystem shooterSubsystem;
  private final IntakeSubsystem intakeSubsystem;

  public ManualFeedIn(ShooterSubsystem shooterSubsystem, IntakeSubsystem intakeSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    this.intakeSubsystem = intakeSubsystem;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    shooterSubsystem.feedTorus(Constants.SHOOTER_FEED_IN_SPEED);
    intakeSubsystem.setIntakeMotor(-0.3);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopFeederMotor();
    intakeSubsystem.stopIntakeMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
