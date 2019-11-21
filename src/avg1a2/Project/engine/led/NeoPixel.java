package avg1a2.project.engine.led;

import TI.BoeBot;
import TI.Timer;

/**
 * Class to control the neopixel leds on the BoeBot.
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
     * The constructor only sets the used attributes, but doesn't automatically run the neopixel.
     * @param pin The pin the neopixel is connected to.
     * @param delay The amount of delay (in milliseconds) the neopixel will stay on.
     * @param offset The amount of delay (in milliseconds) the neopixel will stay off.
     * @param r The amount of red in the rgb standard.
     * @param g The amount of green in the rgb standard.
     * @param b The amount of blue in the rgb standard.
     */
    public NeoPixel(int pin, int delay, int offset, int r, int g, int b){
        this.pin = pin;
        this.delay = delay;
        this.offset = offset;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public NeoPixel(int pin, int delay, int r, int g, int b){
        this(pin,delay,delay,r,g,b);
    }

    public NeoPixel(int pin, int delay, int offset){
        this(pin,delay,offset,255,255,255); //rgb default is white.
    }

    public NeoPixel(int pin, int delay){
        this(pin,delay,delay,255,255,255);
    }

    public NeoPixel(int pin, int r, int g, int b){
        this(pin,0,0,r,g,b); //delay default is 0;
    }

    public NeoPixel(int pin){
        this(pin,0,0,255,255,255);
    }

    /**
     * Turns the neopixel on;
     */
    public void on() {
        BoeBot.rgbSet(pin,r,g,b);
        this.isOn = true;
    }

    /**
     * Turn the neopixel off;
     */
    public void off() {
        BoeBot.rgbSet(pin,0,0,0);
        this.isOn = false;
    }

    /**
     * Sets the delay to be used by the neopixel (time for it to remain on with each loop).
     * @param delay delay in milliseconds.
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Sets the offset to be used by the neopixel (time for it to remain off with each loop).
     * @param offset offset in milliseconds.
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Checks if the led is ready to be updated, if so, toggles the state.
     */
    public void update() {
        if (timer.timeout() || timer == null) {
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
