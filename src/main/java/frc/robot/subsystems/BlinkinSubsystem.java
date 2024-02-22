package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BlinkinSubsystem extends SubsystemBase {
    Spark Blinkin = new Spark(0);

    public BlinkinSubsystem(){
    }

    @Override
    public void periodic(){
        
    }

    public void setLights(double colorData){
        Blinkin.set(colorData);
    }

    public void setRed(){
        Blinkin.set(0.61);
    }

    public void setOrange(){
        Blinkin.set(0.65);
    }

    public void setYellow(){
        Blinkin.set(0.69);
    }

    public void setGreen(){
        Blinkin.set(0.77);
    }

    public void setBlue(){
        Blinkin.set(0.87);
    }

    public void setPurple(){
        Blinkin.set(0.91);
    }

    public void setWhite(){
        Blinkin.set(0.93);
    }
    
}
