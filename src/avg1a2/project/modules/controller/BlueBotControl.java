package avg1a2.project.modules.controller;

import avg1a2.project.logic.State;
import avg1a2.project.modules.bluetoothconversion.BluetoothConversion;
import avg1a2.project.modules.bluetoothconversion.BluetoothConversionCallback;
import avg1a2.project.modules.data.Route;
import avg1a2.project.modules.irconversion.IRConversion;
import avg1a2.project.modules.irconversion.IROverridable;

public class BlueBotControl implements BluetoothConversionCallback, IROverridable {
    private IRConversion irConversion;
    private RouteControl routeControl;
    private BluetoothConversion bluetoothConversion;
    private State programState;

    public BlueBotControl(RouteControl routeControl) {
        this.routeControl = routeControl;
    }

    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    public void setBluetoothConversion(BluetoothConversion bluetoothConversion) {
        this.bluetoothConversion = bluetoothConversion;
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

        if (this.bluetoothConversion == null) {
            throw new RuntimeException("BluetoothConversion has not been initialized");
        }
        bluetoothConversion.update();
    }

    @Override
    public void override() {
        this.routeControl.stop();
        this.programState.setState("Override");
    }

    @Override
    public void startRoute() {
        routeControl.start();
    }

    @Override
    public void cancelRoute() {
        routeControl.stop();
    }

    @Override
    public void resumeRoute() {
        routeControl.resume();
    }

    @Override
    public void pauseRoute() {
        routeControl.pause();
    }

    @Override
    public void newRoute(Route route) {
        routeControl.setRoute(route);
    }
}
