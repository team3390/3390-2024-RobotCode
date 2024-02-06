package com.team3390.robot.commands.shooter;

import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class Shoot extends Command {

  private final ShooterSubsystem shooterSubsystem;

  private double timestamp;

  public Shoot(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
    timestamp = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    shooterSubsystem.setShooterMotor(shooterSubsystem.getCalculatedShooterSpeed());
    if (shooterSubsystem.hasNote()) {
      if ((Timer.getFPGATimestamp() - timestamp) > 2.5) {
        shooterSubsystem.feedTorus(1);
        timestamp = Timer.getFPGATimestamp();
      } else {
        shooterSubsystem.stopFeederMotor();
      }
    } else {
      shooterSubsystem.stopFeederMotor();
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopFeederMotor();
    shooterSubsystem.stopShooterMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
