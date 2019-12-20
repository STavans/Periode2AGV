package avg1a2.project.modules.bluetoothconversion;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothCallback;
import avg1a2.project.modules.data.Route;

public class BluetoothConversion implements BluetoothCallback {
    private Component bluetoothSensor;
    private BluetoothConversionCallback callback;
    private Route route;


    public BluetoothConversion(BluetoothConversionCallback callback) {
        this.callback = callback;
    }

    public void setBluetoothSensor(Component bluetoothSensor) {
        this.bluetoothSensor = bluetoothSensor;
    }

    public void update() throws RuntimeException {
        if (bluetoothSensor == null) {
            throw new RuntimeException("Bluetooth Sensor has not been initialized");
        }
        bluetoothSensor.update();
    }

    @Override
    public void onSignal(String command) {
        System.out.println(command);
        switch (command) {
            case "B" :
                System.out.println("Begin");
                route = new Route();
                break;
            case "w" :
                System.out.println("Forward");
                buildRoute(command);
                break;
            case "a" :
                System.out.println("Left");
                buildRoute(command);
                break;
            case "d" :
                System.out.println("Right");
                buildRoute(command);
                break;
            case "s" :
                System.out.println("Stop");
                buildRoute(command);
                break;
            case "e" :
                route.addStep("End");
                callback.newRoute(route);
                System.out.println("End");
                break;
            case "S" :
                callback.startRoute();
                break;
            case "C" :
                callback.cancelRoute();
                break;
            case "R" :
                callback.resumeRoute();
                break;
            case "P" :
                callback.pauseRoute();
                break;
        }
    }

    private void buildRoute(String step) {
        switch (step) {
            case "w":
                route.addStep("Forward");
                break;
            case "a":
                route.addStep("Left");
                break;
            case "d":
                route.addStep("Right");
                break;
            case "s":
                route.addStep("Stop");
                break;
        }
    }
}