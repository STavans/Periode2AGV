package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothCallback;
import avg1a2.project.logic.State;
import avg1a2.project.modules.irconversion.IRConversion;
import avg1a2.project.modules.irconversion.IROverridable;

public class BlueBotControl implements BluetoothCallback, IROverridable {
    private IRConversion irConversion;
    private RouteControl routeControl;
    private Component bluetoothSensor;
    private State programState;

    public BlueBotControl(RouteControl routeControl) {
        this.routeControl = routeControl;
    }

    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    public void setBluetoothSensor(Component bluetoothSensor) {
        this.bluetoothSensor = bluetoothSensor;
    }

    public void setProgramState(State programState) {
        this.programState = programState;
    }

    public void run() throws RuntimeException {
        if (routeControl == null) {
            throw new RuntimeException("Route Control has not been initialized");
        }
        routeControl.run();

        if (irConversion == null) {
            throw new RuntimeException("IrConversion has not been initialized");
        }
        irConversion.update();

        if (this.bluetoothSensor == null) {
            throw new RuntimeException("BluetoothSensor has not been initialized");
        }
        bluetoothSensor.update();
    }

    @Override
    public void onSignal(int data) {
        if (data == 119) {
            routeControl.startRoute();
        }
    }

    @Override
    public void override() {
        System.out.println("Override");
        this.routeControl.stop();
        this.programState.setState("Override");
    }
}
