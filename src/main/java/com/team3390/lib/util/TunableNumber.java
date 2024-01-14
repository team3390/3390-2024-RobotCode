package com.team3390.lib.util;

import com.team3390.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TunableNumber {
  private String key;
  private double defaultValue;

  public TunableNumber(String dashboardKey) {
    this.key = dashboardKey;
  }

  public double getDefault() {
    return defaultValue;
  }

  public void setDefault(double defaultValue) {
    this.defaultValue = defaultValue;
    if (Constants.TUNING_MODE) {
      SmartDashboard.putNumber(key, SmartDashboard.getNumber(key, defaultValue));
    }
  }

  public double get() {
    return Constants.TUNING_MODE ? SmartDashboard.getNumber(key, defaultValue) : defaultValue;
  }
}
