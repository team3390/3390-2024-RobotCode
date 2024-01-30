// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.shooter;

import com.team3390.robot.Constants.SHOOTER_POSITIONS;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class ShotAMP extends Command {

  private final ShooterSubsystem shooterSubsystem;

  /** Creates a new ShotAMP. */
  public ShotAMP(ShooterSubsystem shooterSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    addRequirements(shooterSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterSubsystem.setShooterAngle(SHOOTER_POSITIONS.AMP);
    new WaitCommand(0.2);
    shooterSubsystem.setShooterMotor(0.2);
    new WaitCommand(0.5);
    shooterSubsystem.setTrigerMotor(0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopPivotMotor();
    shooterSubsystem.stopTrigerMotor();
    shooterSubsystem.stopShooterMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
