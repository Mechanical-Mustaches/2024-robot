package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class DefensePosition extends Command{
    PivotSubsystem pivot;
    ElevatorSubsystem elevator;

    public DefensePosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override
    public void execute(){
        elevator.defensePosition();
    }
    
    
}
