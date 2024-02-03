// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArmCommands.ArmPositions;
import frc.robot.commands.ConveyorCommands.ConveyInwardCommand;
import frc.robot.commands.ConveyorCommands.ConveyLineBreak;
import frc.robot.commands.FloorIntakeCommands.FI_IntakeForward;
import frc.robot.commands.FlyWheelCommands.ShootNoteCommand;
import frc.robot.commands.ShootingPosCommands.AmpPosition;
import frc.robot.commands.ShootingPosCommands.BasePosition;
import frc.robot.commands.ShootingPosCommands.PodiumPosition;
import frc.robot.commands.ShootingPosCommands.TrapPosition;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.commands.swervedrive.drivebase.Lime; 
import java.io.File;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

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
  private final FlyWheelSubsystem flyWheel = new FlyWheelSubsystem();
  private final PivotSubsystem pivot = new PivotSubsystem();
  private final ElevatorSubsystem elevator = new ElevatorSubsystem();



  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OIConstants.kDriverControllerPort);

  private final CommandXboxController m_coDriverController =
      new CommandXboxController(OIConstants.kCoDriverControllerPort);
  
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
   

    //Driver Controls   
    m_driverController.button(4).onTrue((new InstantCommand(drivebase::zeroGyro)));
    m_driverController.button(6).whileTrue(new FI_IntakeForward(floorIntake));
    m_driverController.button(8).whileTrue(new RepeatCommand(new Lime(
      drivebase,
        () -> -m_driverController.getRawAxis(1),
        () -> -m_driverController.getRawAxis(0),
        () -> -m_driverController.getRawAxis(4))
        ));

    //Gunner Controls 
    //Human Player position code
      m_coDriverController.button(1).onTrue(new AmpPosition(pivot, elevator));
      m_coDriverController.button(1).onFalse(new BasePosition(pivot, elevator));

      m_coDriverController.button(2).onTrue(new TrapPosition(pivot, elevator));
      m_coDriverController.button(2).onFalse(new BasePosition(pivot, elevator));

      m_coDriverController.button(3).onTrue(new PodiumPosition(pivot, elevator));
      m_coDriverController.button(3).onFalse(new BasePosition(pivot, elevator));

      m_coDriverController.button(4).debounce(0.1).whileTrue(new ShootNoteCommand(flyWheel));

      m_coDriverController.button(5).whileTrue(new ConveyInwardCommand(conveyor));



    
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
