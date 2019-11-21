package avg1a2.project.logic;

<<<<<<< HEAD
import TI.BoeBot;
import avg1a2.project.engine.Ultrasonic;
=======
import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.hardware.sensor.ir.IRSensor;
>>>>>>> master
import avg1a2.project.modules.*;

class Program implements IRCallback{

    Program() {
    }

    void run() {
<<<<<<< HEAD


=======
        IRSensor test = new IRSensor(1, this);
    }

    @Override
    public void onSignal(int signal) {
        System.out.println(signal);
>>>>>>> master
    }
}
