package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;

public class FlyWheelSubsystem extends SubsystemBase {    
 /** Creates a new FlyWheelSubsystem. */
    private CANSparkMax m_leftWheel = new CANSparkMax(11, MotorType.kBrushless);
    private CANSparkMax m_rightWheel = new CANSparkMax(12, MotorType.kBrushless);
    private final SparkLimitSwitch lineBreak = m_leftWheel.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen); 
    int timer = 0;

  public FlyWheelSubsystem() {
    m_rightWheel.follow(m_leftWheel, true);    
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("flywheelSetpoint", m_leftWheel.get());
  }

  @Override
  public void simulationPeriodic() {
    
  }
  
  public double getPercentSetpoint(){
    return m_leftWheel.get();
  }

  /*
   * Subwoofer Speed: 40% straight on, side
   *  Podium Speed: 70%
   *  193in Speed: 
   */
  
  public void rampUp(){
    m_leftWheel.set(0.5);
  }

  public void rampDown(){
   //m_leftWheel.set(0.3);
   m_leftWheel.set(0);
  }

  public boolean isNoteSeen(){
    return lineBreak.isPressed();
   // return SmartDashboard.getBoolean("flywheelLB", false);
  }

  public void ampShot(){
    m_leftWheel.set(0.4); //0.2;
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

  public void closeShot(){
    m_leftWheel.set(1);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }
  

  public void farShot(){
    m_leftWheel.set(1);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }

  public void sourceNomNom(){
    //m_leftWheel.set(-0.7);
    m_leftWheel.set(-0.3);
    LimelightHelpers.setLEDMode_ForceOff("limelight-april");
  }


}
