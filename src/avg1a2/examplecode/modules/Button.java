package avg1a2.examplecode.modules;

import TI.BoeBot;

/**
 * A button is a sensor based object which will return true when pressed and false when not.
 */
public class Button implements Sensor {
    private int button;

    /**
     * Constructor which sets the pin to read button status.
     * @param input pin on which the button is connected.
     */
    public Button(int input) {
        this.button = input;
    }

    /**
     * Function to check if the button is pressed or inactive.
     * @return boolean true or false, true if pressed, false if not.
     */
    public boolean isActive() {
        return !BoeBot.digitalRead(button);
    }
}
