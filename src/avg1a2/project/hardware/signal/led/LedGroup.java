package avg1a2.project.hardware.signal.led;

import TI.Timer;
import java.util.HashMap;

/**
 * Class to run several LEDs on the same timer, or manually turn them on or off.
 */
public class LedGroup implements LED{
    private HashMap<String,LED> group = new HashMap<>();
    private boolean isOn = false;
    private Timer timer;
    private int delay;
    private int offset;

    /**
     * Constructor sets a delay and offset to use, but does not automatically run.
     * @param delay The amount of delay (in milliseconds) the led will stay on.
     * @param offset The amount of delay (in milliseconds) the led will stay off.
     */
    public LedGroup(int delay, int offset){
        this.delay = delay;
        this.offset = delay;
    }

    public LedGroup(int delay){
        this(delay,delay);
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
     * Searches and removes the given led from the group.
     * @param name Name of the led.
     */
    public void removeLed(String name){
        this.group.remove(name);
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
     * Sets the delay to be used by the group (time for it to remain on with each loop).
     * @param delay delay in milliseconds.
     */
    public void setDelay(int delay){
        this.delay = delay;
    }

    /**
     * Sets the offset to be used by the group (time for it to remain off with each loop).
     * @param offset offset in milliseconds.
     */
    public void setOffset(int offset){
        this.offset = offset;
    }

    /**
     * Checks if the led is ready to be updated, if so, toggles the state.
     */
    public void update() throws RuntimeException {
        if (!(delay > 0)) {
            throw new RuntimeException("Delay has not been set");
        } else if (timer.timeout() || timer == null) {
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
