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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  private static ShooterSubsystem instance;

  private final Configuration motorConfig = new Configuration();
  private final LazyTalonSRX pivotMotorMaster, pivotMotorSlave, shooterMotorMaster, shooterMotorSlave, trigerMotor;
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

  private final DigitalInput shooterSwitch;

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
    shooterMotorMaster = TalonSRXCreator.createTalon(Constants.SHOOTER_SHOT_MOTOR_MASTER_ID, motorConfig);
    shooterMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.SHOOTER_SHOT_MOTOR_SLAVE_ID, Constants.SHOOTER_SHOT_MOTOR_MASTER_ID, motorConfig);
    trigerMotor = TalonSRXCreator.createTalon(Constants.SHOOTER_TRIGER_MOTOR_ID, motorConfig);

    pivotPID.setSetpoint(0);

    shooterSwitch = new DigitalInput(Constants.SHOOTER_SWITCH_ID);
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

  public void setShooterMotor(double speed) {
    shooterMotorMaster.set(speed);
  }

  public void stopShooterMotor() {
    shooterMotorMaster.stopMotor();
  }

  public void feedTorus(double speed) {
    trigerMotor.set(speed);
  }

  public void stopTrigerMotor() {
    trigerMotor.stopMotor();
  }

  public boolean reloaded(){
    return shooterSwitch.get() == false;
  }
}
