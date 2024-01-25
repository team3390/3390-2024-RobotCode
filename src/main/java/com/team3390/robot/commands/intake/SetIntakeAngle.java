package com.team3390.robot.commands.intake;

import com.team3390.robot.Constants.INTAKE_POSITIONS;
import com.team3390.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class SetIntakeAngle extends Command {

  private final IntakeSubsystem intakeSubsystem;
  private final INTAKE_POSITIONS pos;

  public SetIntakeAngle(IntakeSubsystem intakeSubsystem, INTAKE_POSITIONS pos) {
    this.intakeSubsystem = intakeSubsystem;
    this.pos = pos;
    addRequirements(intakeSubsystem);
  }

  @Override
  public void initialize() {
    intakeSubsystem.setIntakeAngle(pos);
  }

  @Override
  public boolean isFinished() {
    return intakeSubsystem.isIntakeAtSetpoint();
  }

}
