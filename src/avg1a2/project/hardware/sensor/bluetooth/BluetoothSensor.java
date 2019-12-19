package avg1a2.project.hardware.sensor.bluetooth;

import TI.SerialConnection;
import avg1a2.project.hardware.Component;

/**
 * Bluetooth Sensor to allow and receive bluetooth connections.
 */
public class BluetoothSensor implements Component {
    private SerialConnection connection;
    private BluetoothCallback callback;

    /**
     * Constructor sets the given SerialConnection and BluetoothCallback as attributes.
     * @param connection The Serial Connection to use for connections.
     * @param callback The callback which to notify on a valid signal.
     */
    public BluetoothSensor(SerialConnection connection, BluetoothCallback callback) {
        this.connection = connection;
        this.callback = callback;
    }

    @Override
    public void update() {
        if (connection.available() > 0) {
            callback.onSignal(connection.readByte());
        }
    }
}
