package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    //Created an Elevator Subsystem
    private final CANSparkMax m_elevator = new CANSparkMax(14, MotorType.kBrushless);
    private final RelativeEncoder elevatorEncoder = m_elevator.getEncoder();
    private final SparkPIDController m_PidController = m_elevator.getPIDController();

    private double kP = 0.1;
    private double kI = 0;
    private double kD = 0;
    private double kIz = 0;
    private double kFF = 0.001;
    private double kMaxOutput = 0.3;
    private double kMinOutput = -0.3;
    

    public ElevatorSubsystem(){
        elevatorEncoder.setPosition(0);
        m_PidController.setFeedbackDevice(elevatorEncoder);
        
        m_PidController.setP(kP);
        m_PidController.setI(kI);
        m_PidController.setD(kD);
        m_PidController.setIZone(kIz);
        m_PidController.setFF(kFF);
        m_PidController.setOutputRange(kMinOutput, kMaxOutput);

    }


    /*
     * Five States: (Least amout of movement)
     *  Base (start) Position
     *  Amp Position 
     *  Trap Position 
     *  Defense Position (Last resort use w/ limelight maybe?) 
     */

     private void setElevatorHight(double heightInchs){
         m_PidController.setReference(heightInchs, CANSparkMax.ControlType.kPosition);
     }

     public void basePosition(){
        setElevatorHight(0.0);
     }

     public void ampPosition(){
        setElevatorHight(2.5);
     }

     public void trapPosition(){
        setElevatorHight(5.5);
   }

     public void defensePosition(){
        setElevatorHight(3.5);
   }

    

     

     

     





    
}
