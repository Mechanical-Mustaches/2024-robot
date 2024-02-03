package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ConveyLineBreak extends Command{
    ConveyorSubsystem conveyor;
    FlyWheelSubsystem flywheel;

    public ConveyLineBreak(ConveyorSubsystem conveyor, FlyWheelSubsystem flywheel){
        this.conveyor = conveyor;
        this.flywheel = flywheel;
    }

    @Override
    public void initialize(){
      conveyor.conveyInward();   
    }

    @Override
    public boolean isFinished(){
        return flywheel.isNoteSeen();
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
    }
    
}
