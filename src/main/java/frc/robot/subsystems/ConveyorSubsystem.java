package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ConveyorSubsystem extends SubsystemBase {
    CANSparkMax m_conveyor = new CANSparkMax(10, MotorType.kBrushless);

    public ConveyorSubsystem(){

    }

    public void conveyInward(){
        m_conveyor.set(0.5);
    }

    public void stopConvey(){
        m_conveyor.set(0);
    }


    
}
