package com.team3390.robot.subsystems;

import com.team3390.lib.math.MathUtil;
import com.team3390.lib.math.PID;
import com.team3390.robot.Constants;
import com.team3390.robot.utility.CompetitionShuffleboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {

  private static LimelightSubsystem instance;
  private final CompetitionShuffleboard shuffleboard = CompetitionShuffleboard.getInstance();

  private final NetworkTable networkTable;

  private final NetworkTableEntry tX; // Derece cinsinden yatay
  private final NetworkTableEntry tY; // Derece cinsinden dikey
  private final NetworkTableEntry tV; // Herhangi bir hedef var mı? (0, 1)
  private final NetworkTableEntry tA; // Hedefin kamerada ne kadar alan kapladığı
  private final NetworkTableEntry tL; // Pipeline ping

  private final PID xPID;
  private final PID yPID;

  public static synchronized LimelightSubsystem getInstance() {
    if (instance == null) {
      instance = new LimelightSubsystem();
    }
    return instance;
  }

  public LimelightSubsystem() {
    networkTable = NetworkTableInstance.getDefault().getTable("limelight");

    tX = networkTable.getEntry("tx");
    tY = networkTable.getEntry("ty");
    tV = networkTable.getEntry("tv");
    tA = networkTable.getEntry("ta");
    tL = networkTable.getEntry("tl");

    xPID = new PID(0.01, 0, 0.00007, 0.05, 0.35, -0.35);
    yPID = new PID(0.05, 0.002, 0.00007, 0, 1, -1);
  }

  @Override
  public void periodic() {
    shuffleboard.limelightXAtSetpointEntry.setBoolean(xPID.atSetpoint());
    shuffleboard.limelightYAtSetpointEntry.setBoolean(yPID.atSetpoint());
    SmartDashboard.putNumber("LM-Pipeline-Ping", tL.getDouble(0));
  }

  public NetworkTableEntry getValue(String key) {
    return networkTable.getEntry(key);
  }

  public void setLedMode(Constants.LIMELIGHT_LIGHT_MODE mode) {
    getValue("ledMode").setNumber(mode.ordinal());
  }

  public Command setLedModeCommand(Constants.LIMELIGHT_LIGHT_MODE mode) {
    return runOnce(() -> setLedMode(mode));
  }

  public void setCamMode(Constants.LIMELIGHT_CAMERA_MODE mode) {
    getValue("camMode").setNumber(mode.ordinal());
  }

  public Command setCamModeCommand(Constants.LIMELIGHT_CAMERA_MODE mode) {
    return runOnce(() -> setCamMode(mode));
  }

  public void setPipeline(int number) {
    getValue("pipeline").setNumber(number);
  }

  public Command setPipelineCommand(int number) {
    return runOnce(() -> setPipeline(number));
  }

  public boolean hasTarget() {
    return tV.getDouble(0) == 1;
  }

  public boolean isXAtSetpoint() {
    return xPID.atSetpoint();
  }

  public boolean isYAtSetpoint() {
    return yPID.atSetpoint();
  }

  public double getXOutput() {
    if (this.hasTarget() && !xPID.atSetpoint()) {
      double x = tX.getDouble(0);
      return xPID.output(xPID.calculate(x, 0));
    }
    return 0;
  }

  public double getYOutput() {
    if (this.hasTarget() && !yPID.atSetpoint()) {
      double y = tY.getDouble(0);
      return yPID.output(yPID.calculate(y, 0));
    }
    return 0;
  }

  public double getCalculatedShooterSpeed() {
    if (this.hasTarget()) {
      double speed = MathUtil.clamp(
        (100 - tA.getDouble(0)) * Constants.LIMELIGHT_SHOOTER_SPEED_COEFFICIENT,
      -1, 1);
      if (Math.abs(speed) < 0.01) {
        return 0;
      }
      return speed;
    }
    return 0;
  }
}
