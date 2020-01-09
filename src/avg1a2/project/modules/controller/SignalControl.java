package avg1a2.project.modules.controller;


import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.logic.State;

//TO DO
//
public class SignalControl {
    private LedGroup turnLeftLEDs;
    private LedGroup turnRightLEDs;
    private LedGroup followRoute;
    private LedGroup idle;
    private LedGroup collision;
    private LedGroup forward;
    private LedGroup reverse;
    private State state;
    private Component warningSpeaker;

    public SignalControl() {

    }

    public void update(){
        switch(state.getState()){
//           TODO
//            case "Collision":
//                boeBotCollision();
//                break;
//            kijken of het knipperen van de IDLE leds tijdens een collision detection met een state switch opgelost kan worden?
            case "Idle":
                //System.out.println("HALOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                boeBotOn();
                break;
            case "driveFW":
                forward();
                break;
            case "driveBW":
                backward();
                break;
            case "turnL":
                turnLeftLED();
                break;
            case "turnR":
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


    public void followRoute(){
        this.followRoute.on();

    }

    public void boeBotOn() {
        this.idle.on();
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

    public void forward(){
        this.forward.on();
    }

    public void backward(){
        this.reverse.on();
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

    public void setFollowRoute(LedGroup followRoute){
        this.followRoute = followRoute;
    }

    public void setWarningSpeaker(Component warningSpeaker) {
        this.warningSpeaker = warningSpeaker;
    }

    public void setForward(LedGroup forward) {
        this.forward = forward;
    }

    public void setReverse(LedGroup reverse) {
        this.reverse = reverse;
    }
}
