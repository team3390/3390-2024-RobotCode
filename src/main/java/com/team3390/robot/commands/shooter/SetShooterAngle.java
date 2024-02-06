// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.shooter;

import com.team3390.robot.Constants.SHOOTER_POSITIONS;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class SetShooterAngle extends Command {

  private final ShooterSubsystem shooterSubsystem;

  private double k = 0;

  /** Creates a new SetShooterAngle. */
  public SetShooterAngle(ShooterSubsystem shooterSubsystem, SHOOTER_POSITIONS pos){
    this.shooterSubsystem = shooterSubsystem;

    switch (pos) {
      case INTAKE:
        k = -1;
        break;
    
      case AMP:
        k = 1;
        break;

      default:
        break;
    }

    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
    shooterSubsystem.setIsPivotPIDActive(false);
  }

  @Override
  public void execute() {
    shooterSubsystem.setPivotMotor(k * 0.7);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopPivotMotor();
    shooterSubsystem.setIsPivotPIDActive(false);
  }

  @Override
  public boolean isFinished() {
    if (k < 0) {
      return shooterSubsystem.isBackPressing();
    } else if (k > 0) {
      return shooterSubsystem.isFrontPressing();
    } else {
      return shooterSubsystem.isBackPressing() || shooterSubsystem.isFrontPressing();
    }
  }
}
