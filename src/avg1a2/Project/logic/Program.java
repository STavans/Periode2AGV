package avg1a2.project.logic;

import TI.BoeBot;
import avg1a2.project.hardware.sensor.ultrasonic.Test;
import avg1a2.project.modules.*;

class Program implements UltraSonicCallback {

    Program() {
    }

    void run() {
        Test test = new Test(this);

        while (true) {
            test.update();
            BoeBot.wait(1);
        }
    }

    @Override
    public void onSignal() {
        System.out.println("Distance is lower than 50");
    }
}
