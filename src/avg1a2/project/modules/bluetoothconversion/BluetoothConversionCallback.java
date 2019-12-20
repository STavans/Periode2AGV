package avg1a2.project.modules.bluetoothconversion;

import avg1a2.project.modules.data.Route;

public interface BluetoothConversionCallback {
    void startRoute();
    void cancelRoute();
    void resumeRoute();
    void pauseRoute();
    void newRoute(Route route);
}
