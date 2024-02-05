package frc.robot.commands.ShootingPosCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class subWOOFCommand extends Command {
    PivotSubsystem pivot;
    ElevatorSubsystem elevator;
    FlyWheelSubsystem flywheel;

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
    }

    @Override
    public boolean isFinished(){
        return true;
    }



}
