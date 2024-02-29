package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem instance;

  private final Configuration motorConfig = new Configuration();
  private final WPI_TalonSRX pivotMotor;
  private final CANSparkMax intakeMotor1, intakeMotor2;
  private final boolean isBreakMode = false;

  public synchronized static IntakeSubsystem getInstance() {
    if (instance == null) {
      instance = new IntakeSubsystem();
    }
    return instance;
  }

  public IntakeSubsystem() {
    motorConfig.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    pivotMotor = new WPI_TalonSRX(Constants.INTAKE_PIVOT_MOTOR_ID);
    intakeMotor1 = new CANSparkMax(1, MotorType.kBrushless);
    intakeMotor2 = new CANSparkMax(2, MotorType.kBrushless);
  }

  @Override
  public void periodic() {}

  public void setPivotMotor(double speed) {
    if (speed != 0) {
      pivotMotor.set(speed);
    }
  }

  public void stopPivotMotor() {
    pivotMotor.stopMotor();
  }
  public void setIntakeMotor(double speed) {
    if (speed != 0) {
      intakeMotor1.set(speed);
      intakeMotor2.set(speed);
    }
  }

  public void stopIntakeMotor() {
    intakeMotor1.stopMotor();
    intakeMotor2.stopMotor();
  }
}
