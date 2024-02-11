package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase{
    PivotSubsystem pivot;
    ElevatorSubsystem elevator;

    public ClimbSubsystem(){

    }

    public void climb(){
        pivot.pivotAmpPosition();
        elevator.ampPosition();
    }
      
    
}
