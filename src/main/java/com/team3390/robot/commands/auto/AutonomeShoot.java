// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.commands.auto;

import com.team3390.robot.commands.shooter.ShootSpeaker;
import com.team3390.robot.subsystems.LimelightSubsystem;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class AutonomeShoot extends Command {

  private final ShooterSubsystem shooterSubsystem;
  private final LimelightSubsystem limelightSubsystem;
  /** Creates a new AutonomeShoot. */
  public AutonomeShoot(ShooterSubsystem shooterSubsystem, LimelightSubsystem limelightSubsystem) {
    this.shooterSubsystem = shooterSubsystem;
    this.limelightSubsystem = limelightSubsystem;
    addRequirements(shooterSubsystem, limelightSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    new ShootSpeaker(shooterSubsystem, limelightSubsystem);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopPivotMotor();
    shooterSubsystem.stopShooterMotor();
    shooterSubsystem.stopFeederMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !shooterSubsystem.reloaded();
  }
}
