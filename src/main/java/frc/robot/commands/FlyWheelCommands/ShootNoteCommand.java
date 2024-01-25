package frc.robot.commands.FlyWheelCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ShootNoteCommand extends Command {
    private final FlyWheelSubsystem flyWheel;

    public ShootNoteCommand(FlyWheelSubsystem flyWheel){
        this.flyWheel = flyWheel;
    }

    @Override
    public void execute(){
        flyWheel.rampUp();
    }

    public boolean isFinsihed(){
        flyWheel.rampDown();
        return true;
    }

    
}
