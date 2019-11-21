package avg1a2.examplecode.modules;

import TI.Timer;

import java.util.ArrayList;

/**
 * Class to run several LEDs on the same timer, or manually turn them on or off.
 */
public class LedGroup implements LED{
    private ArrayList<LED> group = new ArrayList<>();
    private boolean isOn = false;
    private Timer timer;
    private int delay;
    private int offset;

    /**
     * Constructor sets a delay and offset to use, but does not automatically run.
     * @param delay The amount of delay (in milliseconds) the boebot.modules.led will stay on.
     * @param offset The amount of delay (in milliseconds) the boebot.modules.led will stay off.
     */
    public LedGroup(int delay, int offset){
        this.delay = delay;
        this.offset = delay;
        SetTimer(delay);
    }

    /**
     * Constructor sets a default delay to use, but doesn't automatically run.
     * @param delay The amount of delay (in milliseconds) the boebot.modules.led will stay on.
     */
    public LedGroup(int delay){
        this(delay,delay);
    }

    /**
     * Constructor sets default variables with empty input.
     */
    public LedGroup() {
        this(0,0);
    }

    /**
     * Adds a led to the group.
     * @param led The boebot.modules.led to be added.
     */
    public void AddLed(LED led){
        this.group.add(led);
    }

    /**
     * Searches and removes the given led from the group.
     * @param led The boebot.modules.led to be removed.
     */
    public void RemoveLed(LED led){
        for (LED search : group) {
            if (search == led) {
                this.group.remove(led);
            }
        }
    }

    /**
     * Sets the delay to be used by the boebot.modules.led (time for it to remain on with each loop).
     * @param delay delay in milliseconds.
     */
    public void SetDelay(int delay){
        this.delay = delay;
    }

    /**
     * Sets the offset to be used by the boebot.modules.led (time for it to remain off with each loop).
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
        for (LED led : this.group) {
            led.SetBrightness(dutycycle);
        }
    }

    /**
     * Turns the group on.
     */
    public void On() {
        for (LED led : group) {
            led.On();
        }
        this.isOn = true;
    }

    /**
     * Turns the group off.
     */
    public void Off() {
        for (LED led : group) {
            led.Off();
        }
        this.isOn = false;
    }

    /**
     * Runs the group on a loop.
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
     * Toggles the current state of the boebot.modules.led, saved in a boolean attribute.
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
