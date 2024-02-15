package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;

public class AutoFireNote extends Command{
    ConveyorSubsystem convey;
    FlyWheelSubsystem flywheel;
    private boolean hasNote;
    private boolean isFlyFinished;

    public AutoFireNote(ConveyorSubsystem convey, FlyWheelSubsystem flywheel){
        this.convey = convey;
        this.flywheel = flywheel;
    }

    @Override
    public void initialize(){
        convey.conveyInward();
        hasNote = true;
        isFlyFinished = false;
    }

    @Override
    public void execute(){
        if(flywheel.isNoteSeen() && hasNote){
            convey.conveyInward();
            hasNote = false;
        }
        else if(!flywheel.isNoteSeen() && hasNote){
            convey.conveyInward();
            if(flywheel.isNoteSeen()){
                hasNote = true;
            }
            else{
                hasNote = false;
            }
        }
        
        if(!hasNote){
            if(!flywheel.isNoteSeen()){
                isFlyFinished = true;
            }
        }
    }

    @Override
    public boolean isFinished(){
        return isFlyFinished;
    }
    
    @Override
    public void end(boolean i){
        convey.stopConvey();
    }

}
