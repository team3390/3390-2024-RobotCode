// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team3390.lib.drivers.LazyTalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.lib.math.PID;
import com.team3390.robot.Constants;
import com.team3390.robot.utility.CompetitionShuffleboard;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  private static ShooterSubsystem instance;
  private final CompetitionShuffleboard shuffleboard = CompetitionShuffleboard.getInstance();

  private final Configuration motorConfig = new Configuration();
  private final LazyTalonSRX pivotMotorMaster, pivotMotorSlave, trigerMotorMaster, trigerMotorSlave, shooterMotor;
  private final boolean isBreakMode = true;

  private final PID pivotPID = new PID(
    0.02,
    0.00,
    0.00075,
    0.5,
    Constants.SHOOTER_PIVOT_PID_MAX_OUT,
    Constants.SHOOTER_PIVOT_PID_MIN_OUT
  );
  private boolean isPIDActive = true;

  public synchronized static ShooterSubsystem getInstance() {
    if (instance == null) {
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    motorConfig.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    pivotMotorMaster = TalonSRXCreator.createTalon(Constants.SHOOTER_PIVOT_MOTOR_MASTER_ID, motorConfig);
    pivotMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.SHOOTER_PIVOT_MOTOR_SLAVE_ID, Constants.SHOOTER_PIVOT_MOTOR_MASTER_ID, motorConfig);
    trigerMotorMaster = TalonSRXCreator.createTalon(Constants.SHOOTER_TRIGER_MOTOR_MASTER_ID, motorConfig);
    trigerMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.SHOOTER_TRIGER_MOTOR_SLAVE_ID, Constants.SHOOTER_TRIGER_MOTOR_MASTER_ID, motorConfig);
    shooterMotor = TalonSRXCreator.createTalon(Constants.SHOOTER_SHOT_MOTOR_ID, motorConfig);

    pivotPID.setSetpoint(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setShooterAngle(Constants.SHOOTER_POSITIONS pos) {
    pivotPID.setSetpoint(pos.angle);
  }

  public boolean isShooterAtSetpoint() {
    return pivotPID.atSetpoint();
  }

  public void resetShooterEncoder() {}

  public double getShooterAngle() {
    return 0.0;
  }

  public void setPivotMotor(double speed) {
    pivotMotorMaster.set(speed);
  }

  public void stopPivotMotor() {
    pivotMotorMaster.stopMotor();
  }

  public void setIsPivotPIDActive(boolean isActive) {
    isPIDActive = isActive;
  }

  public boolean getIsPivotPIDActive() {
    return isPIDActive;
  }

  public void setTrigerMotor(double speed) {
    trigerMotorMaster.set(speed);
  }

  public void stopTrigerMotor() {
    trigerMotorMaster.stopMotor();
  }

  public void setShooterMotor(double speed) {
    shooterMotor.set(speed);
  }

  public void stopShooterMotor() {
    shooterMotor.stopMotor();
  }
}
