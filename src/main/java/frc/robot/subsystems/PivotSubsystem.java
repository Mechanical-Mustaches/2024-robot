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



    private double kP = 2; //4 .3 .15
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = 0.6;
    private double kMinOutput = -0.25;


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

        //SmartDashboard.putNumber("pivotPOS", 161.64);

    }
         @Override
      public void periodic()
      {
         SmartDashboard.putNumber("pivot Encoer", encoder.getPosition()*360);
      }
     /*
     * Pivot States:
     *  Base Position
     *  Amp Position
     *  Human Position
     *  Climb Position 
     *  Trap Position 
     *  Defense Position (Last resort use w/ limelight maybe?) 
     *  Podium Position
     */
 
     private void setArmPosition(float deg){
        m_PidController.setReference((deg-6) / 360, CANSparkMax.ControlType.kPosition);
     }
     //subtract 6 if you are running the "new" cage remove if old cage
     public void pivotBasePosition(){
        setArmPosition(161.64f);
     }

     public void pivotAmpPosition(){
        setArmPosition(260f);
      }

     public void pivotHumanPosition(){
         setArmPosition(170f);//168
     }
     public void pivotSkipPosition(){
        setArmPosition(220f);
     }
     public void pivotClimbPosition(){
        setArmPosition(215f);
     }
     public void pivotTrapPosition(){
        setArmPosition(328);
     }

     public void pivotPodiumPosition(){
        //setArmPosition((float)SmartDashboard.getNumber("pivotPOS", 220));
        setArmPosition(190f); //186
     }

     public void pivotSubWooferPosition(){
         setArmPosition(162);
     }

    public void pivotDefencePosition(){
         setArmPosition(162f);

     }
}
 