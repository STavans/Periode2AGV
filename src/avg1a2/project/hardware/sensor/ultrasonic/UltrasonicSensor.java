package avg1a2.project.hardware.sensor.ultrasonic;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.Component;

public class UltraSonicSensor implements Component {
    private int trigger;
    private int echo;
    private Timer timer;

    public UltraSonicSensor(int trigger, int echo) {
        this.trigger = trigger;
        this.echo = echo;
    }

    /**
     This method calculates the distance of the ultrasonic sensor's pulse length, if the pulse is greater than 100 it returns a zero.
     That is to test if the sensor works correctly
     **/
    private int calculateDistance(int pulse) {
        if (pulse > 100) {
            return pulse / 58;
        }
        else return 100;
    }

    /**
     This method gets the pulse length of the ultrasonic, it does so by triggering a pulse
     and returning the value of it

     **/
    private int ultraSonicPulse() {
        BoeBot.digitalWrite(this.trigger, true);
        BoeBot.uwait(1);
        BoeBot.digitalWrite(this.trigger, false);
        int pulse = BoeBot.pulseIn(this.echo, true, 10000);
        if (pulse > 100) {
            return pulse;
        } else {
            return 0;
        }
    }

    @Override
    public void update() {
        
    }
}
