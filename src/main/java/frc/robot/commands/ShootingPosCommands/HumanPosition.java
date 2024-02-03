package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class HumanPosition extends Command {
    PivotSubsystem pivot;
    ElevatorSubsystem elevator;

    public HumanPosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override 
    public void initialize(){
        pivot.pivotHumanPosition();
        elevator.humanPosition();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}
