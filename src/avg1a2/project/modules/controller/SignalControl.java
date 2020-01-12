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

    /**
     * This method updates the state in which the Boebot is in
     * Depending on the state it acts accordingly by changing the LED groups
     */
    public void update(){
        switch(this.state.getState()){
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

    /**
     * This method is to initiate the state
     * @param state is used to check what state the Boebot is in.
     */
    public void newState(State state){
        this.state = state;
    }

    /**
     * This method is to set the state
     * @param state gets set through checking what state the Boebot is in
     */
    public void setState(String state){
        this.state.setState(state);
    }

    /**
     * This method is used to set the idle led-group to the blueBot led-group
     */
    public void blueBot(){
        this.idle = this.blueBot;
    }

    /**
     * This method is to set the idle led-group to the remoteControl led-Group
     */
    void remoteControl() {
        this.idle = this.remoteControl;
    }

    /**
     * This method is the led-group that is on by default
     */
    public void boeBotOn() {
        this.idle.on();
    }

    /**
     * Once there is a collision this method is called and set the led-group of idle(depending on if it's in blueBot or remoteControl mode)
     * to the led-group of collision (Red to show that there is a collision).
     */
    private void boeBotCollision() {
        this.collision.on();
    }

    /**
     * This method is used when the Boebot is turning to the left, it changes the left-side of neo pixels to yellow
     */
    private void turnLeftLED() {
        this.turnLeftLEDs.on();
    }

    /**
     * This method is used when the Boebot is turning to the right, it changes the right-side of neo pixels to yellow
     */
    private void turnRightLED() {
        this.turnRightLEDs.on();
    }

    /**
     * This method is used once the Boebot gets the command to go forward
     * The forward three neo pixels get changed to a green colour
     */
    private void forward(){
        this.forward.on();
    }

    /**
     * This method is used once the Boebot gets the command to reverse
     * The back three neo pixels get changed to a orange colour
     */
    private void backward(){
        this.reverse.on();
    }

    /**
     * This method sets the led-group turnLeftLEDs
     * @param turnLeftLEDs is the led-group that is used once it turns to the left
     */
    public void setTurnLeftLEDs(LedGroup turnLeftLEDs) {
        this.turnLeftLEDs = turnLeftLEDs;
    }

    /**
     * This method sets the led-group turnRightLEDs
     * @param turnRightLEDs is the led-group that is used once it turns to the right
     */
    public void setTurnRightLEDs(LedGroup turnRightLEDs) {
        this.turnRightLEDs = turnRightLEDs;
    }

    /**
     * This method sets the led-group remoteControl
     * @param remoteControl is used once the state of the Boebot is in remoteControl
     */
    public void setRemoteControl(LedGroup remoteControl) {
        this.remoteControl = remoteControl;
    }

    /**
     * This method sets the led-group collision
     * @param collision is the led-group that is used once it detects a collision
     */
    public void setCollision(LedGroup collision) {
        this.collision = collision;
    }

    /**
     * This method sets the led-group blueBot
     * @param blueBot is the led-group that is used once the state of the Boebot is in blueBot
     */
    public void setBlueBot(LedGroup blueBot){
        this.blueBot = blueBot;
    }

    /**
     * This method sets the led-group forward
     * @param forward is the led-group that is used once it gets the command to go forward
     */
    public void setForward(LedGroup forward) {
        this.forward = forward;
    }

    /**
     * This method sets the led-group reverse
     * @param reverse is the led-group that is used once it gets the command to reverse
     */
    public void setReverse(LedGroup reverse) {
        this.reverse = reverse;
    }
}
