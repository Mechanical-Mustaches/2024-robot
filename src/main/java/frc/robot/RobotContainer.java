// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OIConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.IntakingNoteCommand;
import frc.robot.commands.AutoCommands.AutoFireNote;
import frc.robot.commands.AutoCommands.AutoFireNoteFirst;
import frc.robot.commands.AutoCommands.AutoIntakeCommand;
import frc.robot.commands.ConveyorCommands.ConveyFireCommand;
import frc.robot.commands.ConveyorCommands.ConveySpitNote;
import frc.robot.commands.FlyWheelCommands.CloseShotCommand;
import frc.robot.commands.FlyWheelCommands.FarShotCommand;
import frc.robot.commands.PositionCommands.AmpPosition;
import frc.robot.commands.PositionCommands.BasePosition;
import frc.robot.commands.PositionCommands.ClimbPosition;
import frc.robot.commands.PositionCommands.ClosePOSCommand;
import frc.robot.commands.PositionCommands.FarPOSCommand;
import frc.robot.commands.PositionCommands.HumanPosition;
import frc.robot.commands.PositionCommands.SkipPosition;
import frc.robot.commands.PositionCommands.TrapPosition;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
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
    inititalizePathPlannerCommands();

    TeleopDrive simClosedFieldRel = new TeleopDrive(
      drivebase,
       () -> m_driverController.getRawAxis(1),
       () -> m_driverController.getRawAxis(0),
       () -> m_driverController.getRawAxis(4), () -> true);

    TeleopDrive closedFieldRel = new TeleopDrive(
        drivebase,
        () -> -MathUtil.applyDeadband(m_driverController.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(m_driverController.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
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


    
    /*Gunner Controls 
      (Close Shot)  (Far Shot)  (Amp Shot)  (Fire)
      (Source)      (Skip Shot) ()          (April Track)
      (Climb)       (Climb Alt) (Intake)    (Intake Track)
    */

      //Close Shot:
      m_coDriverController.button(1).whileTrue(new ParallelCommandGroup(
        new CloseShotCommand(flyWheel),
        new ClosePOSCommand(pivot, elevator))
      );

      //Source
      m_coDriverController.button(2).whileTrue(new HumanPosition(pivot, elevator, flyWheel, conveyor));
      m_coDriverController.button(2).onFalse(new BasePosition(pivot, elevator));

      //Climb
      m_coDriverController.button(3).whileTrue(new ClimbPosition(pivot, elevator));
      m_coDriverController.button(3).onFalse(new BasePosition(pivot, elevator));

      //Far Shot
      m_coDriverController.button(4).whileTrue(new ParallelCommandGroup(
        new FarShotCommand(flyWheel),
        new FarPOSCommand(pivot, elevator))
      );
     
      //Skip Shot
      m_coDriverController.button(5).whileTrue(new SkipPosition(pivot, elevator, flyWheel));
      m_coDriverController.button(5).onFalse(new BasePosition(pivot, elevator));
  
      //Trap Score
      m_coDriverController.button(6).whileTrue(new TrapPosition(pivot, elevator));
      m_coDriverController.button(6).onFalse(new BasePosition(pivot, elevator));
      
      //Amp Shot
      m_coDriverController.button(7).whileTrue(new AmpPosition(pivot, elevator, flyWheel));
      m_coDriverController.button(7).onFalse(new BasePosition(pivot, elevator));

      //Reserved for Future Implementation
      m_coDriverController.button(8).whileTrue(new ConveySpitNote(conveyor));

      //Intake (no limelight)
      m_coDriverController.button(9).whileTrue(new IntakingNoteCommand(floorIntake, conveyor, elevator, pivot, flyWheel));
      
      //Fire
      m_coDriverController.button(10).whileTrue(new ConveyFireCommand(conveyor));

      //Track April (back limelight) 
      m_coDriverController.button(11).whileTrue(new RepeatCommand(new AprilTrack(
      drivebase,
        () -> -MathUtil.applyDeadband(m_driverController.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(m_driverController.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
        () -> -m_driverController.getRawAxis(4)
        )));

      //Track Note (front limelight)
      m_coDriverController.button(12).whileTrue(new RepeatCommand(new NoteTrack(
      drivebase,
        () -> -MathUtil.applyDeadband(m_driverController.getRawAxis(1), OperatorConstants.LEFT_Y_DEADBAND),
        () -> -MathUtil.applyDeadband(m_driverController.getRawAxis(0), OperatorConstants.LEFT_X_DEADBAND),
        () -> -m_driverController.getRawAxis(4)
        )));
      m_coDriverController.button(12).whileTrue(new IntakingNoteCommand(floorIntake, conveyor, elevator, pivot, flyWheel));
    
  }

  public void initialize(){
    new BasePosition(pivot, elevator);
    flyWheel.rampDown();
  }

    // Register Named Commands (Pathplanner)
  private void inititalizePathPlannerCommands() {
    NamedCommands.registerCommand("Fire", new AutoFireNote(conveyor, flyWheel));
    NamedCommands.registerCommand("FireFirst", new AutoFireNoteFirst(conveyor, flyWheel));
    NamedCommands.registerCommand("Intake", new AutoIntakeCommand(floorIntake, conveyor, elevator, pivot, flyWheel));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  private final SendableChooser<String> autoChooser = new SendableChooser<String>();
  
  private String initializeAutoChooser() {
    autoChooser.setDefaultOption("4 Peice Close Auto", "auto4C");
    autoChooser.addOption("3 Peice Close Amp Side", "Auto3C-A");
    autoChooser.addOption("3 Peice Close Source Side", "Auto3C-S");
    autoChooser.addOption("3 Peice Bumrush Source Side", "Auto3F-S");
    autoChooser.addOption("3 Peice Bumrush Amp Side", "Auto3F-A");
    autoChooser.addOption("test PID", "test");
    SmartDashboard.putData("Auto Selector", autoChooser);
    return autoChooser.getSelected();
  }

  public Command getAutonomousCommand()
  {
    return drivebase.getAutonomousCommand(initializeAutoChooser());
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
