package com.team3390.robot.commands.drive;

import com.team3390.lib.math.PID;
import com.team3390.robot.Constants;
import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;

public class RotateToAngle extends Command {

  private final Drivetrain drivetrain;

  private final double angle;
  private double startingAngle = 0;

  private final PID pid = new PID(
    Constants.DRIVE_ROTATION_PID_P,
    Constants.DRIVE_ROTATION_PID_I,
    Constants.DRIVE_ROTATION_PID_D,
    Constants.DRIVE_ROTATION_PID_TOLERANCE,
    Constants.DRIVE_ROTATION_PID_MAX_OUT,
    Constants.DRIVE_ROTATION_PID_MIN_OUT
  );

  public RotateToAngle(Drivetrain drivetrain, double angle) {
    this.drivetrain = drivetrain;
    this.angle = angle;
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    startingAngle = drivetrain.getHeading();
    pid.setSetpoint(angle);
    pid.reset();
  }

  @Override
  public void execute() {
    double rotSpeed = pid.output(pid.calculate(drivetrain.getHeading() - startingAngle));
    drivetrain.driveCartesian(0, 0, rotSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    drivetrain.stopMotors();
  }

  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
  }
}
