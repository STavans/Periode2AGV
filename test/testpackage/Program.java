package testpackage;

import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.hardware.sensor.ir.IRSensor;

public class Program implements IRCallback {
    public Program() {
    }

    public void run() {
        IRSensor test = new IRSensor(15, this);
        while (true){
            test.update();
        }
    }

    @Override
    public void onSignal(int signal) {
        System.out.println(signal);
    }
}
