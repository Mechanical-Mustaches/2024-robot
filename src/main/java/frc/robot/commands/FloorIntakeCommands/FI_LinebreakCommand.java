package frc.robot.commands.FloorIntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FloorIntakeSubsystem;

public class FI_LinebreakCommand extends Command{
    FloorIntakeSubsystem floorIntake;

    public FI_LinebreakCommand(FloorIntakeSubsystem floorIntake){
        this.floorIntake = floorIntake;
    }

    @Override
    public void initialize(){
        floorIntake.intakeForward();
    }

    @Override
    public boolean isFinished(){
        return floorIntake.isNotePresent();
    }

    @Override
    public void end(boolean i){
        floorIntake.intakeStop();
    }

    
}
