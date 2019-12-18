package avg1a2.project.hardware.sensor.ultrasonic;

import TI.BoeBot;
import avg1a2.project.hardware.Component;

public abstract class UltraSonicSensor implements Component {
    private int trigger;
    private int echo;

    public UltraSonicSensor(int trigger, int echo) {
        this.trigger = trigger;
        this.echo = echo;
    }

    /**
     This method calculates the distance of the ultrasonic sensor's pulse length, if the pulse is greater than 100 it returns a zero.
     That is to test if the sensor works correctly
     **/
    protected int calculateDistance(int pulse) {
        if (pulse > 100) {
            return pulse / 58;
        }
        else return 100;
    }

    /**
     This method gets the pulse length of the ultrasonic, it does so by triggering a pulse
     and returning the value of it

     **/
    protected int ultraSonicPulse() {
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

    public abstract void update();
}
