package com.team3390.robot.commands.drive;

import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;

public class RotateToAngle extends Command {

  private final Drivetrain drivetrain;

  private final double angle;
  private final double absAngle;
  private double startingAngle;
  private final double deadband = Constants.DRIVE_ROTATION_DEADBAND;

  public RotateToAngle(Drivetrain drivetrain, double angle) {
    this.drivetrain = drivetrain;
    this.angle = angle;
    this.absAngle = Math.abs(angle);
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    startingAngle = drivetrain.getHeading();
  }

  @Override
  public void execute() {
    // TODO: açıya dönmeyebilir yöne göre değiştir bu kısmı
    drivetrain.rotateToAngle(angle);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stopMotors();
  }

  @Override
  public boolean isFinished() {
    // 45 + 2 < current angle && current angle > 45 - 2
    double currentAngle = Math.abs(drivetrain.getHeading() - startingAngle);
    return absAngle + deadband < currentAngle && currentAngle > absAngle - deadband;
  }
}
