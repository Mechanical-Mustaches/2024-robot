// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.commands.IntakingNoteCommand;
import frc.robot.commands.ConveyorCommands.ConveyInwardCommand;
import frc.robot.commands.FloorIntakeCommands.FI_IntakeForward;
import frc.robot.commands.ShootingPosCommands.AmpPosition;
import frc.robot.commands.ShootingPosCommands.BasePosition;
import frc.robot.commands.ShootingPosCommands.HumanPosition;
import frc.robot.commands.ShootingPosCommands.PodiumPosition;
import frc.robot.commands.ShootingPosCommands.subWOOFCommand;
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
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.swervedrive.drivebase.TeleopDrive;
import frc.robot.commands.swervedrive.drivebase.AprilTrack;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.commands.swervedrive.drivebase.NoteTrack; 
import java.io.File;

import com.pathplanner.lib.auto.NamedCommands;


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
// Register Named Commands
      NamedCommands.registerCommand("spin", new FI_IntakeForward(floorIntake));
      NamedCommands.registerCommand("stop", new FI_IntakeForward(floorIntake));
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
    m_driverController.button(5).onTrue((new InstantCommand(drivebase::lock)));
   // m_driverController.button(6).whileTrue(new FI_IntakeForward(floorIntake));
    
  

    //Gunner Controls 
      //Close Shot:
      m_coDriverController.button(1).onTrue(new subWOOFCommand(pivot, elevator, flyWheel));
      m_coDriverController.button(1).onFalse(new BasePosition(pivot, elevator));

      //Source:
      m_coDriverController.button(2).whileTrue(new HumanPosition(pivot, elevator, flyWheel, conveyor));
      m_coDriverController.button(2).onFalse(new BasePosition(pivot, elevator));

      //Climb:
      m_coDriverController.button(3);

      //Far Shot:
      m_coDriverController.button(4).onTrue(new PodiumPosition(pivot, elevator, flyWheel));
      m_coDriverController.button(4).onFalse(new BasePosition(pivot, elevator));

      //Reserved for Future Implementation
      m_coDriverController.button(5);
  
      //Reserved for Future Implementation
      m_coDriverController.button(6);
      
      //Amp Shot
      m_coDriverController.button(7).onTrue(new AmpPosition(pivot, elevator, flyWheel));
      m_coDriverController.button(7).onFalse(new BasePosition(pivot, elevator));

      //Reserved for Future Implementation
      m_coDriverController.button(8);

      //Intake (no limelight)
      m_coDriverController.button(9).whileTrue(new IntakingNoteCommand(floorIntake, conveyor, elevator, pivot, flyWheel));
      
      //Fire
      m_coDriverController.button(10).whileTrue(new ConveyInwardCommand(conveyor));

      //Track April (back limelight)
      m_coDriverController.button(11).whileTrue(new RepeatCommand(new AprilTrack(
      drivebase,
        () -> -m_driverController.getRawAxis(1),
        () -> -m_driverController.getRawAxis(0),
        () -> -m_driverController.getRawAxis(4))
        ));

      //Track Note (front limelight)
      m_coDriverController.button(12).whileTrue(new RepeatCommand(new NoteTrack(
      drivebase,
        () -> -m_driverController.getRawAxis(1),
        () -> -m_driverController.getRawAxis(0),
        () -> -m_driverController.getRawAxis(4))
        ));
    
  }

  public void initialize(){
    new BasePosition(pivot, elevator);
    flyWheel.rampDown();
  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

     private final SendableChooser<String> autoChooser = new SendableChooser<String>();
  
  private String initializeAutoChooser() {
    autoChooser.setDefaultOption("path1", "path1");
    autoChooser.addOption("path2", "path2");
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
