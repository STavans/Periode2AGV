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

    public SignalControl() {
        //this.setLEDs = setLEDs;
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

    public void setTurnLeftLEDs(LedGroup turnLeftLEDs) {
        this.turnLeftLEDs = turnLeftLEDs;
    }

    public void setTurnRightLEDs(LedGroup turnRightLEDs) {
        this.turnRightLEDs = turnRightLEDs;
    }

    public void setIdle(LedGroup idle) {
        this.idle = idle;
        idle.on();
    }

    public void setCollision(LedGroup collision) {
        this.collision = collision;
    }

    public void setSetLEDs(LedGroup setLEDs) {
        this.setLEDs = setLEDs;
    }

    public void setWarningSpeaker(Component warningSpeaker) {
        this.warningSpeaker = warningSpeaker;
    }
}
