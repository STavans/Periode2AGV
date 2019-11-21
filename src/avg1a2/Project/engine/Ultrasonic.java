package avg1a2.project.engine;

import TI.BoeBot;
import TI.Timer;

public class Ultrasonic {
    private int pin;
    private int timeOut;

    public Ultrasonic(int pin, int time) {
        this.pin = pin;
        this.timeOut = time;
    }

    public double detectObject(){
        double distance = BoeBot.pulseIn(this.pin, true, this.timeOut ) / 58.0;

        System.out.println(distance);

        return distance;
    }
}
