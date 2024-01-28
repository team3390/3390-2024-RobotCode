package com.team3390.robot.utility;

import com.team3390.robot.Constants;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class CompetitionShuffleboard {

  public ShuffleboardTab tab;

  private static CompetitionShuffleboard instance;

  // Intake
  public final GenericEntry hasNoteEntry;

  // Limelight
  public final GenericEntry limelightXAtSetpointEntry;
  public final GenericEntry limelightYAtSetpointEntry;

  public static synchronized CompetitionShuffleboard getInstance() {
    if (instance == null) {
      instance = new CompetitionShuffleboard();
    }
    return instance;
  }

  public CompetitionShuffleboard() {
    tab = Shuffleboard.getTab("Control Panel");
    if (Constants.ROBOT_FIELD_MODE) {
      Shuffleboard.selectTab("Control Panel");
    }

    hasNoteEntry = tab.add("Intake Has Note", false).getEntry();

    limelightXAtSetpointEntry = tab.add("LM X At Setpoint", false).getEntry();
    limelightYAtSetpointEntry = tab.add("LM Y At Setpoint", false).getEntry();
  }

}
