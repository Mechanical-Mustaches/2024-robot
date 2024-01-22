package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PivotSubsystem extends SubsystemBase {
    
    private final CANSparkMax m_pivot = new CANSparkMax(13, MotorType.kBrushless);
    private final SparkAbsoluteEncoder encoder = m_pivot.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle); 
    private final SparkPIDController m_PidController;

    private double kP = 0;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = 1;
    private double kMinOutput = -1;


    public PivotSubsystem(){
        m_pivot.restoreFactoryDefaults();
        m_PidController = m_pivot.getPIDController();
        m_PidController.setFeedbackDevice(encoder);

        m_PidController.setP(kP);
        m_PidController.setI(kI);
        m_PidController.setD(kD);
        m_PidController.setIZone(kIz);
        m_PidController.setFF(kFF);
        m_PidController.setOutputRange(kMinOutput, kMaxOutput);

    }

    



    





}
