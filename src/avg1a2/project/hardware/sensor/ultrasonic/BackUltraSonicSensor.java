package avg1a2.project.hardware.sensor.ultrasonic;

import TI.Timer;

/**
 * Child object of UltraSonic specifically used to detect collisions on the back of the BoeBot.
 */
public class BackUltraSonicSensor extends UltraSonicSensor {
    private BackUltraSonicCallback callback;
    private Timer timer;

    /**
     * Constructor sets the initial attributes.
     * @param trigger The pin which to use for the UltraSonic trigger.
     * @param echo The pin which to use for the UltraSonic echo.
     * @param callback The callback which to signal upon detection.
     */
    public BackUltraSonicSensor(int trigger, int echo, BackUltraSonicCallback callback) {
        super(trigger, echo);
        this.callback = callback;
    }

    /**
     * Updatable function to be continuously called.
     */
    @Override
    public void update() {
        if (timer == null || timer.timeout()) {
            int scan = super.ultraSonicPulse();
            if (scan > 100) {
                int distance = super.calculateDistance(scan);
               //TODO
//                if (distance < 20 && distance > 10) {
//                    callback.onBackUltraSonic();
//                }  else if (distance <= 10){
//                    callback.onBackCloseUltraSonic();
//                } else {
//                    callback.onBackFarUltraSonic();
//                }
            }
            timer = new Timer(50);
        }
    }
}
