package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkBase.IdleMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class FlyWheelSubsystem extends SubsystemBase {    
 /** Creates a new FlyWheelSubsystem. */
    private CANSparkMax m_leftWheel = new CANSparkMax(11, MotorType.kBrushless);
    private CANSparkMax m_rightWheel = new CANSparkMax(12, MotorType.kBrushless);
    private final SparkLimitSwitch lineBreak = m_leftWheel.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen); 

  public FlyWheelSubsystem() {
    m_rightWheel.follow(m_leftWheel, true);    
    m_rightWheel.enableVoltageCompensation(12);
    m_leftWheel.enableVoltageCompensation(12);
    m_rightWheel.setIdleMode(IdleMode.kCoast);
    m_leftWheel.setIdleMode(IdleMode.kCoast);
  }

  /*
   * Subwoofer Speed: 40% straight on, side
   *  Podium Speed: 70%
   *  193in Speed: 
   */
  private void setSpeed(double percent){
    m_leftWheel.set(percent);
    SmartDashboard.putNumber("flywheel percent", percent);
  }
  
  public void rampUp(){
    setSpeed(0.5);
  }

  public void rampDown(){
   setSpeed(0);
  }

  public boolean isNoteSeen(){
    return lineBreak.isPressed();
  }

  public void ampShot(){
    setSpeed(0.4); //0.2;
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

  public void closeShot(){
    setSpeed(1);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }
  
  public void farShot(){
    setSpeed(1);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

  public void sourceNomNom(){
    //m_leftWheel.set(-0.7);
    setSpeed(-0.3);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }


}
