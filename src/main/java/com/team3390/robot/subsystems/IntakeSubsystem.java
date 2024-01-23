package com.team3390.robot.subsystems;

import com.team3390.lib.drivers.LazyTalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.lib.math.PID;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

  private static IntakeSubsystem instance;
  
  private final Configuration motorConfig = new Configuration();
  private final LazyTalonSRX pivotMotor;

  private final DigitalInput intakeSwitch;
  private boolean hasNote = false;
  private boolean last;
  private int count = -1;

  private final PID posPID = new PID(
    Constants.INTAKE_PIVOT_PID_P,
    Constants.INTAKE_PIVOT_PID_I,
    Constants.INTAKE_PIVOT_PID_D,
    Constants.INTAKE_PIVOT_PID_TOLERANCE,
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
    pivotMotor = TalonSRXCreator.createTalon(Constants.INTAKE_PIVOT_MOTOR_ID, motorConfig);

    intakeSwitch = new DigitalInput(Constants.INTAKE_SWITCH_ID);

    posPID.setSetpoint(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("HasNote", hasNote);
    if (isPIDActive) {
      double pivotMotorSpeed = posPID.output(posPID.calculate(getIntakeAngle()));
      pivotMotor.set(pivotMotorSpeed);
    }

    if (intakeSwitch.get() == false && last == true) {
      count++;
    }
    last = intakeSwitch.get();
    hasNote = (count % 2) == 1;
  }

  public void setIntakeAngle(Constants.INTAKE_POSITIONS pos) {
    posPID.setSetpoint(pos.angle);
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

  public void setIsPIDActive(boolean val) {
    isPIDActive = val;
  }

  public boolean getIsPIDActive() {
    return isPIDActive;
  }
}
