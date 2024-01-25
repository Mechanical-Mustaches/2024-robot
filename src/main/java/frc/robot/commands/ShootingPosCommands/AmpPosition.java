package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class AmpPosition extends Command{
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
   

    public AmpPosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;

    }

    @Override
    public void execute(){
        pivot.pivotAmpPosition();
        elevator.ampPosition();
    }

    
    
}
