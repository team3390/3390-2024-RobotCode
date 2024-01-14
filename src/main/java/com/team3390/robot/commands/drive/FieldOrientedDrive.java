package com.team3390.robot.commands.drive;

import java.util.function.DoubleSupplier;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.Command;

public class FieldOrientedDrive extends Command {

  private final Drivetrain drivetrain;
  private final DoubleSupplier xSpeed, ySpeed, zRotation;

  private final SlewRateLimiter xLimiter, yLimiter, rotationLimiter;

  public FieldOrientedDrive(Drivetrain drivetrain, DoubleSupplier xSpeed, DoubleSupplier ySpeed, DoubleSupplier zRotation) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    this.zRotation = zRotation;
    this.drivetrain = drivetrain;
    this.xLimiter = new SlewRateLimiter(Constants.DRIVE_RATE_LIMIT);
    this.yLimiter = new SlewRateLimiter(Constants.DRIVE_RATE_LIMIT);
    this.rotationLimiter = new SlewRateLimiter(Constants.DRIVE_RATE_LIMIT);
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double x = xSpeed.getAsDouble();
    double y = ySpeed.getAsDouble();
    double rot = zRotation.getAsDouble();

    x = Math.abs(x) > Constants.DRIVE_X_DEADBAND ? x : 0.0;
    y = Math.abs(y) > Constants.DRIVE_Y_DEADBAND ? y : 0.0;
    rot = Math.abs(rot) > Constants.DRIVE_ROTATION_DEADBAND ? rot : 0.0;

    x = xLimiter.calculate(x);
    y = yLimiter.calculate(y);
    rot = rotationLimiter.calculate(rot);

    drivetrain.driveCartesian(x, y, rot, true);
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
