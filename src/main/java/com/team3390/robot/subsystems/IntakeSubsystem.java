package com.team3390.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.team3390.lib.math.PID;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private final CANSparkMax pivotMotor = new CANSparkMax(Constants.INTAKE_PIVOT_MOTOR_ID, Constants.INTAKE_PIVOT_MOTOR_TYPE);

  private final RelativeEncoder pivotMotorEncoder;

  private final PID posPID = new PID(
    Constants.INTAKE_PIVOT_PID_P,
    Constants.INTAKE_PIVOT_PID_I,
    Constants.INTAKE_PIVOT_PID_D,
    Constants.INTAKE_PIVOT_PID_TOLERANCE,
    Constants.INTAKE_PIVOT_PID_MAX_OUT,
    Constants.INTAKE_PIVOT_PID_MIN_OUT
  );
  private boolean isPIDActive = true;

  public IntakeSubsystem() {
    pivotMotor.setIdleMode(IdleMode.kBrake);

    pivotMotorEncoder = pivotMotor.getEncoder();
    pivotMotorEncoder.setPositionConversionFactor(Constants.INTAKE_PIVOT_MOTOR_RATIO);

    resetPivotEncoder();

    posPID.setSetpoint(0);
  }

  @Override
  public void periodic() {
    if (isPIDActive) {
      double pivotMotorSpeed = posPID.output(posPID.calculate(getIntakeAngle()));
      pivotMotor.set(pivotMotorSpeed);
    }
  }

  public void setIntakeAngle(Constants.INTAKE_POSITIONS pos) {
    posPID.setSetpoint(pos.angle);
  }

  public void resetPivotEncoder() {
    pivotMotorEncoder.setPosition(0);
  }

  public double getIntakeAngle() {
    return pivotMotorEncoder.getPosition();
  }

  public void setPivotMotor(double speed) {
    pivotMotor.set(speed);
  }

  public void stopPivotMotor() {
    pivotMotor.stopMotor();
  }

  public void setIsPIDActive(boolean val) {
    isPIDActive = val;
  }

  public boolean getIsPIDActive() {
    return isPIDActive;
  }
}
