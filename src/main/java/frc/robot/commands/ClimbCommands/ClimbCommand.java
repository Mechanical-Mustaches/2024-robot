package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends Command {
    ClimbSubsystem climb;

    public ClimbCommand(ClimbSubsystem climb){
        this.climb = climb;
    }
    
    @Override
    public void initialize(){
        climb.climb();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
