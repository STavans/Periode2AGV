package avg1a2.project.hardware.sensor.ultrasonic;

/**
 * Callback for the front ultraSonic to use whenever it detects a collision.
 */
public interface FrontUltraSonicCallback {
    void onFrontUltraSonic();
    void onFrontCloseUltraSonic();
    void onFrontFarUltraSonic();
}
