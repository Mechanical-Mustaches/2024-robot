package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FloorIntakeSubsystem extends SubsystemBase {
    /** Creates a new FloorIntakeSubsystem. */
    private CANSparkMax m_floorIntake = new CANSparkMax(9, MotorType.kBrushless);
    


  public FloorIntakeSubsystem() {
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void intakeForward(){
    m_floorIntake.set(0.8);
  
  }

  public void intakeStop(){
    m_floorIntake.set(0);
  }

}
