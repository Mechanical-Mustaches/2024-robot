package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import frc.robot.commands.PositionCommands.LiveUp;
import frc.robot.commands.swervedrive.drivebase.liveShot;

public class SmartShot extends SequentialCommandGroup{

    public SmartShot(FloorIntakeSubsystem floorIntake, SwerveSubsystem drivebase, ElevatorSubsystem elevator,
        FlyWheelSubsystem flyWheel, PivotSubsystem pivot, DoubleSupplier vX, DoubleSupplier vY){
        
        addCommands(
                (new LiveUp(pivot, elevator)),
                (new RepeatCommand(new liveShot(drivebase, pivot, flyWheel, vX, vY)))
        );
       
    }

    

    
}
