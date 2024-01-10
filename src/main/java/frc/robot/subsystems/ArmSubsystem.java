package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;

public class ArmSubsystem extends SubsystemBase {
     /** Creates a new FlyWheelSubsystem. */

    //These motors (wheels) will work in opposite of eachother so they can shoot the note out
    private CANSparkMax m_leftWheel = new CANSparkMax(96, MotorType.kBrushless);
    private CANSparkMax m_rightWheel = new CANSparkMax(95, MotorType.kBrushless);


  public ArmSubsystem() {
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }



}
