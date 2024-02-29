package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  private static ShooterSubsystem instance;

  private final Configuration motorConfig = new Configuration();
  private final WPI_TalonSRX pivotMotorMaster, pivotMotorSlave, shooterMotorMaster, shooterMotorSlave, feederMotor;
  private final boolean isBreakMode = false;

  private boolean isShooterActive = false;

  public synchronized static ShooterSubsystem getInstance() {
    if (instance == null) {
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  public ShooterSubsystem() {
    motorConfig.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    pivotMotorMaster = new WPI_TalonSRX(Constants.SHOOTER_PIVOT_MOTOR_MASTER_ID);
    pivotMotorSlave = new WPI_TalonSRX(Constants.SHOOTER_PIVOT_MOTOR_SLAVE_ID);
    shooterMotorMaster = new WPI_TalonSRX(Constants.SHOOTER_SHOT_MOTOR_MASTER_ID);
    shooterMotorSlave = new WPI_TalonSRX(Constants.SHOOTER_SHOT_MOTOR_SLAVE_ID);
    feederMotor = new WPI_TalonSRX(Constants.SHOOTER_FEEDER_MOTOR_ID);

    shooterMotorSlave.setInverted(true);

    shooterMotorMaster.configOpenloopRamp(Constants.SHOOTER_SHOOT_MOTOR_ACCELERATION);
    shooterMotorSlave.configOpenloopRamp(Constants.SHOOTER_SHOOT_MOTOR_ACCELERATION);
  }

  @Override
  public void periodic() {}

  public void setPivotMotor(double speed) {
    if (speed != 0) {
      pivotMotorMaster.set(speed);
      pivotMotorSlave.set(-speed);
    }
  }

  public void stopPivotMotor() {
    pivotMotorMaster.stopMotor();
    pivotMotorSlave.stopMotor();
  }

  public void setShooterMotor(double speed) {
    shooterMotorMaster.set(speed);
    shooterMotorSlave.set(speed);
  }

  public void stopShooterMotor() {
    shooterMotorMaster.stopMotor();
    shooterMotorSlave.stopMotor();
  }

  public void feedTorus(double speed) {
    feederMotor.set(speed);
  }

  public void stopFeederMotor() {
    feederMotor.stopMotor();
  }

  public boolean isShooterActive() {
    return isShooterActive;
  }

  public void setShooterActive(boolean isShooterActive){
    this.isShooterActive = isShooterActive;
  }
}
