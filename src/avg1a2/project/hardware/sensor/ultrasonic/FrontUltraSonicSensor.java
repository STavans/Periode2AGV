package avg1a2.project.hardware.sensor.ultrasonic;

import TI.Timer;

/**
 * UltraSonic sensor is the sensor which will calculate distance towards an object in front of it, based on sound pulses.
 */
public class FrontUltraSonicSensor extends UltraSonicSensor {
    private FrontUltraSonicCallback callback;
    private Timer timer;

    public FrontUltraSonicSensor(int trigger, int echo, FrontUltraSonicCallback callback) {
        super(trigger, echo);
        this.callback = callback;
    }

    @Override
    public void update() {
        if (timer == null || timer.timeout()) {
            int scan = super.ultraSonicPulse();
            if (scan > 100) {
                int distance = super.calculateDistance(scan);
                if (distance < 20 && distance > 10) {
                    callback.onFrontUltraSonic();
                }  else if (distance <= 10){
                    callback.onFrontCloseUltraSonic();
                } else {
                    callback.onFrontFarUltraSonic();
                }
            }
            timer = new Timer(50);
        }
    }
}
