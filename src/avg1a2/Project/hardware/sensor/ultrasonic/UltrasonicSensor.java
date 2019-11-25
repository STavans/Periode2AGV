package avg1a2.project.hardware.sensor.ultrasonic;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.sensor.Sensor;

public class UltrasonicSensor implements Sensor{
    private int pin1;
    private int pin2;
    private Timer timer;
    private Timer timer2;
    private UltraSonicCallback ultraSonicCallback;
    private boolean isOn;

/*
Initialises all of the privates shown above
 */
    public UltrasonicSensor(int pin1, int pin2, UltraSonicCallback ultraSonicCallBack, boolean isOn) {
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.timer = new Timer(50);

        this.ultraSonicCallback = ultraSonicCallBack;
        this.isOn = isOn;
    }

/*
This method calculates the distance of the ultrasonic sensor's pulse length, if the pulse is greater than 100 it returns a zero.
That is to test if the sensor works correctly
 */
    public int calculateDistance(int pulse){

        if(pulse > 100) {
            return pulse / 58;
        }
        return 0;

    }

/*
This method gets the pulse length of the ultrasonic, it does so by triggering a pulse
and returning the value of it

 */
    public int ultraSonicPulse(){
        BoeBot.digitalWrite(this.pin1, true);
        BoeBot.wait(1);
        BoeBot.digitalWrite(this.pin1, false);
        int pulse = BoeBot.pulseIn(this.pin2, true, 10000);

        if(pulse > 100){
            return pulse;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }

//    @Override
//    public void update() {
//        if(this.timer.timeout() || this.timer == null){
//                int distance = calculateDistance();
//
//                if(distance < 50){
//                    this.ultraSonicCallback.onUltraSonic();
//                }
//
//            this.timer = new Timer(50);
//        }
//    }
    /*
    This updates the current state of the ultrasonic sensor. By having a set timer of 50milliseconds
    it gets the ultrasonic pulse and then calculates the distance
     */
public void update() {
    if (timer.timeout()) {
        int scan = ultraSonicPulse();
        if (scan > 100) {
            int distance = calculateDistance(scan);
            if (distance < 50) {
                System.out.println(distance);
                ultraSonicCallback.onUltraSonic();
            }
        }
        this.timer = new Timer(50);

    }

}
}
