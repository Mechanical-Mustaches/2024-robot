package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.commands.ConveyorCommands.ConveyLineBreak;
import frc.robot.commands.FloorIntakeCommands.FI_IntakeForward;
import frc.robot.commands.FloorIntakeCommands.FI_LinebreakCommand;
import frc.robot.commands.ShootingPosCommands.BasePosition;

public class IntakingNoteCommand extends SequentialCommandGroup{

    public IntakingNoteCommand(FloorIntakeSubsystem floorIntake, ConveyorSubsystem conveyor,
        ElevatorSubsystem elevator, PivotSubsystem pivot, FlyWheelSubsystem flywheel){
        
        addCommands(
                (new BasePosition(pivot, elevator)),   
                (new FI_LinebreakCommand(floorIntake)),
                new ParallelCommandGroup(
                    (new ConveyLineBreak(conveyor, flywheel)),
                    (new FI_IntakeForward(floorIntake))
                )
                
        );
       
    }

    
}
