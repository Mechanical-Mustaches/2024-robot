package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class BasePosition extends Command{
    PivotSubsystem pivot;
    ElevatorSubsystem elevator;


    public BasePosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override
    public void execute(){
        pivot.pivotBasePosition();
        elevator.basePosition();
    }

    @Override
    public void end(boolean i){
        
    }
    
}
