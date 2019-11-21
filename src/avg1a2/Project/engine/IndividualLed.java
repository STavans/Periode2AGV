package avg1a2.project.engine;

import TI.BoeBot;
import TI.PWM;
import TI.Timer;

/**
 * Synchronous Led objects are LEDs which alternate on or off on a asynchronous/alternating timer.
 * eg. it turns on for a different duration as for which it is off.
 */
public class IndividualLed implements LED {
    private int pin;
    private int delay;
    private int offset;
    private boolean isOn = false;
    private Timer timer;
    private PWM brightness;


    /**
     * The constructor only sets the used attributes, but doesn't automatically run the boebot.modules.LED.
     * @param pin The pin the boebot.modules.LED is connected to.
     * @param delay The amount of delay (in milliseconds) the boebot.modules.LED will stay on.
     * @param offset The amount of delay (in milliseconds) the boebot.modules.LED will stay off.
     */
    public IndividualLed(int pin, int delay, int offset){
        this.pin = pin;
        this.delay = delay;
        this.offset = offset;
        this.brightness = new PWM(pin,0);
        setTimer(delay);
    }

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the boebot.modules.LED.
     * @param input The pin the boebot.modules.LED is connected to.
     * @param delay The amount of delay (in milliseconds) the boebot.modules.LED will toggle on.
     */
    public IndividualLed(int input, int delay) {
        this(input,delay,delay);
    }

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the boebot.modules.LED.
     * @param input The pin the boebot.modules.LED is connected to.
     */
    public IndividualLed(int input){
        this(input,0,0);
    }

    /**
     * Turns the led on.
     */
    public void on() {
        BoeBot.digitalWrite(pin, false);
        this.brightness.start();
        this.isOn = true;
    }

    /**
     * Turns the led off.
     */
    public void off() {
        BoeBot.digitalWrite(pin, true);
        this.isOn = false;
    }

    /**
     * Sets the delay to be used by the boebot.modules.LED (time for it to remain on with each loop).
     * @param delay delay in milliseconds.
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Sets the offset to be used by the boebot.modules.LED (time for it to remain off with each loop).
     * @param offset offset in milliseconds.
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Sets the brightness of the LEDs.
     * @param brightness dutycycle for the brightness.
     */
    public void setBrightness(int brightness) {

    }

    /**
     * Checks if the led is ready to be updated, if so, toggles the state.
     */
    public void update() {
        if (timer.timeout()) {
            toggle();
        }
    }

    /**
     * Sets the timer the Run loop will use.
     * @param delay The amount of delay (in milliseconds) between each toggle.
     */
    private void setTimer(int delay) {
        this.timer = new Timer(delay);
        timer.mark();
    }

    /**
     * Toggles the current state of the boebot.modules.LED, saved in a boolean attribute.
     * Sets the new timer in order for each toggle to be set to a different delay.
     */
    private void toggle() {
        if (isOn) {
            off();
            setTimer(this.offset);
        } else {
            on();
            setTimer(this.delay);
        }
    }
}
