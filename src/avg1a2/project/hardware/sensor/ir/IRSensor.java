package avg1a2.project.hardware.sensor.ir;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.sensor.Sensor;

/**
 * this class uses the infrared sensor as an input sensor.
 * the IR signal will be converted into a 0 or 1 and this code is the specific button id such that
 * when pressed on the remote control, the BoeBot executes the correct action.
 */
public class IRSensor implements Sensor {
    private int pin;
    private IRCallback irCallback;

    public IRSensor(int pin, IRCallback irCallback) {
        this.pin = pin;
        this.irCallback = irCallback;
    }

    /**
     * receives the IR signal, puts this signal in an array, and reverses the signal such that
     * when converted a button id is generated.
     * @return returns the reversed IR signal array
     */
    public int[] scan(){
        int pulse[] = new int[12];
        for (int i = 0; i <12; i++) {
            pulse[i] = BoeBot.pulseIn(pin,false,20000);
        }
        return pulse;
    }

    /**
     * to convert the IR signal into a 0 or 1
     * @param signal uses the
     * @return returns the converted id for each individual button when pressed
     */
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

    /**
     * to check if the user pressed a new button on the remote control.
     */
    @Override
    public void update() {
        int pulseLen = BoeBot.pulseIn(pin,false,30000);
        if (pulseLen > 2000) {
            this.irCallback.onSignal(converter(scan()));
        }
    }
}
