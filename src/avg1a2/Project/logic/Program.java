package avg1a2.project.logic;

<<<<<<< HEAD
=======
import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.hardware.sensor.ir.IRSensor;
>>>>>>> master

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
