package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PivotSubsystem extends SubsystemBase {
    
    private final CANSparkMax m_pivot = new CANSparkMax(14, MotorType.kBrushless);
    private final SparkAbsoluteEncoder encoder = m_pivot.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle); 
    private final SparkPIDController m_PidController;



    private double kP = 4;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = 0.3;
    private double kMinOutput = -0.15;


    public PivotSubsystem(){
        m_pivot.restoreFactoryDefaults();
        m_pivot.setIdleMode(IdleMode.kBrake);
        m_PidController = m_pivot.getPIDController();
        m_PidController.setFeedbackDevice(encoder);

        m_PidController.setP(kP);
        m_PidController.setI(kI);
        m_PidController.setD(kD);
        m_PidController.setIZone(kIz);
        m_PidController.setFF(kFF);
        m_PidController.setOutputRange(kMinOutput, kMaxOutput);

        SmartDashboard.putNumber("pivotPOS", 5);

    }
         @Override
      public void periodic()
      {
         SmartDashboard.putNumber("pivot Encoer", encoder.getPosition());
      }
     /*
     * Four States: (Least amout of movement)
     *  Base (start) Position
     *  Amp Position = 170
     *  Human Position = 85 
     *  Trap Position 
     *  Defense Position (Last resort use w/ limelight maybe?) 
     *  Podium Position
     */

     private void setArmPosition(float deg){
        m_PidController.setReference(deg / 360, CANSparkMax.ControlType.kPosition);
     }

     public void pivotBasePosition(){
        setArmPosition(206f);
     }

     public void pivotAmpPosition(){
        //setArmPosition((float)SmartDashboard.getNumber("pivotPOS", 220));
        setArmPosition(295.2f);
      }

     public void pivotHumanPosition(){
         setArmPosition(210f);
     }

     public void pivotTrapPosition(){
        setArmPosition(230f);
     }

     public void pivotPodiumPosition(){
         setArmPosition(240f);
     }

     public void pivotSubWooferPosition(){
         setArmPosition(206f);
     }

    public void pivotDefencePosition(){
         setArmPosition(210f);

     }
}
 