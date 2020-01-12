package avg1a2.project.hardware.signal;

import TI.BoeBot;
import avg1a2.project.hardware.Component;

/**
 * A sound making device which can be used to signal states or events.
 */
public class Speaker implements Component {
    private int frequency;
    private int time;
    private int pin;

    /**
     * Constructor sets the pin of the speaker, the frequency and the time frame.
     * @param pin The pin the Speaker is connected to.
     * @param frequency Frequency of the sound created by the beeps.
     * @param time Time of each beep in milliseconds.
     */
    public Speaker(int pin, int frequency, int time){
        this.frequency = frequency;
        this.time = time;
        this.pin = pin;
    }

    /**
     * Call to sound a beep with the set parameters.
     */
    private void beep() {
        BoeBot.freqOut(this.pin,this.frequency,this.time);
    }

    /**
     * Updatable function which can be called continuously.
     */
    public void update() {
        beep();
    }
}
