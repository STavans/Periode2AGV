package avg1a2.project.hardware.sensor.whisker;

import TI.BoeBot;
import avg1a2.project.hardware.sensor.Sensor;

public class Whisker implements Sensor {
    private int pin;

/*
The constructor initialises the integer
 */
    public Whisker(int pin) {
        this.pin = pin;
    }

    /**
     This class checks if the whisker is activated or not
     */
    public void whiskerIsActive(){
        if(BoeBot.digitalRead(pin)){
            isActive();
        }  else{
            System.out.println("It's not activated");
        }
    }

    @Override
    public boolean isActive() {
        System.out.println("It's activated");
        return false;
    }

    @Override
    public void update() {


    }
}
