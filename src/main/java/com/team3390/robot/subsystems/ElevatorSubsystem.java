package com.team3390.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team3390.lib.drivers.TalonSRXCreator.Configuration;
import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {

  private static ElevatorSubsystem instance;

  private final WPI_TalonSRX master, slave;
  private final Configuration talonConfig = new Configuration();
  private final boolean isBreakMode = false;

  private final Solenoid brake;

  public synchronized static ElevatorSubsystem getInstance() {
    if (instance == null) {
      instance = new ElevatorSubsystem();
    }
    return instance;
  }

  public ElevatorSubsystem() {
    talonConfig.NEUTRAL_MODE = isBreakMode ? NeutralMode.Brake : NeutralMode.Coast;
    talonConfig.INVERTED = true;
    master = new WPI_TalonSRX(Constants.ELEVATOR_MOTOR_MASTER_ID);
    slave = new WPI_TalonSRX(Constants.ELEVATOR_MOTOR_SLAVE_ID);

    brake = new Solenoid(PneumaticsModuleType.CTREPCM, 4);
  }

  @Override
  public void periodic() {}

  public void setSpeed(double speed) {
    if (speed != 0) {
      master.set(speed);
      slave.set(speed);
    }
  }

  public void stopMotors() {
    master.stopMotor();
    slave.stopMotor();
  }

  public void setBrake(boolean on) {
    brake.set(!on);
  }
}
