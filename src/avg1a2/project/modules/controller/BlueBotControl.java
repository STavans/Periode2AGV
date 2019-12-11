package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothCallback;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.logic.State;
import avg1a2.project.modules.irconversion.IROverridable;

public class BlueBotControl implements BluetoothCallback, IROverridable {
    private RouteControl routeControl;
    private Component bluetoothSensor;
    private LedGroup neoPixels;
    private State programState;

    public BlueBotControl(RouteControl routeControl) {
        this.routeControl = routeControl;

        neoPixels = new LedGroup();
        neoPixels.addLed("pixel1",new NeoPixel(0,0,0,255));
        neoPixels.addLed("pixel2",new NeoPixel(1,0,0,255));
        neoPixels.addLed("pixel3",new NeoPixel(2,0,0,255));
        neoPixels.addLed("pixel4",new NeoPixel(3,0,0,255));
        neoPixels.addLed("pixel5",new NeoPixel(4,0,0,255));
        neoPixels.addLed("pixel6",new NeoPixel(5,0,0,255));
    }

    public void setBluetoothSensor(Component bluetoothSensor) {
        this.bluetoothSensor = bluetoothSensor;
    }

    public void setProgramState(State programState) {
        this.programState = programState;
    }

    public void run() throws RuntimeException {
        routeControl.run();
        blueBotScan();
    }

    private void blueBotScan() {
        if (this.bluetoothSensor == null) {
            throw new RuntimeException();
        }
        bluetoothSensor.update();
    }

    @Override
    public void onSignal(int data) {
        if (data == 119) {
            neoPixels.on();
        } else {
            neoPixels.off();
        }
    }

    @Override
    public void override() {
        this.programState.setState("Override");
    }
}
