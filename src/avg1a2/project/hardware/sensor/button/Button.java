package avg1a2.project.hardware.sensor.button;

import TI.BoeBot;
import avg1a2.project.hardware.sensor.Sensor;

/**
 * A button is a sensor based object which will return true when pressed and false when not.
 */
public class Button implements Sensor {
    private ButtonCallback buttonCallBack;
    private int button;

    /**
     * Constructor which sets the pin to read button status.
     * @param pin pin on which the button is connected.
     */
    public Button(int pin, ButtonCallback buttonCallBack) {
        this.button = pin;
        this.buttonCallBack = buttonCallBack;
    }

    /**
     * Function to check if the button is pressed or inactive.
     * @return boolean true or false, true if pressed, false if not.
     */
    public boolean isActive() {
        return !BoeBot.digitalRead(button);
    }

    @Override
    public void update() {
        if(this.isActive()){
            buttonCallBack.onButtonPress();
        }
    }
}
