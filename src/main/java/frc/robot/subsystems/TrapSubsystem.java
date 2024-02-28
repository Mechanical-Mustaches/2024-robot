package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TrapSubsystem extends SubsystemBase {
    private final CANSparkMax m_trap = new CANSparkMax(50, MotorType.kBrushless);
    private final RelativeEncoder trapEncoder = m_trap.getEncoder();
    private final SparkPIDController m_PidController = m_trap.getPIDController();

    private double kP = 0.2;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = 0.1;
    private double kMinOutput = -0.1;


    public TrapSubsystem(){
    trapEncoder.setPosition(0);
        m_PidController.setFeedbackDevice(trapEncoder);
        m_trap.setIdleMode(IdleMode.kBrake);
        
        m_PidController.setP(kP);
        m_PidController.setI(kI);
        m_PidController.setD(kD);
        m_PidController.setIZone(kIz);
        m_PidController.setFF(kFF);
        m_PidController.setOutputRange(kMinOutput, kMaxOutput);
    }

    public void wipe(){
        m_PidController.setReference(10, CANSparkMax.ControlType.kPosition);
    }

    public void trapBasePosition(){
        m_PidController.setReference(0, CANSparkMax.ControlType.kPosition);
    }




    
}
