package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.pivotConstants;

public class PivotSubsystem extends SubsystemBase {
    
    private final CANSparkMax m_pivot = new CANSparkMax(14, MotorType.kBrushless);
    private final SparkAbsoluteEncoder encoder = m_pivot.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle); 
    private final SparkPIDController m_PidController;
 
    private double kP = 6; //7.2
    private double kI = 0.019;
    private double kD = 150;

    private double ampkP = 2.5;
    private double ampkI = 0;
    private double ampkD = 0;

    private double kIz = 0.5;
    private double kFF = 0;
    private double kMaxOutput = 0.5; //0.4
    private double kMinOutput = -0.1;

    public PivotSubsystem(){
        m_pivot.restoreFactoryDefaults();
        m_pivot.setIdleMode(IdleMode.kBrake);
        m_PidController = m_pivot.getPIDController();
        m_PidController.setFeedbackDevice(encoder);

        m_PidController.setP(kP);
        m_PidController.setI(kI);
        m_PidController.setD(kD);
        m_PidController.setIZone(kIz);
        m_PidController.setFF(kFF);

        m_PidController.setP(ampkP,1);
        m_PidController.setI(ampkI,1);
        m_PidController.setD(ampkD,1);
        m_PidController.setIZone(kIz,1);
        m_PidController.setFF(kFF,1);
        
        m_PidController.setOutputRange(kMinOutput, kMaxOutput);
        SmartDashboard.setDefaultNumber("pos", 0);
        m_PidController.setIMaxAccum(1, 0);
    }

     /*
     * Pivot States:
     *  Base Position
     *  Amp Position
     *  Human Position
     *  Climb Position 
     *  Trap Position 
     *  Defense Position (Last resort use w/ limelight maybe?) 
     *  Podium Position
     */
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Pivot Ebcider Valye", encoder.getPosition()*360);
    }

     private void setArmPosition(float deg){
        int slot = 0;
        if (deg > 200) {
            slot = 1;
        }
         m_PidController.setReference((deg) / 360, CANSparkMax.ControlType.kPosition, slot);
         m_PidController.setIAccum(0);
         SmartDashboard.putNumber("pivot setpoint", (deg));
     }

     public void pivotBasePosition(){
        setArmPosition(pivotConstants.basePosition);
     }

     public void pivotAmpPosition(){
        setArmPosition(254f);
      }

     public void pivotHumanPosition(){
         setArmPosition(164f);
     }
     public void pivotSkipPosition(){
        setArmPosition(184f);
     }
     public void pivotClimbPosition(){
        setArmPosition(209f);
     }
     public void pivotTrapPosition(){
        setArmPosition(322);
     }

     public void pivotPodiumPosition(){
        setArmPosition(190f);
      }

     public void pivotSubWooferPosition(){
         setArmPosition(154);
     }

    public void pivotDefencePosition(){
         setArmPosition(156f);
     }

    public void pivotTestPosition(){
        double output =SmartDashboard.getNumber("pos", pivotConstants.basePosition);

        output = Math.max(output, pivotConstants.basePosition);
        double deg = Math.min(output, 210);
        setArmPosition((float)deg);
     }

    public void dynamicShot(double Distance, double angledeg, boolean isRed){
      
      //equation from google sheets for our line of best fit
      double degOffset = 0;
      if(isRed) {
        degOffset = 180;
      }
      double angle = Math.abs(angledeg-degOffset)/360;
      SmartDashboard.putNumber("LiveShot Computed Angle", angle);
      //double output = 157 +8.43*Distance - 0.271*Math.pow(Distance, 2); //pretty okay one without angle comp
      //double output = 125.6497 + 27.444*Distance - (3.185*Distance*Distance) + (1.119*angle) + (61.84*angle*angle) +1; //first attempt at including angle
      //double output = 125.76785857794536 + Distance * 27.252600857688766 + Math.pow(Distance, 2) * -3.1428771404934173 + angle * 8.92498259473741 + 0.9;
      double output = 125.55246220859223 + Distance * 30.479217634813665 + Math.pow(Distance, 2) * -3.9385528567845647 + angle * -9.990768684068321 + Math.pow(angle, 2) * 16.174554817622628 + 3.25;

      if(Distance > 4.336) {
        output = 186;
      }

      //give output an upper and lower bound to not break the pivot
      output = Math.max(output, pivotConstants.basePosition);
      double deg = Math.min(output, 210);

      setArmPosition((float)deg);
    // double output =SmartDashboard.getNumber("pos", pivotConstants.basePosition);

    //     output = Math.max(output, pivotConstants.basePosition);
    //     double deg = Math.min(output, 210);
    //     setArmPosition((float)deg);
    }

    public boolean getAtSetpoint(double threshould) {
        return encoder.getPosition()*360 > threshould-5;
    }
}
 