/**
 * @author team3390, canokan
 */

package com.team3390.robot;

import com.team3390.robot.commands.auto.Red_3NoteAuto;
import com.team3390.robot.commands.drive.MecanumDrive;
import com.team3390.robot.commands.drive.RotateToAngle;
import com.team3390.robot.subsystems.Drivetrain;
import com.team3390.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class RobotContainer {

  private final Drivetrain drivetrain = Drivetrain.getInstance();
  private final IntakeSubsystem intakeSubsystem = IntakeSubsystem.getInstance();
  
  private final Joystick gamepad = new Joystick(Constants.JOYSTICK_GAMEPAD_PORT);

  public RobotContainer() {
    drivetrain.setDefaultCommand(new MecanumDrive(
      drivetrain, 
      () -> -gamepad.getRawAxis(1),
      () -> gamepad.getRawAxis(2),
      () -> (gamepad.getRawAxis(0) / 3),
      false
    ));

    new Trigger(() -> gamepad.getRawButton(1)).whileTrue(new RotateToAngle(drivetrain, 90));
  }

  public Command getAutonomousCommand() {
    return new Red_3NoteAuto(drivetrain);
  }
}
