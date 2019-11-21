package avg1a2.project.hardware.sensor.ir;

import TI.BoeBot;
import avg1a2.project.hardware.sensor.Sensor;


public class IRSensor implements Sensor {

    private int pin;
    private IRCallback irCallback;

    public IRSensor(int pin, IRCallback irCallback) {
        this.pin = pin;
        this.irCallback = irCallback;
    }

    public int[] scan(){

        int pulse[] = new int[12];
        for (int i = 0; i <12; i++) {
            pulse[i] = BoeBot.pulseIn(8,false,20000);
        }
        return pulse;
    }

    private int converter (int signal[]) {

        int id = 0;
        int bit;
        int counter = 0;

        for (int i = 0; i < signal.length; i++) {
            if (signal[i] > 1000) {
                bit = 1;
            } else if (signal[i] < 0){
                return -1;
            } else {
                bit = 0;
            }

            if (bit == 1) {
                id = id | 1 << counter;
            }
            counter++;
        }
        return id;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void update() {
        int pulseLength = BoeBot.pulseIn(8, false, 30000);

        if (pulseLength > 2000){
        this.irCallback.onSignal(converter(scan()));
        }
    }
}
