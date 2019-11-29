package avg1a2.project.hardware.sensor.ir;

/**
 * Callback for the infrared Sensor to use whenever it detects an infrared signal.
 */
public interface IRCallback {
    void onSignal(int signal);
}
