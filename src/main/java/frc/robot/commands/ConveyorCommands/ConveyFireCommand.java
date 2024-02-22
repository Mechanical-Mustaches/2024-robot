package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BlinkinSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyFireCommand extends Command{
    ConveyorSubsystem conveyor; 
    BlinkinSubsystem blinkin;
  
    public ConveyFireCommand(ConveyorSubsystem conveyor, BlinkinSubsystem blinkin){
        this.conveyor = conveyor;
        this.blinkin = blinkin;
    }

    @Override
    public void initialize(){
        conveyor.conveyInward();
        blinkin.setPurple();
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
        blinkin.setWhite();
    }
    

}
