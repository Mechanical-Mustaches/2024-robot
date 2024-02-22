package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BlinkinSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ConveyorMoveBack extends Command {
    ConveyorSubsystem conveyor;
    FlyWheelSubsystem flywheel;
    BlinkinSubsystem blinkin;

    public ConveyorMoveBack(ConveyorSubsystem conveyor, FlyWheelSubsystem flywheel, BlinkinSubsystem blinkin){
        this.conveyor = conveyor;
        this.flywheel = flywheel;
        this.blinkin = blinkin;
    }

    @Override
    public void initialize(){
        conveyor.conveyMoveBack();
        blinkin.setGreen();
    }

    @Override
    public boolean isFinished(){
        return !flywheel.isNoteSeen();
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
        blinkin.setGreen();
    }


    
}
