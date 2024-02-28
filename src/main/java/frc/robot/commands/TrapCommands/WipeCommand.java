package frc.robot.commands.TrapCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TrapSubsystem;

public class WipeCommand extends Command{
    TrapSubsystem trap;

    public WipeCommand(TrapSubsystem trap){
        this.trap = trap;
    }

    @Override
    public void initialize(){ 
        trap.wipe();
    }

   
    
}
