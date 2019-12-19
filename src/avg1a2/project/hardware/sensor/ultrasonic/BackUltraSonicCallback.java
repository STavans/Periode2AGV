package avg1a2.project.hardware.sensor.ultrasonic;

/**
 * Interface which defines the functions/situations the Back UltraSonic needs to supply for.
 */
public interface BackUltraSonicCallback {
    void onBackUltraSonic();
    void onBackCloseUltraSonic();
    void onBackFarUltraSonic();
}
