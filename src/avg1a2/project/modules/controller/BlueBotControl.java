package avg1a2.project.modules.controller;

import avg1a2.project.logic.State;
import avg1a2.project.modules.bluetoothconversion.BluetoothConversion;
import avg1a2.project.modules.bluetoothconversion.BluetoothConversionCallback;
import avg1a2.project.modules.data.Route;
import avg1a2.project.modules.irconversion.IRConversion;
import avg1a2.project.modules.irconversion.IROverridable;

/**
 * Controller to setup bluetooth connections and top level manager of the routeControl.
 */
public class BlueBotControl implements BluetoothConversionCallback, IROverridable {
    private IRConversion irConversion;
    private RouteControl routeControl;
    private SignalControl signalControl;
    private BluetoothConversion bluetoothConversion;
    private State programState;

    /**
     * Constructor to set the routeControl to manage and the SignalControl to notify.
     * @param routeControl RouteControl to mange.
     * @param signalControl SignalControl to notify.
     */
    public BlueBotControl(RouteControl routeControl, SignalControl signalControl) {
        this.routeControl = routeControl;
        this.signalControl = signalControl;
    }

    /**
     * IrConversion to update to check if override mode needs to be enabled.
     * @param irConversion The IrConversion to use.
     */
    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    /**
     * Sets the bluetoothConversion to use.
     * @param bluetoothConversion BluetoothConversion to use.
     */
    public void setBluetoothConversion(BluetoothConversion bluetoothConversion) {
        this.bluetoothConversion = bluetoothConversion;
    }

    /**
     * Sets the program State to change if override mode is enabled.
     * @param programState State to affect.
     */
    public void setProgramState(State programState) {
        this.programState = programState;
    }

    /**
     * Runnable object to update all it's components.
     * @throws RuntimeException Throws and error if a component is not set.
     */
    public void run() throws RuntimeException {
        if (this.routeControl == null) {
            throw new RuntimeException("Route Control has not been initialized");
        }
        this.routeControl.run();

        if (this.irConversion == null) {
            throw new RuntimeException("IrConversion has not been initialized");
        }
        this.irConversion.update();

        if (this.bluetoothConversion == null) {
            throw new RuntimeException("BluetoothConversion has not been initialized");
        }
        this.bluetoothConversion.update();
    }

    /**
     * Override function to be called whenever the remote takes over control.
     */
    @Override
    public void override() {
        this.routeControl.pause();
        this.programState.setState("Override");
        this.signalControl.remoteControl();
    }

    /**
     * Starts the route from bluetooth conversion.
     */
    @Override
    public void startRoute() {
        this.routeControl.start();
    }

    /**
     * Cancels the route from bluetooth conversion.
     */
    @Override
    public void cancelRoute() {
        this.routeControl.stop();
    }

    /**
     * Resume the route from bluetooth conversion.
     */
    @Override
    public void resumeRoute() {
        this.routeControl.resume();
    }

    /**
     * Pauses the route from bluetooth conversion.
     */
    @Override
    public void pauseRoute() {
        this.routeControl.pause();
    }

    /**
     * Sets a new route as the active BoeBot route.
     * @param route Route to set as active.
     */
    @Override
    public void newRoute(Route route) {
        this.routeControl.setRoute(route);
    }
}
