package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ConveyorSubsystem extends SubsystemBase {
    CANSparkMax m_conveyor = new CANSparkMax(10, MotorType.kBrushless);
    RelativeEncoder encoder;

    public ConveyorSubsystem(){
        m_conveyor.setIdleMode(IdleMode.kBrake);
        encoder = m_conveyor.getEncoder();
    }

    public void conveyInward(){
        m_conveyor.set(-0.8);
    }

    public void stopConvey(){
        m_conveyor.set(0);
    }

    public void conveyFromSource(){
        m_conveyor.set(-0.1);
        // 0.2
    }

    public void conveyMoveBack(){
        m_conveyor.set(0.1);
    }
}
