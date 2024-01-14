package com.team3390.robot.commands.drive;

import java.util.function.DoubleSupplier;

import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;

public class FieldOrientedDrive extends Command {

  private Drivetrain drivetrain;
  private DoubleSupplier xSpeed, ySpeed, zRotation;

  public FieldOrientedDrive(Drivetrain drivetrain, DoubleSupplier xSpeed, DoubleSupplier ySpeed, DoubleSupplier zRotation) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.zRotation = zRotation;
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    drivetrain.driveCartesian(xSpeed.getAsDouble(), ySpeed.getAsDouble(), zRotation.getAsDouble(), true);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
