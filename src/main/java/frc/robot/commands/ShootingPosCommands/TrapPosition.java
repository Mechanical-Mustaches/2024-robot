package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class TrapPosition extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;

    public TrapPosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;

    }

    @Override
    public void execute(){
        pivot.pivotTrapPosition();
        elevator.trapPosition();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
    
}
