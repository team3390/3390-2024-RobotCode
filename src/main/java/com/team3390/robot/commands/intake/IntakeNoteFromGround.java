package com.team3390.robot.commands.intake;

import com.team3390.robot.Constants.INTAKE_POSITIONS;
import com.team3390.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class IntakeNoteFromGround extends SequentialCommandGroup {
  public IntakeNoteFromGround(IntakeSubsystem intakeSubsystem) {
    addCommands(
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.FLOOR),
      new IntakeNote(intakeSubsystem),
      new SetIntakeAngle(intakeSubsystem, INTAKE_POSITIONS.HUMAN_PLAYER)
    );
  }
}
