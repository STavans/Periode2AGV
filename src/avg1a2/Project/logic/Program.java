package avg1a2.project.logic;

import TI.BoeBot;
<<<<<<< HEAD
import avg1a2.project.hardware.sensor.Sensor;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.signal.led.IndividualLed;
import avg1a2.project.hardware.signal.led.LED;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.*;

=======
import avg1a2.project.hardware.sensor.ultrasonic.Test;
import avg1a2.project.modules.*;

class Program implements UltraSonicCallback {
>>>>>>> master

class Program implements UltraSonicCallback, Sensor {
    Program() {
    }

    void run() {
<<<<<<< HEAD

//        LED test1 = new IndividualLed(2);
//        LED test2 = new LedGroup();
//        LED test3 = new NeoPixel(1);

        while (true) {
//            try {
//                test1.update();
//                test2.update();
//                test3.update();
//            } catch (RuntimeException runtimeException) {
//                System.out.println(runtimeException);
//            }
            UltrasonicSensor sensor = new UltrasonicSensor(0, 1, 100000, 10000, this, true);
            sensor.detectObject();
=======
        Test test = new Test(this);

        while (true) {
            test.update();
>>>>>>> master
            BoeBot.wait(1);
        }
    }

    @Override
<<<<<<< HEAD
    public void onUltraSonic() {


    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void update() {

=======
    public void onSignal() {
        System.out.println("Distance is lower than 50");
>>>>>>> master
    }
}
