package com.team3390.robot.commands.intake;

import com.team3390.robot.Constants.INTAKE_POSITIONS;
import com.team3390.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SetIntakeAngle extends InstantCommand {

  public SetIntakeAngle(IntakeSubsystem intakeSubsystem, INTAKE_POSITIONS pos) {
    super(() -> intakeSubsystem.setIntakeAngle(pos), intakeSubsystem);
  }

}
