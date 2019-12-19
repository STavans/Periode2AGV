package avg1a2.project.hardware.signal.led;

import TI.BoeBot;
import TI.Timer;

/**
 * Class to control the NeoPixel LEDs on the BoeBot.
 */
public class NeoPixel implements LED{
    private int pin;
    private int delay;
    private int offset;
    private int r;
    private int g;
    private int b;
    private boolean isOn;
    private Timer timer;

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the NeoPixel.
     * @param pin The pin the NeoPixel is connected to.
     * @param delay The amount of delay (in milliseconds) the NeoPixel will stay on.
     * @param offset The amount of delay (in milliseconds) the NeoPixel will stay off.
     * @param r The amount of red in the rgb standard.
     * @param g The amount of green in the rgb standard.
     * @param b The amount of blue in the rgb standard.
     */
    private NeoPixel(int pin, int delay, int offset, int r, int g, int b){
        this.pin = pin;
        this.delay = delay;
        this.offset = offset;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the NeoPixel.
     * @param pin The pin the NeoPixel is connected to.
     * @param delay The amount of delay (in milliseconds) the NeoPixel will stay on.
     * @param r The amount of red in the rgb standard.
     * @param g The amount of green in the rgb standard.
     * @param b The amount of blue in the rgb standard.
     */
    public NeoPixel(int pin, int delay, int r, int g, int b){
        this(pin,delay,delay,r,g,b);
    }

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the NeoPixel.
     * @param pin The pin the NeoPixel is connected to.
     * @param r The amount of red in the rgb standard.
     * @param g The amount of green in the rgb standard.
     * @param b The amount of blue in the rgb standard.
     */
    public NeoPixel(int pin, int r, int g, int b){
        this(pin,0,0,r,g,b); //delay default is 0;
    }

    /**
     * Turns the NeoPixel on;
     */
    public void on() {
        BoeBot.rgbSet(pin,r,g,b);
        BoeBot.rgbShow();
        this.isOn = true;
    }

    /**
     * Turn the NeoPixel off;
     */
    public void off() {
        BoeBot.rgbSet(pin,0,0,0);
        BoeBot.rgbShow();
        this.isOn = false;
    }

    /**
     * Checks if the led is ready to be updated, if so, toggles the state.
     */
    public void update() throws RuntimeException {
        if (!(delay > 0)) {
            throw new RuntimeException("Delay has not been set");
        } else if (timer == null || timer.timeout()) {
            toggle();
        }
    }

    /**
     * Sets the timer the Run loop will use.
     * @param delay The amount of delay (in milliseconds) between each toggle.
     */
    private void setTimer(int delay) {
        if (delay > 0) {
            this.timer = new Timer(delay);
            timer.mark();
        }
    }

    /**
     * Toggles the current state of the NeoPixel saved in a boolean attribute.
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
