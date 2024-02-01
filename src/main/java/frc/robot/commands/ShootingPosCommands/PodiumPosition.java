package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class PodiumPosition extends Command {
    PivotSubsystem pivot;
    ElevatorSubsystem elevator;

    public PodiumPosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override
    public void execute(){
        pivot.pivotPodiumPosition();
        elevator.podiumPosition();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
