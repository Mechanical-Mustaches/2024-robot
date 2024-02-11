package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyInwardCommand extends Command{
    ConveyorSubsystem conveyor; 
    private boolean isConveying = false;


    public ConveyInwardCommand(ConveyorSubsystem conveyor){
        this.conveyor = conveyor;
    }

    @Override
    public void execute(){
        conveyor.conveyInward();
        isConveying = true;
    }

    @Override
    public boolean isFinished(){
        return isConveying;
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
    }
    

}
