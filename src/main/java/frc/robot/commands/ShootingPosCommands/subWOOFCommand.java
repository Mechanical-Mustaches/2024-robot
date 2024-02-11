package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class subWOOFCommand extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;
    private final FlyWheelSubsystem flywheel;
    private boolean isAtSubWoof = false;

    public subWOOFCommand(PivotSubsystem pivot, ElevatorSubsystem elevator, FlyWheelSubsystem flywheel){
        this.pivot = pivot;
        this.elevator = elevator;
        this.flywheel = flywheel;

    }

    @Override
    public void initialize(){
        pivot.pivotSubWooferPosition();
        elevator.subWooferPosition();
        flywheel.subWoofShot();
        isAtSubWoof = true;
    }

    @Override
    public boolean isFinished(){
        return isAtSubWoof;
    }



}
