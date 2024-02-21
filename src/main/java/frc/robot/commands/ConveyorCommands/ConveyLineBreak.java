package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.BlinkinSubsystem;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ConveyLineBreak extends Command{
    ConveyorSubsystem conveyor;
    FlyWheelSubsystem flywheel;
    FloorIntakeSubsystem intake;
    BlinkinSubsystem blinkin;

    public ConveyLineBreak(ConveyorSubsystem conveyor, FlyWheelSubsystem flywheel, FloorIntakeSubsystem intake){
        this.conveyor = conveyor;
        this.flywheel = flywheel;
        this.intake = intake;
    }

    @Override
    public void initialize(){
      conveyor.conveyInward();   
      intake.intakeForward();
      blinkin.setRed();
    }

    @Override
    public boolean isFinished(){
        return flywheel.isNoteSeen();
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
        intake.intakeStop();
    }
    
}
