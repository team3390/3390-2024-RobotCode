package com.team3390.robot.commands.shooter;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ManualShoot extends Command {

  private final ShooterSubsystem shooterSubsystem;

  public ManualShoot(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
  }

  @Override
  public void initialize() {
    shooterSubsystem.setShooterActive(true);
  }

  @Override
  public void execute() {
    shooterSubsystem.setShooterMotor(Constants.SHOOTER_SHOOT_SPEED);
  }
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopShooterMotor();
    shooterSubsystem.setShooterActive(false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
