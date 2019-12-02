package avg1a2.examplecode.rejectsfornow.whisker;

import TI.BoeBot;
import avg1a2.project.hardware.Component;

public class Whisker implements Component {
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
            //isActive();
        }  else{
            System.out.println("It's not activated");
        }
    }


    @Override
    public void update() {
        //
        // To Do, if we use it
        //
    }
}
