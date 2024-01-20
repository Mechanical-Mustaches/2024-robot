package frc.robot.commands.ArmCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmPositions extends Command {
    ArmSubsystem arm;

    public ArmPositions(ArmSubsystem arm){
        this.arm = arm;
    }

    @Override
    public void initialize(){
        arm.togglePosition();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

   
    
}
