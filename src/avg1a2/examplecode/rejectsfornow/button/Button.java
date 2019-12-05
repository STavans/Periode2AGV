package avg1a2.examplecode.rejectsfornow.button;

import TI.BoeBot;

/**
 * A button is a sensor based object which will return true when pressed and false when not.
 */
public class Button {
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

    /**
     * Function to be able to be continuously called and give a signal to it's callback whenever it detects input.
     */
    public void update() {
        if(this.isActive()){
            buttonCallBack.onButtonPress();
        }
    }
}
