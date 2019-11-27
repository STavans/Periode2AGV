package avg1a2.tests;

import TI.BoeBot;
import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.modules.irconversion.IRConversion;

public class Program implements IRCallback {
    public Program() {
    }

    public void run() {

        RemoteControlTest remoteControlTest = new RemoteControlTest();
        IRConversion irConversion = new IRConversion(remoteControlTest);
        IRSensor irSensor = new IRSensor(15,irConversion);
        while (true){
            irSensor.update();
            BoeBot.wait(1);
        }
    }

    @Override
    public void onSignal(int signal) {
        System.out.println(Integer.toBinaryString(signal));
    }
}
