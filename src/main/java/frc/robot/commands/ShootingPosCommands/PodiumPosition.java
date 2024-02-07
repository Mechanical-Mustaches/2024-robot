package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class PodiumPosition extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
    private final FlyWheelSubsystem flywheel;

    public PodiumPosition(PivotSubsystem pivot, ElevatorSubsystem elevator, FlyWheelSubsystem flywheel){
        this.pivot = pivot;
        this.elevator = elevator;
        this.flywheel = flywheel;
    }

    @Override
    public void execute(){
        pivot.pivotPodiumPosition();
        elevator.podiumPosition();
        flywheel.podiumShot();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

    @Override
    public void end(boolean i){
        pivot.pivotBasePosition();
        elevator.basePosition();
        flywheel.rampDown();
    }
}
