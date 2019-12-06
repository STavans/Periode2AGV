package avg1a2.project.hardware.sensor.bluetooth;

import TI.SerialConnection;
import avg1a2.project.hardware.Component;

public class BluetoothSensor implements Component {
    private SerialConnection connection;
    private BluetoothCallback callback;

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
