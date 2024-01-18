package com.team3390.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;

public final class Constants {
  
  public static final boolean ROBOT_FIELD_MODE = DriverStation.isFMSAttached();
  public static final boolean TUNING_MODE = true;

  public static final int JOYSTICK_LEFT_PORT = 0;
  public static final int JOYSTICK_RIGHT_PORT = 1;
  public static final int JOYSTICK_GAMEPAD_PORT = 4;

  public static final int     DRIVE_FRONT_LEFT_ID = 15;
  public static final int     DRIVE_FRONT_RIGHT_ID = 0;
  public static final int     DRIVE_REAR_LEFT_ID = 14;
  public static final int     DRIVE_REAR_RIGHT_ID = 1;
  public static final boolean DRIVE_FRONT_LEFT_INVERTED = false;
  public static final boolean DRIVE_FRONT_RIGHT_INVERTED = true;
  public static final boolean DRIVE_REAR_LEFT_INVERTED = false;
  public static final boolean DRIVE_REAR_RIGHT_INVERTED = true;
  public static final double  DRIVE_ROTATION_PID_DEADBAND = 2.0;
  public static final double  DRIVE_RATE_LIMIT = 2;
  public static final double  DRIVE_X_DEADBAND = 0.01;
  public static final double  DRIVE_Y_DEADBAND = 0.01;
  public static final double  DRIVE_ROTATION_DEADBAND = 0.01;

  public static final int       INTAKE_PIVOT_MOTOR_ID = 4;
  public static final MotorType INTAKE_PIVOT_MOTOR_TYPE = MotorType.kBrushless;
  public static final double    INTAKE_PIVOT_MOTOR_RATIO = 1 / 2;
  public static final double    INTAKE_PIVOT_PID_P = 0.12;
  public static final double    INTAKE_PIVOT_PID_I = 0.0;
  public static final double    INTAKE_PIVOT_PID_D = 0.001;
  public static final double    INTAKE_PIVOT_PID_TOLERANCE = 1;
  public static final double    INTAKE_PIVOT_PID_MAX_OUT = 0.6;
  public static final double    INTAKE_PIVOT_PID_MIN_OUT = INTAKE_PIVOT_PID_MAX_OUT * -1;
  public static enum  INTAKE_POSITIONS {
    ELEVATOR(0.0),
    HUMAN_PLAYER(45.0),
    FLOOR(135.0);

    public final double angle;
    private INTAKE_POSITIONS(double angle) {
      this.angle = angle;
    }
  }

  public static final Port SENSOR_NAVX_PORT = Port.kMXP;

}
