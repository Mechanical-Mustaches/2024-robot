package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FloorIntakeSubsystem extends SubsystemBase {
    /** Creates a new FloorIntakeSubsystem. */
    private CANSparkMax m_floorIntake = new CANSparkMax(9, MotorType.kBrushless);
    private CANSparkMax m_floorIntake_helper = new CANSparkMax(25, MotorType.kBrushless);



  public FloorIntakeSubsystem() {
    m_floorIntake.setIdleMode(IdleMode.kCoast);
    m_floorIntake_helper.setIdleMode(IdleMode.kCoast);
    m_floorIntake_helper.follow(m_floorIntake, true);
  }

  private void setSpeed(double percent){
         m_floorIntake.set(percent);
         SmartDashboard.putNumber("floor intake percent", percent);
     }

  public void intakeForward(){
    setSpeed(1);
  
  }

  public void intakeStop(){
    setSpeed(0);
  }

  public void intakeReverse(){
    setSpeed(-0.8);
  }
}
