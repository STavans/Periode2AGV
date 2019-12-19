package avg1a2.project.hardware.sensor.ultrasonic;

import TI.Timer;

/**
 * Child object of UltraSonic specifically used to detect collisions on the front of the BoeBot.
 */
public class FrontUltraSonicSensor extends UltraSonicSensor {
    private FrontUltraSonicCallback callback;
    private Timer timer;

    /**
     * Constructor sets the initial attributes.
     * @param trigger The pin which to use for the UltraSonic trigger.
     * @param echo The pin which to use for the UltraSonic echo.
     * @param callback The callback which to signal upon detection.
     */
    public FrontUltraSonicSensor(int trigger, int echo, FrontUltraSonicCallback callback) {
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
