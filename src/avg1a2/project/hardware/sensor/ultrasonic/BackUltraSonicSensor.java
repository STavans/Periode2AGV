package avg1a2.project.hardware.sensor.ultrasonic;

import TI.Timer;

public class BackUltraSonicSensor extends UltraSonicSensor {
    private BackUltraSonicCallback callback;
    private Timer timer;

    public BackUltraSonicSensor(int trigger, int echo, BackUltraSonicCallback callback) {
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
                    callback.onBackUltraSonic();
                }  else if (distance <= 10){
                    callback.onBackCloseUltraSonic();
                } else {
                    callback.onBackFarUltraSonic();
                }
            }
            timer = new Timer(50);
        }
    }
}
