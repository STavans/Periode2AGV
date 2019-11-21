package avg1a2.project.logic;

import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.modules.*;

class Program implements IRCallback{

    Program() {
    }

    void run() {
        IRSensor test = new IRSensor(1, this);
        while (true) {
            test.update();
        }
    }

    @Override
    public void onSignal(int signal) {
        System.out.println(signal);
    }
}
