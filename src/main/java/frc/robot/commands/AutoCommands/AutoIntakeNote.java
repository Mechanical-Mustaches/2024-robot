package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class AutoIntakeNote extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
    private final ConveyorSubsystem conveyor;
    private final FlyWheelSubsystem flywheel;
    private final FloorIntakeSubsystem intake;
    private boolean hasNote;

    public AutoIntakeNote(PivotSubsystem pivot, ElevatorSubsystem elevator, ConveyorSubsystem conveyor,
    FlyWheelSubsystem flywheel, FloorIntakeSubsystem intake){
        this.pivot = pivot;
        this.elevator = elevator;
        this.conveyor = conveyor;
        this.flywheel = flywheel;
        this.intake = intake;
    }

    @Override
    public void initialize(){
        pivot.pivotBasePosition();
        elevator.basePosition();
        hasNote = false;
    }

    @Override
    public void execute(){
        intake.intakeForward();
        conveyor.conveyInward();

        if(flywheel.isNoteSeen()){
            conveyor.stopConvey();
            intake.intakeStop();
            conveyor.conveyMoveBack();

            if(!flywheel.isNoteSeen()){
                hasNote = true;
            }
        }

        if(hasNote){
            conveyor.stopConvey();
            intake.intakeStop();
        }

    }

    @Override
    public boolean isFinished(){
        return hasNote;
    }
}
