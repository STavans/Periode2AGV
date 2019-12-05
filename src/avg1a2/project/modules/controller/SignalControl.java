package avg1a2.project.modules.controller;


import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.signal.led.LedGroup;

//TO DO
//
public class SignalControl {
    private LedGroup turnLeftLEDs;
    private LedGroup turnRightLEDs;
    private LedGroup idle;
    private LedGroup collision;
    private LedGroup setLEDs;
    private Component warningSpeaker;

    /**
     * @param idle The LedGroup to use whenever no collision has been detected.
     * @param collision The LedGroup to use whenever a collision has been detected.
     * @param warningSpeaker Speaker to use whenever a collision has been detected.
     */
    public SignalControl(LedGroup idle, LedGroup collision, LedGroup turnRightLEDs, LedGroup turnLeftLEDs,  Component warningSpeaker) {
        this.turnRightLEDs = turnRightLEDs;
        this.turnLeftLEDs = turnLeftLEDs;
        this.setLEDs = setLEDs;
        this.idle = idle;
        this.collision = collision;
        this.warningSpeaker = warningSpeaker;
        idle.on();
    }

    public void boeBotOn() {
        this.idle.on();
    }

    public void setLed(){
        this.setLEDs.on();
    }

    public void boeBotCollision() {
        this.collision.on();
    }

    public void turnLeftLED() {
        this.turnLeftLEDs.on();
    }

    public void turnRightLED() {
        this.turnRightLEDs.on();
    }

    public void setWarningSpeakerOn() {
        this.warningSpeaker.update();
    }
}
