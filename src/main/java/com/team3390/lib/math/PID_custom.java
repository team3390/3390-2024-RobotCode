package com.team3390.lib.math;

import edu.wpi.first.wpilibj.Timer;

public class PID_custom {
  
  private final double kP;
  private final double kI;
  private final double kD;
  private final double ILimit;

  private final double tolerance;

  private double maxOut;
  private double minOut;

  private double setpoint;
  private double errorSum = 0;
  private double lastTimestamp = 0;
  private double lastError = 0;

  public PID_custom(double kP, double kI, double kD, double ILimit, double tolerance, double maxOutput, double minOutput) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;

    this.ILimit = ILimit;

    this.tolerance = tolerance;

    this.maxOut = maxOutput;
    this.minOut = minOutput;
  }

  public PID_custom(double kP, double kI, double kD, double tolerance, double maxOutput, double minOutput) {
    this(kP, kI, kD, 0.0, tolerance, maxOutput, minOutput);
  }

  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }

  public void reset() {
    errorSum = 0;
    lastError = 0;
    lastTimestamp = Timer.getFPGATimestamp();
  }

  public double calculate(double measurement) {
    double error = setpoint - measurement;
    double deltaT = Timer.getFPGATimestamp() - lastTimestamp;

    if (Math.abs(error) < ILimit) {
      errorSum += error * deltaT;
    }

    double errorRate = (error - lastError) / deltaT;

    lastTimestamp = Timer.getFPGATimestamp();
    lastError = error;
    return kP * error + kI * errorSum + kD * errorRate;
  }

  public double calculate(double target, double measurement) {
    setpoint = target;
    return calculate(measurement);
  }

}
