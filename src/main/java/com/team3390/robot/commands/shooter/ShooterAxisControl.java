package com.team3390.robot.commands.shooter;

import java.util.function.DoubleSupplier;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ShooterAxisControl extends Command {

  private final ShooterSubsystem shooterSubsystem;

  private final DoubleSupplier pivot;

  public ShooterAxisControl(ShooterSubsystem shooterSubsystem, DoubleSupplier pivot) {
    this.shooterSubsystem = shooterSubsystem;
    this.pivot = pivot;
    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double calcspeed = Math.abs(pivot.getAsDouble()) > Constants.SHOOTER_PIVOT_DEADBAND ? pivot.getAsDouble() : 0.0;
    shooterSubsystem.setPivotMotor(calcspeed);
  }

  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopPivotMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
