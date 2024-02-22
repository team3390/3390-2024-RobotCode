package com.team3390.robot.commands.shooter;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class AutoShoot extends Command {

  private final ShooterSubsystem shooterSubsystem;

  private double timestamp;

  public AutoShoot(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
    timestamp = Timer.getFPGATimestamp();
    shooterSubsystem.setShooterActive(true);
  }

  @Override
  public void execute() {
    shooterSubsystem.setShooterMotor(Constants.SHOOTER_SHOOT_SPEED);
    if ((Timer.getFPGATimestamp() - timestamp) > 2) {
      shooterSubsystem.feedTorus(Constants.SHOOTER_FEED_IN_SPEED);
      timestamp = Timer.getFPGATimestamp();
    } else {
      shooterSubsystem.stopFeederMotor();
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopFeederMotor();
    shooterSubsystem.stopShooterMotor();
    shooterSubsystem.setShooterActive(false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
