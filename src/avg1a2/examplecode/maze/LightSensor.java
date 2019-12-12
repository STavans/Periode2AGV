package avg1a2.examplecode.maze;

import TI.BoeBot;

public class LightSensor {
    private int pin;

    public LightSensor(int pin) {
        this.pin = pin;
    }

    public int read() {
        return BoeBot.analogRead(pin);
    }


}
