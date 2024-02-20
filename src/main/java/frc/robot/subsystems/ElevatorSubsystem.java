package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    //Created an Elevator Subsystem
    private final CANSparkMax m_elevator = new CANSparkMax(13, MotorType.kBrushless);
    private final CANSparkMax m_elevator_helper = new CANSparkMax(15, MotorType.kBrushless);
    private final RelativeEncoder elevatorEncoder = m_elevator.getEncoder();
    private final SparkPIDController m_PidController = m_elevator.getPIDController();

    private double kP = 0.1;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0;
    private double kMaxOutput = 0.9;
    private double kMinOutput = -0.7;

    

    public ElevatorSubsystem(){ 
        m_elevator.setInverted(true);
        m_elevator_helper.setInverted(true);
        elevatorEncoder.setPosition(0);
        m_PidController.setFeedbackDevice(elevatorEncoder);
        m_elevator.setIdleMode(IdleMode.kBrake);
        m_elevator_helper.setIdleMode(IdleMode.kBrake);
        
        m_PidController.setP(kP);
        m_PidController.setI(kI);
        m_PidController.setD(kD);
        m_PidController.setIZone(kIz);
        m_PidController.setFF(kFF);
        m_PidController.setOutputRange(kMinOutput, kMaxOutput);
        m_elevator_helper.follow(m_elevator, false);

    }


    /*
     * Five States: (Least amout of movement)
     *  Base (start) Position
     *  Amp Position = 15
     *  Human Position = 3 
     *  Trap Position 
     *  Defense Position (Last resort use w/ limelight maybe?) 
     *  Podium Position 
     * 
     *   Max Pos --> -44 
     */

     private void setElevatorHight(double heightInchs){
        m_PidController.setReference(heightInchs, CANSparkMax.ControlType.kPosition);
     }

     public void basePosition(){
        setElevatorHight(0.0);
     }

     public void ampPosition(){
        setElevatorHight(16.0);
     }

     public void humanPosition(){
        setElevatorHight(2.0);//3
     }

     public void trapPosition(){
        setElevatorHight(55);
      }

     public void defensePosition(){
        setElevatorHight(1);
      }
   
      public void podiumPosition(){ 
        setElevatorHight(0);
      }

      public void subWooferPosition(){
         setElevatorHight(0.0);
      }

      

    

     

     

     





    
}
