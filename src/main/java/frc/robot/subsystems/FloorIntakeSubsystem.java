package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkLimitSwitch;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FloorIntakeSubsystem extends SubsystemBase {
    /** Creates a new FloorIntakeSubsystem. */
    private CANSparkMax m_floorIntake = new CANSparkMax(9, MotorType.kBrushless);
    private CANSparkMax m_floorIntake_helper = new CANSparkMax(25, MotorType.kBrushless);
    
    private final SparkLimitSwitch lineBreak = m_floorIntake.getForwardLimitSwitch(SparkLimitSwitch.Type.kNormallyOpen); 



  public FloorIntakeSubsystem() {
    SmartDashboard.putBoolean("intakeLB", true);
    m_floorIntake.setIdleMode(IdleMode.kCoast);
    m_floorIntake_helper.setIdleMode(IdleMode.kCoast);
    m_floorIntake_helper.follow(m_floorIntake, true);
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
    m_floorIntake.set(1);
  
  }

  public void intakeStop(){
    m_floorIntake.set(0);
  }

  public void intakeReverse(){
    m_floorIntake.set(-0.8);
  }

  public boolean isNotePresent(){
    return lineBreak.isPressed();
    //return SmartDashboard.getBoolean("intakeLB", true);
  }

}
