package avg1a2.modules;

import TI.BoeBot;
import TI.PWM;
import TI.Timer;

/**
 * Synchronous Led objects are LEDs which alternate on or off on a asynchronous/alternating timer.
 * eg. it turns on for a different duration as for which it is off.
 */
public class ProductionLed implements LED {
    private int led;
    private int delay;
    private int offset;
    private boolean isOn = false;
    private Timer timer;
    private PWM brightness;

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the boebot.modules.LED.
     * @param input The pin the boebot.modules.LED is connected to.
     * @param delay The amount of delay (in milliseconds) the boebot.modules.LED will stay on.
     * @param offset The amount of delay (in milliseconds) the boebot.modules.LED will stay off.
     */
    public ProductionLed(int input, int delay, int offset) {
        this.led = input;
        this.delay = delay;
        this.offset = offset;
        this.brightness = new PWM(input,0);
        SetTimer(input);
    }

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the boebot.modules.LED.
     * @param input The pin the boebot.modules.LED is connected to.
     * @param delay The amount of delay (in milliseconds) the boebot.modules.LED will toggle on.
     */
    public ProductionLed(int input, int delay) {
        this(input,delay,delay);
    }

    /**
     * The constructor only sets the used attributes, but doesn't automatically run the boebot.modules.LED.
     * @param input The pin the boebot.modules.LED is connected to.
     */
    public ProductionLed(int input){
        this(input,0,0);
    }

    /**
     * Sets the delay to be used by the boebot.modules.LED (time for it to remain on with each loop).
     * @param delay delay in milliseconds.
     */
    public void SetDelay(int delay){
        this.delay = delay;
    }

    /**
     * Sets the offset to be used by the boebot.modules.LED (time for it to remain off with each loop).
     * @param offset offset in milliseconds.
     */
    public void SetOffset(int offset){
        this.offset = offset;
    }

    /**
     * Sets the brightness of the LEDs.
     * @param dutycycle dutycycle for the brightness.
     */
    public void SetBrightness(int dutycycle) {
        this.brightness.update(dutycycle);
    }

    /**
     * Turns the boebot.modules.LED on.
     */
    public void On() {
        BoeBot.digitalWrite(led, false);
        this.brightness.start();
        this.isOn = true;
    }

    /**
     * Turns the boebot.modules.LED off.
     */
    public void Off() {
        BoeBot.digitalWrite(led, true);
        this.isOn = false;
    }

    /**
     * Runs the boebot.modules.LED on a asynchronous loop.
     */
    public void Run() {
        if (timer.timeout()) {
            Toggle();
        }
    }

    /**
     * Sets the timer the Run loop will use.
     * @param delay The amount of delay (in milliseconds) between each toggle.
     */
    private void SetTimer(int delay) {
        this.timer = new Timer(delay);
        timer.mark();
    }

    /**
     * Toggles the current state of the boebot.modules.LED, saved in a boolean attribute.
     * Sets the new timer in order for each toggle to be set to a different delay.
     */
    private void Toggle() {
        if (isOn) {
            Off();
            SetTimer(this.offset);
        } else {
            On();
            SetTimer(this.delay);
        }
    }
}