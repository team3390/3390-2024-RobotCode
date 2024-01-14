package com.team3390.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;

public final class Constants {
  
  public static final boolean ROBOT_FIELD_MODE = DriverStation.isFMSAttached();
  public static final boolean TUNING_MODE = false;

  public static final int JOYSTICK_LEFT_PORT = 0;
  public static final int JOYSTICK_RIGHT_PORT = 1;
  public static final int JOYSTICK_GAMEPAD_PORT = 4;

  public static final int     DRIVE_FRONT_LEFT_ID = 0;
  public static final int     DRIVE_FRONT_RIGHT_ID = 1;
  public static final int     DRIVE_REAR_LEFT_ID = 2;
  public static final int     DRIVE_REAR_RIGHT_ID = 3;
  public static final boolean DRIVE_FRONT_LEFT_INVERTED = false;
  public static final boolean DRIVE_FRONT_RIGHT_INVERTED = true;
  public static final boolean DRIVE_REAR_LEFT_INVERTED = false;
  public static final boolean DRIVE_REAR_RIGHT_INVERTED = true;
  public static final double  DRIVE_ROTATION_PID_DEADBAND = 2.0;
  public static final double  DRIVE_RATE_LIMIT = 2;
  public static final double  DRIVE_X_DEADBAND = 0.01;
  public static final double  DRIVE_Y_DEADBAND = 0.01;
  public static final double  DRIVE_ROTATION_DEADBAND = 0.01;

  public static final Port SENSOR_NAVX_PORT = Port.kMXP;

}
