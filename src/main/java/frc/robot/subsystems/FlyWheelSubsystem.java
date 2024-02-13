package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheelSubsystem extends SubsystemBase {    
 /** Creates a new FlyWheelSubsystem. */
    private CANSparkMax m_leftWheel = new CANSparkMax(11, MotorType.kBrushless);
    private CANSparkMax m_rightWheel = new CANSparkMax(12, MotorType.kBrushless);
    private final SparkLimitSwitch lineBreak = m_leftWheel.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen); 

  public FlyWheelSubsystem() {
    m_rightWheel.follow(m_leftWheel, true);

    SmartDashboard.putBoolean("flywheelLB", false);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
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
    //return lineBreak.isPressed();
    return SmartDashboard.getBoolean("flywheelLB", false);
  }

  public void ampShot(){
    m_leftWheel.set(0.2);
  }

  public void subWoofShot(){
    m_leftWheel.set(0.4);
  }

  public void podiumShot(){
    m_leftWheel.set(0.8);
  }

  public void sourceNomNom(){
    //m_leftWheel.set(-0.7);
    m_leftWheel.set(-0.5);
  }


}
