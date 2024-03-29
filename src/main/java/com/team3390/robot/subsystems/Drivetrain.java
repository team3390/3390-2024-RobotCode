package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  private static Drivetrain instance;

  private boolean isBreakMode = false;

  private final Configuration talonConfiguration = new Configuration();
  private final WPI_TalonSRX frontLeft, frontRight, rearLeft, rearRight;
  private final MecanumDrive driveController;

  private final AHRS navX = new AHRS(Constants.SENSOR_NAVX_PORT);

  public synchronized static Drivetrain getInstance() {
    if (instance == null) {
      instance = new Drivetrain();
    }
    return instance;
  }

  public Drivetrain() {
    talonConfiguration.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    frontLeft = new WPI_TalonSRX(Constants.DRIVE_FRONT_LEFT_ID);
    frontRight = new WPI_TalonSRX(Constants.DRIVE_FRONT_RIGHT_ID);
    rearLeft = new WPI_TalonSRX(Constants.DRIVE_REAR_LEFT_ID);
    rearRight = new WPI_TalonSRX(Constants.DRIVE_REAR_RIGHT_ID);

    frontLeft.setInverted(Constants.DRIVE_FRONT_LEFT_INVERTED);
    frontRight.setInverted(Constants.DRIVE_FRONT_RIGHT_INVERTED);
    rearLeft.setInverted(Constants.DRIVE_REAR_LEFT_INVERTED);
    rearRight.setInverted(Constants.DRIVE_REAR_RIGHT_INVERTED);

    // MotorController frontLeftMotor, MotorController rearLeftMotor, MotorController frontRightMotor, MotorController rearRightMotor
    driveController = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
    driveController.setSafetyEnabled(false);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Heading", getHeading());
  }

  public double getHeading() {
    return navX.getAngle();
  }

  public Rotation2d getHeading2d() {
    return Rotation2d.fromDegrees(navX.getAngle());
  }

  public void resetGyro() {
    navX.reset();
  }

  /**
   * Drive method for Mecanum platform.
   *
   * <p>Angles are measured counterclockwise from the positive X axis. The robot's speed is
   * independent of its angle or rotation rate.
   * @param xSpeed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param ySpeed The robot's speed along the Y axis [-1.0..1.0]. Left is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Counterclockwise is
   *     positive.
   */
  public void driveCartesian(double xSpeed, double ySpeed, double zRotation) {
    driveCartesian(xSpeed, ySpeed, zRotation, false);
  }

  /**
   * Drive method for Mecanum platform.
   *
   * <p>Angles are measured counterclockwise from the positive X axis. The robot's speed is
   * independent of its angle or rotation rate.
   * @param xSpeed The robot's speed along the X axis [-1.0..1.0]. Forward is positive.
   * @param ySpeed The robot's speed along the Y axis [-1.0..1.0]. Left is positive.
   * @param zRotation The robot's rotation rate around the Z axis [-1.0..1.0]. Counterclockwise is
   *     positive.
   * @param fod Field-Oriented Driving
   */
  public void driveCartesian(double xSpeed, double ySpeed, double zRotation, boolean fod) {
    if (xSpeed + ySpeed + zRotation != 0) {
      System.out.println("Drive Execute");
      if (fod) {
        driveController.driveCartesian(xSpeed, ySpeed, zRotation, getHeading2d());
      } else {
        driveController.driveCartesian(xSpeed, ySpeed, zRotation);
      }
    }
  }

  public void stopMotors() {
    driveController.stopMotor();
  }

  public void rotateToAngle(double angle) {
    driveController.drivePolar(0.0, getHeading2d(), angle);
  }
}