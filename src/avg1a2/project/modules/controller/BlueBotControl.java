package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothSensorCallback;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;

public class BlueBotControl implements BluetoothSensorCallback {
    private Component bluetoothSensor;
    private LedGroup neopixels;

    public BlueBotControl() {
        neopixels = new LedGroup();
        neopixels.addLed("pixel1",new NeoPixel(0,0,0,255));
        neopixels.addLed("pixel2",new NeoPixel(1,0,0,255));
        neopixels.addLed("pixel3",new NeoPixel(2,0,0,255));
        neopixels.addLed("pixel4",new NeoPixel(3,0,0,255));
        neopixels.addLed("pixel5",new NeoPixel(4,0,0,255));
        neopixels.addLed("pixel6",new NeoPixel(5,0,0,255));
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
            neopixels.on();
        } else {
            neopixels.off();
        }
    }
}
