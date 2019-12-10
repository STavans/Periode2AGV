package avg1a2.examplecode.maze.ultrasonic;

import TI.BoeBot;
import TI.Timer;

/**
 * UltraSonic sensor is the sensor which will calculate distance towards an object in front of it, based on sound pulses.
 */
public class UltrasonicSensor {
    private int pin1;
    private int pin2;
    private Timer timer;
    private UltraSonicCallback ultraSonicCallback;

    /**
    Initialises all of the privates shown above
     **/
    public UltrasonicSensor(int pin1, int pin2, UltraSonicCallback ultraSonicCallBack) {
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.ultraSonicCallback = ultraSonicCallBack;
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
        BoeBot.digitalWrite(this.pin1, true);
        BoeBot.uwait(1);
        BoeBot.digitalWrite(this.pin1, false);
        int pulse = BoeBot.pulseIn(this.pin2, true, 10000);
        if (pulse > 100) {
            return pulse;
        } else {
            return 0;
        }
    }

    /**
    This updates the current state of the ultrasonic sensor. By having a set timer of 50milliseconds
    it gets the ultrasonic pulse and then calculates the distance
     **/
    public void update() {
        if (timer == null || timer.timeout()) {
            int scan = ultraSonicPulse();
            //System.out.println(scan);
            if (scan > 100) {
                int distance = calculateDistance(scan);
                if (distance < 20 && distance > 5) {
                    ultraSonicCallback.onUltraSonic();
                }  else if (distance <= 5){
                    ultraSonicCallback.closeUltraSonic();
                } else {
                    ultraSonicCallback.onNoCollision();
                }
            }
            timer = new Timer(50);
        }
    }
}