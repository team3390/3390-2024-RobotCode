package com.team3390.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C.Port;

public final class Constants {
  
  public static final boolean ROBOT_FIELD_MODE = DriverStation.isFMSAttached();
  public static final boolean ROBOT_ENABLED = DriverStation.isEnabled();
  public static final boolean TUNING_MODE = true;

  public static final int JOYSTICK_LEFT_PORT = 0;
  public static final int JOYSTICK_RIGHT_PORT = 1;
  public static final int JOYSTICK_GAMEPAD_PORT = 4;

  public static final int     DRIVE_FRONT_LEFT_ID = 4;//2 numaralı talon problemli
  public static final int     DRIVE_FRONT_RIGHT_ID = 15;
  public static final int     DRIVE_REAR_LEFT_ID = 3;
  public static final int     DRIVE_REAR_RIGHT_ID = 14;
  public static final boolean DRIVE_FRONT_LEFT_INVERTED = false;
  public static final boolean DRIVE_FRONT_RIGHT_INVERTED = true;
  public static final boolean DRIVE_REAR_LEFT_INVERTED = false;
  public static final boolean DRIVE_REAR_RIGHT_INVERTED = true;
  public static final double  DRIVE_RATE_LIMIT = 2.5;
  public static final double  DRIVE_RATE_X_LIMIT = 4;
  public static final double DRIVE_RATE_Y_LIMIT = 4;
  public static final double  DRIVE_X_DEADBAND = 0.01;
  public static final double  DRIVE_Y_DEADBAND = 0.01;
  public static final double  DRIVE_ROTATION_DEADBAND = 0.01;
  public static final double  DRIVE_ROTATION_PID_P = 0.01;
  public static final double  DRIVE_ROTATION_PID_I = 0;
  public static final double  DRIVE_ROTATION_PID_D = 0.00007;
  public static final double  DRIVE_ROTATION_PID_TOLERANCE = 0.5;
  public static final double  DRIVE_ROTATION_PID_MAX_OUT = 0.5;
  public static final double  DRIVE_ROTATION_PID_MIN_OUT = -0.5;

  public static final int    INTAKE_PIVOT_MOTOR_ID = 9;
  public static final int    INTAKE_PIVOT_MOTOR_SLAVE_ID = 8;
  public static final double INTAKE_PIVOT_MOTOR_RATIO = 1 / 2;
  public static final double INTAKE_PIVOT_PID_P = 0.0;
  public static final double INTAKE_PIVOT_PID_I = 0.0;
  public static final double INTAKE_PIVOT_PID_D = 0.0;
  public static final double INTAKE_PIVOT_PID_TOLERANCE = 0;
  public static final double INTAKE_PIVOT_PID_MAX_OUT = 1;
  public static final double INTAKE_PIVOT_PID_MIN_OUT = INTAKE_PIVOT_PID_MAX_OUT * -1;
  public static final int    INTAKE_SWITCH_ID = 0;
  public static final int    INTAKE_HAND_MOTOR_ID = 5;
  public static enum  INTAKE_POSITIONS {
    ELEVATOR(0.0),
    HUMAN_PLAYER(45.0),
    FLOOR(135.0);

    public final double angle;
    private INTAKE_POSITIONS(double angle) {
      this.angle = angle;
    }
  }

  public static final int ELEVATOR_MOTOR_MASTER_ID = 12;
  public static final int ELEVATOR_MOTOR_SLAVE_ID = 13;
  public static final double ELEVATOR_UP_SPEED = 1;
  public static final double ELEVATOR_DOWN_SPEED = -1;

  public static final int    SHOOTER_PIVOT_MOTOR_MASTER_ID = 11;
  public static final int    SHOOTER_PIVOT_MOTOR_SLAVE_ID = 10;
  public static final int    SHOOTER_SHOT_MOTOR_MASTER_ID = 6;
  public static final int    SHOOTER_SHOT_MOTOR_SLAVE_ID = 7;
  public static final int    SHOOTER_FEEDER_MOTOR_ID = 0;
  public static final double SHOOTER_PIVOT_PID_P = 0.0;
  public static final double SHOOTER_PIVOT_PID_I = 0.0;
  public static final double SHOOTER_PIVOT_PID_D = 0.0;
  public static final double SHOOTER_PIVOT_PID_TOLERANCE = 0;
  public static final double SHOOTER_PIVOT_PID_MAX_OUT = 1;
  public static final double SHOOTER_PIVOT_PID_MIN_OUT = INTAKE_PIVOT_PID_MAX_OUT * -1;
  public static final double SHOOTER_PIVOT_DEADBAND = 0.09;
  public static final double SHOOTER_SHOOT_MOTOR_ACCELERATION = 1;
  public static final double SHOOTER_FEED_IN_SPEED = 1;
  public static final double SHOOTER_FEED_OUT_SPEEED = -0.4;
  public static final double SHOOTER_SHOOT_SPEED = 1;

  public static enum  SHOOTER_POSITIONS {
    INTAKE, SPEAKER, AMP
  }

  public static final double LIMELIGHT_SHOOTER_SPEED_COEFFICIENT = 2.0;
  public static enum LIMELIGHT_LIGHT_MODE {
    PIPELINE_VALUE, OFF, BLINK, ON
  }
  public static enum LIMELIGHT_CAMERA_MODE {
    VISION, DRIVE
  }

  public static final Port SENSOR_NAVX_PORT = Port.kMXP;

}
