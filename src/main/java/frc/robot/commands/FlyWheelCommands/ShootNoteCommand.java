package frc.robot.commands.FlyWheelCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ShootNoteCommand extends Command {
    private final FlyWheelSubsystem flyWheel;
    private boolean isRampedUp = false;

    public ShootNoteCommand(FlyWheelSubsystem flyWheel){
        this.flyWheel = flyWheel;
    }

    @Override
    public void execute(){
        flyWheel.rampUp();
        isRampedUp = true;
    }

    @Override
    public boolean isFinished(){
        return isRampedUp;
    }

    @Override
    public void end(boolean i){
        flyWheel.rampDown();
    }

    
}
