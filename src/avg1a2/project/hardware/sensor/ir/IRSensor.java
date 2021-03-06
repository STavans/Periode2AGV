package avg1a2.project.hardware.sensor.ir;

import TI.BoeBot;
import avg1a2.project.hardware.Component;

/**
 * this class uses the infrared sensor as an input sensor.
 * the IR signal will be converted into a 0 or 1 and this code is the specific button id such that
 * when pressed on the remote control, the BoeBot executes the correct action.
 */
public class IRSensor implements Component {
    private int pin;
    private IRCallback irCallback;

    /**
     * Constructor to set the pin and callback for the sensor.
     * @param pin Pin on which the sensor is connected.
     * @param irCallback Callback for the Sensor to use when it detects a signal.
     */
    public IRSensor(int pin, IRCallback irCallback) {
        this.pin = pin;
        this.irCallback = irCallback;
    }

    /**
     * receives the IR signal, puts this signal in an array, and reverses the signal such that
     * when converted a button id is generated.
     * @return returns the reversed IR signal array
     */
    private int[] scan(){
        int[] pulse = new int[12];
        for (int i = 0; i <12; i++) {
            pulse[i] = BoeBot.pulseIn(this.pin,false,20000);
        }
        return pulse;
    }

    /**
     * to convert the IR signal into a 0 or 1
     * @param signal uses the
     * @return returns the converted id for each individual button when pressed
     */
    private int converter (int[] signal) {
        int id = 0;
        int bit;
        int counter = 0;

        for (int i : signal) {
            if (i > 1000) { //1000 has been selected as the threshold to separate true and false from analog values.
                bit = 1;
            } else if (i < 0){
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
