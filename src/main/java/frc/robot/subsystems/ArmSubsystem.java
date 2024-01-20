package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

public class ArmSubsystem extends SubsystemBase {
     /** Creates a new ArmSubsystem. */

     //Left arm has an absolute encoder
    private CANSparkMax m_leftArm = new CANSparkMax(31, MotorType.kBrushless);
    private CANSparkMax m_rightArm = new CANSparkMax(2, MotorType.kBrushless);

    private final SparkAbsoluteEncoder encoder = m_leftArm.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle); 
    private final SparkPIDController m_PidController;

    private double kP = 0.1;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = .1;
    private double kMinOutput = -.1;
    
    private boolean isArmRaised = false; 

  public ArmSubsystem() {
    m_leftArm.restoreFactoryDefaults();
    m_PidController = m_leftArm.getPIDController();
    m_PidController.setFeedbackDevice(encoder);

    m_PidController.setP(kP);
    m_PidController.setI(kI);
    m_PidController.setD(kD);
    m_PidController.setIZone(kIz);
    m_PidController.setFF(kFF);
    m_PidController.setOutputRange(kMinOutput, kMaxOutput);

    m_rightArm.follow(m_leftArm, true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void togglePosition(){
    System.out.println("position: " + isArmRaised);
    if(isArmRaised){
      m_PidController.setReference(0, CANSparkMax.ControlType.kPosition);
      isArmRaised = false;
    }
    else{ 
      m_PidController.setReference(0.5, CANSparkMax.ControlType.kPosition);
      isArmRaised = true;
    }
   
  } 



}
