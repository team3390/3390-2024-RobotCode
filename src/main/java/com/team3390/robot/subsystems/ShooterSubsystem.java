package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team3390.lib.drivers.LazyTalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {

  private static ShooterSubsystem instance;
  private final LimelightSubsystem limelightSubsystem = LimelightSubsystem.getInstance();

  private final Configuration motorConfig = new Configuration();
  private final LazyTalonSRX pivotMotorMaster, pivotMotorSlave, shooterMotorMaster, shooterMotorSlave, feederMotor;
  private final boolean isBreakMode = true;

  private boolean isPIDActive = true;
  private boolean isShooterActive = false;

  private final DigitalInput hasNoteSwitch, shooterBackSwitch, shooterFrontSwitch;

  public synchronized static ShooterSubsystem getInstance() {
    if (instance == null) {
      instance = new ShooterSubsystem();
    }
    return instance;
  }

  public ShooterSubsystem() {
    motorConfig.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    pivotMotorMaster = TalonSRXCreator.createTalon(Constants.SHOOTER_PIVOT_MOTOR_MASTER_ID, motorConfig);
    pivotMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.SHOOTER_PIVOT_MOTOR_SLAVE_ID,
    Constants.SHOOTER_PIVOT_MOTOR_MASTER_ID, motorConfig);
    shooterMotorMaster = TalonSRXCreator.createTalon(Constants.SHOOTER_SHOT_MOTOR_MASTER_ID, motorConfig);
    shooterMotorSlave = TalonSRXCreator.createCustomPermanentSlaveTalon(Constants.SHOOTER_SHOT_MOTOR_SLAVE_ID,
    Constants.SHOOTER_SHOT_MOTOR_MASTER_ID, motorConfig);
    feederMotor = TalonSRXCreator.createTalon(Constants.SHOOTER_FEEDER_MOTOR_ID, motorConfig);

    hasNoteSwitch = new DigitalInput(Constants.SHOOTER_SWITCH_ID);
    shooterBackSwitch = new DigitalInput(8);
    shooterFrontSwitch = new DigitalInput(9);
  }

  @Override
  public void periodic() {
    if (isPIDActive && Constants.ROBOT_ENABLED) {
      double speed = limelightSubsystem.getYOutput();
      this.setPivotMotor(speed);
    }
  }

  public void setTargetLock(boolean isActive){
    isPIDActive = isActive;
  }

  public double getCalculatedShooterSpeed() {
    return limelightSubsystem.getCalculatedShooterSpeed();
  }

  public void setIsPivotPIDActive(boolean isActive) {
    isPIDActive = isActive;
  }

  public void setPivotMotor(double speed) {
    pivotMotorMaster.set(speed);
    pivotMotorSlave.set(speed);
  }

  public void stopPivotMotor() {
    pivotMotorMaster.stopMotor();
  }


  public boolean getIsPivotPIDActive() {
    return isPIDActive;
  }

  public void setShooterMotor(double speed) {
    shooterMotorMaster.set(speed);
    shooterMotorSlave.set(speed);
  }

  public void stopShooterMotor() {
    shooterMotorMaster.stopMotor();
  }

  public void feedTorus(double speed) {
    feederMotor.set(speed);
  }

  public void stopFeederMotor() {
    feederMotor.stopMotor();
  }

  public boolean isBackPressing() {
    return shooterBackSwitch.get();
  }

  public boolean isFrontPressing() {
    return shooterFrontSwitch.get();
  }

  public boolean hasNote() {
    return !hasNoteSwitch.get();
  }
  public boolean isShooterActive(){
    return isShooterActive;
  }
  public void setShooterActive(boolean isShooterActive){
    this.isShooterActive = isShooterActive;
  }
}
