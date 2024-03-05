package frc.robot.commands.PositionCommands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.PivotSubsystem;


public class PivotLimeLightCommand extends Command {
  private final PivotSubsystem pivot;
  private String limeLight = "limelight-april";
  //needs to be changed with actual variable 
  private double m = 0;
  private double b = 0;

  public PivotLimeLightCommand(PivotSubsystem pivot) {
    this.pivot = pivot;

  }

  @Override
  public void initialize(){
    SmartDashboard.putBoolean("seeApril", false);
  }

  @Override
  public void execute(){
    if(LimelightHelpers.getTV("limelight-april")){
      SmartDashboard.putBoolean("seeApril", true);
      pivot.pivotPOS((float)(LimelightHelpers.getTA(limeLight) * m + b));

    }
    else{
       SmartDashboard.putBoolean("seeApril", false);
       pivot.pivotBasePosition();
    } 
  }
  
  @Override
  public boolean isFinished()
  {
    return false;
  }

  @Override
  public void end(boolean interrupted)
  {
    pivot.pivotBasePosition();
  }


}




