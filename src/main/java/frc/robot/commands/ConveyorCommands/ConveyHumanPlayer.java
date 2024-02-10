package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ConveyHumanPlayer extends Command {
    FlyWheelSubsystem flywheel;
    ConveyorSubsystem conveyor;

    public ConveyHumanPlayer(FlyWheelSubsystem flywheel, ConveyorSubsystem conveyor){
        this.flywheel = flywheel;
        this.conveyor = conveyor;
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
