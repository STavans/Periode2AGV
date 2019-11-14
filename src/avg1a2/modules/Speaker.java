package avg1a2.modules;

import TI.BoeBot;

/**
 * Sets and stores a speaker object used to sound beeps.
 */
public class Speaker {
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

    /**
     * Set method to change to frequency.
     * @param frequency Frequency of the sound created by the beeps.
     */
    public void SetFrequency(int frequency) {
        this.frequency = frequency;
        System.out.println(this.frequency);
    }
}
