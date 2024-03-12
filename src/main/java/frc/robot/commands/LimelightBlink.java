package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;

public class LimelightBlink extends Command {
    Timer time = new Timer();

    public LimelightBlink(){
        time.reset();
    }

    @Override
    public void initialize(){
        time.start();
        LimelightHelpers.setLEDMode_ForceBlink("limelight-april");
        while(time.get() < 1) {
        }
        LimelightHelpers.setLEDMode_ForceOff("limelight-april");
        time.stop();
        time.reset();
    }


}
