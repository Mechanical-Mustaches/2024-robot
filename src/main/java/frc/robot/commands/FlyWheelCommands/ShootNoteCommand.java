package frc.robot.commands.FlyWheelCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ShootNoteCommand extends Command {
    FlyWheelSubsystem flyWheel;

    public ShootNoteCommand(FlyWheelSubsystem flyWheel){
        this.flyWheel = flyWheel;
    }

    @Override
    public void execute(){
        flyWheel.rampUp();
    }

    @Override
    public void end(boolean i){
        flyWheel.rampDown();
    }

    
}
