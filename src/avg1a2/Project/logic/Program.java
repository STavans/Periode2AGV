package avg1a2.project.logic;

import TI.BoeBot;
import avg1a2.project.hardware.sensor.ultrasonic.Test;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.modules.*;

class Program implements UltraSonicCallback {

    Program() {
    }

    void run() {
        UltrasonicSensor sensor = new UltrasonicSensor(0,1, this, true);

        while (true) {
            sensor.calculateDistance();
            BoeBot.wait(1);
        }
    }
    
    @Override
    public void onUltraSonic() {

    }
}