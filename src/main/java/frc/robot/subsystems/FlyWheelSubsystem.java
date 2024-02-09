package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlyWheelSubsystem extends SubsystemBase {    
 /** Creates a new FlyWheelSubsystem. */
    private CANSparkMax m_leftWheel = new CANSparkMax(11, MotorType.kBrushless);
    private CANSparkMax m_rightWheel = new CANSparkMax(12, MotorType.kBrushless);

    private final SparkPIDController m_PidController = m_leftWheel.getPIDController();  
    private final RelativeEncoder flyWheelEncoder = m_leftWheel.getEncoder();  

    private final SparkLimitSwitch lineBreak = m_leftWheel.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen); 

    private double kP = 0.0001;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = 1;
    private double kMinOutput = -1;

  public FlyWheelSubsystem() {
    m_PidController.setFeedbackDevice(flyWheelEncoder);
    
    m_PidController.setP(kP);
    m_PidController.setI(kI);
    m_PidController.setD(kD);
    m_PidController.setIZone(kIz);
    m_PidController.setFF(kFF);
    m_PidController.setOutputRange(kMinOutput, kMaxOutput);

    m_rightWheel.follow(m_leftWheel, true);

   SmartDashboard.putNumber("flywheel", 0);
    SmartDashboard.putBoolean("flywheelLB", false);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(this.isNoteSeen());
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
  // m_PidController.setReference(0.5, CANSparkMax.ControlType.kVelocity);
    m_leftWheel.set(SmartDashboard.getNumber("flywheel", 0));
  }

  public void rampDown(){
   // m_PidController.setReference(0, CANSparkMax.ControlType.kVelocity);
   m_leftWheel.set(0);
  }

  public boolean isNoteSeen(){
    return lineBreak.isPressed();
    //return SmartDashboard.getBoolean("flywheelLB", true);
  }


} 
