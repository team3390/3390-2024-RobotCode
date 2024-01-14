/**
 * @author team3390, canokan
 */

package com.team3390.robot;

import com.team3390.robot.commands.drive.FieldOrientedDrive;
import com.team3390.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class RobotContainer {

  private final Drivetrain drivetrain = Drivetrain.getInstance();

  private final Joystick leftStick = new Joystick(Constants.JOYSTICK_LEFT_PORT);
  private final Joystick rightStick = new Joystick(Constants.JOYSTICK_RIGHT_PORT);
  // private final Joystick gamepad = new Joystick(Constants.JOYSTICK_GAMEPAD_PORT);

  public RobotContainer() {
    drivetrain.setDefaultCommand(new FieldOrientedDrive(
      drivetrain, 
      () -> rightStick.getX(),
      () -> rightStick.getY(),
      () -> leftStick.getX()
    ));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
