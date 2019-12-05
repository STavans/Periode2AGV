package avg1a2.project.hardware.sensor.bluetooth;

import TI.SerialConnection;
import avg1a2.project.hardware.Component;

public class BluetoothSensor implements Component {
    private SerialConnection connection;
    private BluetoothSensorCallback callback;

    public BluetoothSensor(SerialConnection connection, BluetoothSensorCallback callback) {
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
