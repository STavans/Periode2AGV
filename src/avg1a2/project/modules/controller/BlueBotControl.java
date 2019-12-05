package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothSensorCallback;
import avg1a2.project.hardware.signal.led.NeoPixel;

public class BlueBotControl implements BluetoothSensorCallback {
    private Component bluetoothSensor;
    private NeoPixel neoPixel1;
    private NeoPixel neoPixel2;

    public BlueBotControl() {
        neoPixel1 = new NeoPixel(0,0,0,255);
        neoPixel2 = new NeoPixel(0,0,0,255);
    }

    public void setBluetoothSensor(Component bluetoothSensor) {
        this.bluetoothSensor = bluetoothSensor;
    }

    public void run() throws RuntimeException {
        if (this.bluetoothSensor == null) {
            throw new RuntimeException();
        }
        bluetoothSensor.update();
    }

    @Override
    public void onSignal(int data) {
        if (data == 119) {
            neoPixel1.on();
            neoPixel2.on();
        } else {
            neoPixel1.off();
            neoPixel2.off();
        }
    }
}
