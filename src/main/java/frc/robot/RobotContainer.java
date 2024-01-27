// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArmCommands.ArmPositions;
import frc.robot.commands.ConveyorCommands.ConveyInwardCommand;
import frc.robot.commands.FloorIntakeCommands.FI_IntakeForward;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{

  // The robot's subsystems and commands are defined here...
  private final FloorIntakeSubsystem floorIntake = new FloorIntakeSubsystem();
  private final ArmSubsystem arm = new ArmSubsystem();
  private final ConveyorSubsystem conveyor = new ConveyorSubsystem();


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OIConstants.kDriverControllerPort);
  
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                       "swerve/neo"));
  

  

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer()
  {
    // Configure the trigger bindings
    configureBindings();
    initializeAutoChooser();

    TeleopDrive simClosedFieldRel = new TeleopDrive(
      drivebase,
       () -> m_driverController.getRawAxis(1),
       () -> m_driverController.getRawAxis(0),
       () -> m_driverController.getRawAxis(4), () -> true);

    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> -m_driverController.getRawAxis(1),
        () -> -m_driverController.getRawAxis(0),
        () -> -m_driverController.getRawAxis(4), () -> true);

    drivebase.setDefaultCommand(!RobotBase.isSimulation() ? closedFieldRel : simClosedFieldRel);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  
  private void configureBindings() {
   
    m_driverController.x().onTrue((new InstantCommand(drivebase::zeroGyro)));
    m_driverController.y().whileTrue(new ConveyInwardCommand(conveyor));
    m_driverController.a().whileTrue(new FI_IntakeForward(floorIntake));
   
    m_driverController.button(6).whileTrue(new FI_IntakeForward(floorIntake));
    m_driverController.b().debounce(.1).onTrue(new ArmPositions(arm));
    m_driverController.button(4).onTrue((new InstantCommand(drivebase::zeroGyro)));

  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

     private final SendableChooser<String> autoChooser = new SendableChooser<String>();
  
  private String initializeAutoChooser() {
    autoChooser.setDefaultOption("New Path", "New Path");
    autoChooser.addOption("path2", "SamplePath");
    SmartDashboard.putData("Auto Selector", autoChooser);
    return autoChooser.getSelected();
  }

  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return drivebase.getAutonomousCommand(initializeAutoChooser(), true);
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
