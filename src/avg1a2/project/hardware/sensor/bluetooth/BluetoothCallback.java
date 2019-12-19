package avg1a2.project.hardware.sensor.bluetooth;

/**
 * Interface defines that functions bluetooth sensors will need to support.
 */
public interface BluetoothCallback {
    void onSignal(int data);
}
