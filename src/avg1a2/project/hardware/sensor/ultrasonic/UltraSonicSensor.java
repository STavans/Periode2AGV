package avg1a2.project.hardware.sensor.ultrasonic;

import TI.BoeBot;
import avg1a2.project.hardware.Component;

/**
 * Abstract class to define an UltraSonic Sensor, which uses sound pulses to detect collisions.
 */
public abstract class UltraSonicSensor implements Component {
    private int trigger;
    private int echo;

    /**
     * Default constructor for all UltraSonicSensors.
     * @param trigger The pin which to use for the UltraSonic trigger.
     * @param echo The pin which to use for the UltraSonic echo.
     */
    UltraSonicSensor(int trigger, int echo) {
        this.trigger = trigger;
        this.echo = echo;
    }

    /**
     This method calculates the distance of the ultrasonic sensor's pulse length, if the pulse is greater than 100 it returns a zero.
     That is to test if the sensor works correctly
     **/
    int calculateDistance(int pulse) {
        if (pulse > 100) {
            return pulse / 58;
        }
        else return 100;
    }

    /**
     This method gets the pulse length of the ultrasonic, it does so by triggering a pulse
     and returning the value of it.
     **/
    int ultraSonicPulse() {
        BoeBot.digitalWrite(this.trigger, true);
        BoeBot.uwait(1);
        BoeBot.digitalWrite(this.trigger, false);
        int pulse = BoeBot.pulseIn(this.echo, true, 10000);
        if (pulse > 100) {
            return pulse;
        } else {
            return 0;
        }
    }

    /**
     * Abstract function update, is required for all components and thus will be needed for all UltraSonic Sensors,
     * but they change depending on position due to callback separation, eliminating any use for it to be predefined.
     */
    public abstract void update();
}
