package avg1a2.project.modules.bluetoothconversion;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothCallback;
import avg1a2.project.modules.data.Route;

/**
 * Converts the bluetooth ascii characters it receives into a certain command, managed through the callbacks.
 */
public class BluetoothConversion implements BluetoothCallback {
    private Component bluetoothSensor;
    private BluetoothConversionCallback callback;
    private Route route;

    /**
     * Constructor which sets the callback.
     * @param callback Callback on which to send a signal on a valid command.
     */
    public BluetoothConversion(BluetoothConversionCallback callback) {
        this.callback = callback;
    }

    /**
     * Sets the bluetooth sensor to use in order to detect signals.
     * @param bluetoothSensor Bluetooth Sensor hardware component.
     */
    public void setBluetoothSensor(Component bluetoothSensor) {
        this.bluetoothSensor = bluetoothSensor;
    }

    /**
     * Updatable function with checks the bluetooth Sensor for a signal.
     * @throws RuntimeException Gives an exception if the sensor is not set.
     */
    public void update() throws RuntimeException {
        if (this.bluetoothSensor == null) {
            throw new RuntimeException("Bluetooth Sensor has not been initialized");
        }
        this.bluetoothSensor.update();
    }

    /**
     * OnSignal function necessary for the conversion into actual commands.
     * @param command the String version of the ascii code.
     */
    @Override
    public void onSignal(String command) {
        switch (command) {
            case "B" : //starts the route construction process.
                this.route = new Route();
                break;
            case "w" : //adds forward step to route.
                buildRoute(command);
                break;
            case "d" : //adds right step to route.
                buildRoute(command);
                break;
            case "a" : //adds left step to route.
                buildRoute(command);
                break;
            case "s" : //adds stop step to route.
                buildRoute(command);
                break;
            case "e" : //ends the route construction process.
                this.route.addStep("End");
                this.callback.newRoute(this.route);
                break;
            case "S" : //starts the route
                this.callback.startRoute();
                break;
            case "C" : //cancels the route
                this.callback.cancelRoute();
                break;
            case "R" : //resumes the route
                this.callback.resumeRoute();
                break;
            case "P" : //pauses the route.
                this.callback.pauseRoute();
                break;
        }
    }

    /**
     * Builds the route depending on the supplied command.
     * @param step String which will define which step to add.
     */
    private void buildRoute(String step) {
        switch (step) {
            case "w":
                this.route.addStep("Forward");
                break;
            case "a":
                this.route.addStep("Left");
                break;
            case "d":
                this.route.addStep("Right");
                break;
            case "s":
                this.route.addStep("Stop");
                break;
        }
    }
}
