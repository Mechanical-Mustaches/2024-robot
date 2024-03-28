package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class testPosition extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
    private FlyWheelSubsystem flywheel;

    public testPosition(FlyWheelSubsystem flyWheel, PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
        this.flywheel = flyWheel;
    }

    @Override
    public void initialize(){
        pivot.pivotTestPosition();
        elevator.basePosition();
        flywheel.farShot();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    
    @Override
    public void end(boolean i){
        pivot.pivotBasePosition();
        flywheel.rampDown();
    }
}