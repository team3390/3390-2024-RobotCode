package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.team3390.lib.drivers.LazyTalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private static ElevatorSubsystem instance;

  private final LazyTalonSRX master, slave;
  private final Configuration talonConfig = new Configuration();
  private final boolean isBreakMode = true;

  private final DigitalInput downSwitch;

  public synchronized static ElevatorSubsystem getInstance() {
    if (instance == null) {
      instance = new ElevatorSubsystem();
    }
    return instance;
  }

  public ElevatorSubsystem() {
    talonConfig.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    talonConfig.INVERTED = true;
    master = TalonSRXCreator.createTalon(Constants.ELEVATOR_MOTOR_MASTER_ID, talonConfig);
    slave = TalonSRXCreator.createTalon(Constants.ELEVATOR_MOTOR_SLAVE_ID, talonConfig);

    downSwitch = new DigitalInput(Constants.ELEVATOR_DOWN_SWITCH_ID);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("ElevatorDownSwitch", downSwitch.get());
  }

  public void setSpeed(double speed) {
    master.set(speed);
    slave.set(speed);
  }

  public void stopMotors() {
    master.stopMotor();
    slave.stopMotor();
  }

  public boolean IsElevatorDown(){
    return downSwitch.get() == false;
  }
}
