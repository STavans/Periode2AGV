package avg1a2.project.modules.controller;

import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.logic.State;


public class SignalControl {
    private LedGroup turnLeftLEDs;
    private LedGroup turnRightLEDs;
    private LedGroup blueBot;
    private LedGroup remoteControl;
    private LedGroup idle;
    private LedGroup collision;
    private LedGroup forward;
    private LedGroup reverse;
    private State state;

    public SignalControl() {
    }

    public void update(){
        switch(state.getState()){
            case "Idle":
                boeBotOn();
                break;
            case "Collision" :
                boeBotCollision();
                break;
            case "DriveFW":
                forward();
                break;
            case "DriveBW":
                backward();
                break;
            case "TurnL":
                turnLeftLED();
                break;
            case "TurnR":
                turnRightLED();
                break;
        }
    }

    public void newState(State state){
        this.state = state;
    }

    public void setState(String state){
        this.state.setState(state);
    }

    public void blueBot(){
        this.idle = this.blueBot;
    }

    void remoteControl() {
        this.idle = this.remoteControl;
    }

    public void boeBotOn() {
        this.idle.on();
    }

    private void boeBotCollision() {
        this.collision.on();
    }

    private void turnLeftLED() {
        this.turnLeftLEDs.on();
    }

    private void turnRightLED() {
        this.turnRightLEDs.on();
    }

    private void forward(){
        this.forward.on();
    }

    private void backward(){
        this.reverse.on();
    }

    public void setTurnLeftLEDs(LedGroup turnLeftLEDs) {
        this.turnLeftLEDs = turnLeftLEDs;
    }

    public void setTurnRightLEDs(LedGroup turnRightLEDs) {
        this.turnRightLEDs = turnRightLEDs;
    }

    public void setRemoteControl(LedGroup remoteControl) {
        this.remoteControl = remoteControl;
    }

    public void setCollision(LedGroup collision) {
        this.collision = collision;
    }

    public void setBlueBot(LedGroup blueBot){
        this.blueBot = blueBot;
    }

    public void setForward(LedGroup forward) {
        this.forward = forward;
    }

    public void setReverse(LedGroup reverse) {
        this.reverse = reverse;
    }
}
