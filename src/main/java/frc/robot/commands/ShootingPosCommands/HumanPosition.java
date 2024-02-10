package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ConveyorCommands.ConveyHumanPlayer;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class HumanPosition extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
    private final FlyWheelSubsystem flywheel;
    private final ConveyorSubsystem conveyor;

    public HumanPosition(PivotSubsystem pivot, ElevatorSubsystem elevator, FlyWheelSubsystem flywheel, ConveyorSubsystem conveyor){
        this.pivot = pivot;
        this.elevator = elevator;
        this.flywheel = flywheel;
        this.conveyor = conveyor;
    }

    @Override 
    public void initialize(){
        pivot.pivotHumanPosition();
        elevator.humanPosition();
        flywheel.sourceNomNom();
        conveyor.conveyFromSource();
    }

    @Override
    public void execute(){
        if(!flywheel.isNoteSeen()){
            conveyor.conveyFromSource();
        }
        else{
            conveyor.stopConvey();
        }
    }

    @Override
    public boolean isFinished(){
        if(flywheel.isNoteSeen()){
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
        flywheel.rampDown();
    }

}
