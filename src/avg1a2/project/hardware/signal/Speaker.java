package avg1a2.project.hardware.signal;

import TI.BoeBot;
import avg1a2.project.hardware.Component;

public class Speaker implements Component {
    private int frequency;
    private int time;
    private int pin;

    /**
     * Constructor sets the pin of the speaker, the frequency and the time frame.
     * @param pin The pin the boebot.modules.Speaker is connected to.
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
    public void Beep() {
        BoeBot.freqOut(pin,frequency,time);
    }

    public void update() {
        Beep();
    }
}
