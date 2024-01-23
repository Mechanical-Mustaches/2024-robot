package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyInwardCommand extends Command{
    ConveyorSubsystem conveyor; 

    public ConveyInwardCommand(ConveyorSubsystem conveyor){
        this.conveyor = conveyor;
    }

    @Override
    public void execute(){
        conveyor.conveyInward();
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
    }
    

}
