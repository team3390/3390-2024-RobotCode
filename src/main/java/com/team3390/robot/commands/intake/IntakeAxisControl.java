package com.team3390.robot.commands.intake;

import java.util.function.DoubleSupplier;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class IntakeAxisControl extends Command {

  private final IntakeSubsystem intakeSubsystem;

  private final DoubleSupplier pivot;

  public IntakeAxisControl(IntakeSubsystem intakeSubsystem, DoubleSupplier pivot) {
    this.intakeSubsystem = intakeSubsystem;
    this.pivot = pivot;
    addRequirements(intakeSubsystem);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double calcspeed = Math.abs(pivot.getAsDouble()) > Constants.SHOOTER_PIVOT_DEADBAND ? pivot.getAsDouble() : 0.0;
    intakeSubsystem.setPivotMotor(calcspeed);
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubsystem.stopPivotMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
