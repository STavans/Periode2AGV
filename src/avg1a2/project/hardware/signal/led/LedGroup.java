package avg1a2.project.hardware.signal.led;

import TI.Timer;
import java.util.HashMap;

/**
 * Class to run several LEDs on the same timer, or manually turn them on or off.
 */
public class LedGroup implements LED{
    private HashMap<String,LED> group = new HashMap<>();
    private Timer timer;
    private int delay;
    private int offset;
    private boolean isOn = false;

    /**
     * Constructor sets a delay and offset to use, but does not automatically run.
     * @param delay The amount of delay (in milliseconds) the led will stay on.
     * @param offset The amount of delay (in milliseconds) the led will stay off.
     */
    private LedGroup(int delay, int offset){
        this.delay = delay;
        this.offset = offset;
    }

    /**
     * Constructor sets a delay and offset to use, but does not automatically run.
     * @param delay The amount of delay (in milliseconds) the led will stay on.
     */
    public LedGroup(int delay){
        this(delay,delay); //If no offset is given, it is set the same value as the delay for equal timings.
    }

    public LedGroup() {
        this(0,0);
    } //delay default is 0;

    /**
     * Adds a led to the group.
     * @param name The name of the led to be added.
     * @param led The led to be added.
     */
    public void addLed(String name, LED led){
        this.group.put(name,led);
    }

    /**
     * Turns the group on.
     */
    public void on() {
        for (String led : group.keySet()) {
            group.get(led).on();
        }
        this.isOn = true;
    }

    /**
     * Turns the group off.
     */
    public void off() {
        for (String led : group.keySet()) {
            group.get(led).off();
        }
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
     * Toggles the current state of the group, saved in a boolean attribute.
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
