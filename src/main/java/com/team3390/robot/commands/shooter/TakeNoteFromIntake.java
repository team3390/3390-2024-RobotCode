package com.team3390.robot.commands.shooter;

import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class TakeNoteFromIntake extends Command {

  private final ShooterSubsystem shooterSubsystem;

  public TakeNoteFromIntake(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
    shooterSubsystem.setIsPivotPIDActive(false);
  }

  @Override
  public void execute() {
    if (!shooterSubsystem.isBackPressing()) {
      shooterSubsystem.setPivotMotor(-0.6);
    } else {
      if (!shooterSubsystem.hasNote()) {
        shooterSubsystem.feedTorus(0.5);
      } else {
        shooterSubsystem.stopFeederMotor();
      }
    }
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.setIsPivotPIDActive(true);
    shooterSubsystem.stopFeederMotor();
  }

  @Override
  public boolean isFinished() {
    return shooterSubsystem.hasNote();
  }
}
