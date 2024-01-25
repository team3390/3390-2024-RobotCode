package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team3390.lib.drivers.LazyTalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.lib.math.PID;
import com.team3390.robot.Constants;
import com.team3390.robot.utility.CompetitionShuffleboard;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem instance;
  private final CompetitionShuffleboard shuffleboard = CompetitionShuffleboard.getInstance();

  private final Configuration motorConfig = new Configuration();
  private final LazyTalonSRX pivotMotor, intakeMotor;
  private final boolean isBreakMode = true;

  private final DigitalInput intakeSwitch;
  private boolean hasNote = false;
  private boolean last;
  private int count = -1;

  private final PID pivotPID = new PID(
    0.02,
    0.00,
    0.00075,
    0.5,
    Constants.INTAKE_PIVOT_PID_MAX_OUT,
    Constants.INTAKE_PIVOT_PID_MIN_OUT
  );
  private boolean isPIDActive = true;

  public synchronized static IntakeSubsystem getInstance() {
    if (instance == null) {
      instance = new IntakeSubsystem();
    }
    return instance;
  }

  public IntakeSubsystem() {
    motorConfig.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    pivotMotor = TalonSRXCreator.createTalon(Constants.INTAKE_PIVOT_MOTOR_ID, motorConfig);
    intakeMotor = TalonSRXCreator.createTalon(Constants.INTAKE_HAND_MOTOR_ID, motorConfig);

    intakeSwitch = new DigitalInput(Constants.INTAKE_SWITCH_ID);

    pivotPID.setSetpoint(0);
  }

  @Override
  public void periodic() {
    shuffleboard.hasNoteEntry.setBoolean(hasNote);
    
    if (isPIDActive) {
      double pivotMotorSpeed = pivotPID.output(pivotPID.calculate(getIntakeAngle()));
      pivotMotor.set(pivotMotorSpeed);
    }

    updateSwitch();
  }

  private void updateSwitch() {
    if (intakeSwitch.get() == false && last == true) {
      count++;
    }
    last = intakeSwitch.get();
    hasNote = (count % 2) == 1;
  }

  public boolean hasNote() {
    return hasNote;
  }

  public void setIntakeAngle(Constants.INTAKE_POSITIONS pos) {
    pivotPID.setSetpoint(pos.angle);
  }

  public boolean isIntakeAtSetpoint() {
    return pivotPID.atSetpoint();
  }

  public void resetPivotEncoder() {}

  public double getIntakeAngle() {
    return 0.0;
  }

  public void setPivotMotor(double speed) {
    pivotMotor.set(speed);
  }

  public void stopPivotMotor() {
    pivotMotor.stopMotor();
  }

  public void setIsPivotPIDActive(boolean isActive) {
    isPIDActive = isActive;
  }

  public boolean getIsPivotPIDActive() {
    return isPIDActive;
  }

  public void setIntakeMotor(double speed) {
    intakeMotor.set(speed);
  }

  public void stopIntakeMotor() {
    intakeMotor.stopMotor();
  }
}
