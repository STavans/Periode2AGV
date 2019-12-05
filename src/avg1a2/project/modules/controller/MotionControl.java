package avg1a2.project.modules.controller;

import TI.Servo;
import TI.Timer;
import avg1a2.project.logic.State;

/**
 * Controller to manage any and all actions related to motion and is called/used by the other controllers.
 */
public class MotionControl  {
    private Servo sLeft;
    private Servo sRight;
    private Timer timer;
    private State state;
    private State action;
    private int currentSpeed;
    private int targetSpeed;

    /**
     * Constructor sets it's Servo's and it's currentSpeed at creation.
     */
    public MotionControl(Servo sLeft, Servo sRight){
        this.sLeft = sLeft;
        this.sRight = sRight;
        this.currentSpeed = 0;
    }

    /**
     * Updates the controller to check if any actions are still in progress and if so, to continue them.
     */
    public void update() {
        if (state.ifState("Executing")) {
            turnDegrees();
            accelerateToSpeed();
        }
    }

    /**
     * Insert a new State object for the controller to use.
     * @param state The State for the controller to use.
     */
    public void newState(State state) {
        this.state = state;
        this.state.setState("Idle");
    }

    /**
     * Set the current active state of the controller.
     * @param state The new state the controller needs to be in.
     */
    public void setState(String state) {
        this.state.setState(state);
    }

    /**
     * Sets the targetSpeed for the BoeBot.
     * @param targetSpeed Target Speed for the BoeBot to reach.
     */
    public void setTargetSpeed(int targetSpeed) {
        this.targetSpeed = targetSpeed;
        if (this.targetSpeed != currentSpeed) {
            this.action.setState("Accelerating");
            this.state.setState("Executing");
        }
    }

    /**
     * Checks if the controller is idle using it's state.
     * @return True if the controller is idle, false if it's not.
     */
    boolean isIdle() {
        return state.ifState("Idle");
    }

    /**
     * Insert a new State object, defining all non single run actions for the controller to use.
     * @param action State containing all actions.
     */
    public void newAction(State action) {
        this.action = action;
        //TEMP
        this.action.addState("Accelerating");
    }

    /**
     * Sets the current action for the controller to keep checking to execute.
     * @param action New active action.
     */
    void setAction(String action) {
        this.action.setState(action);
    }

    /**
     * Function to allow the BoeBot to accelerate to the current target speed.
     */
    public void accelerateToSpeed(){
        if (this.action.ifState("Accelerating")) {
            if (targetSpeed != currentSpeed) {
                if (targetSpeed < currentSpeed) {
                    setSpeedForward(currentSpeed - 1);
                } else {
                    setSpeedForward(currentSpeed + 1);
                }
            } else {
                this.action.setState("None");
                this.state.setState("Idle");
            }
        }
    }

    /**
     * Sets a new speed for the BoeBot.
     * @param speed The new speed of the BoeBot.
     */
    void setSpeedForward(int speed){
        this.sLeft.update(1500 + speed);
        this.sRight.update(1500 - speed);
        this.currentSpeed = speed;
    }

    /**
     * Emergency brake for the BoeBot to come to a total standstill in case of emergency.
     */
    void emergencyBrake(){
        this.sLeft.update(1500); // This code is repeated a lot, maybe a private function to set the wheels? Doesn't help much, but it is a bit shorter.
        this.sRight.update(1500);
        this.currentSpeed = 0;
        //maybe we need to let it increase back to the currentSpeed afterwards instead.
        setState("Idle");
    }

    /**
     * Function to check if the turnDegrees function needs to be ended.
     */
    private void turnDegrees(){ //Can we combine this function back into the other turn function? Maybe one function that manages these both?
        if (action.ifState("TurnDegrees") && timer != null && timer.timeout()) {
            sLeft.update(1500);
            sRight.update(1500);
            setTurnDegrees(0,0);
            setAction("None");
            setState("Idle");
        }
    }

    /**
     * Initiates the BoeBot to start turning.
     * @param degrees The amount of degrees to turn.
     * @param turnSpeed The speed at which to turn.
     */
    void setTurnDegrees(int degrees, int turnSpeed) { //Can we just hardcode the speed and time based on degrees?
        boolean reverse = false;
        int pulse;
        int turnDegrees = Math.abs(degrees);
        turnSpeed = Math.abs(turnSpeed);
        int turnTime = (int)(turnDegrees / (double)turnSpeed * 427); //multiplying by 427, after experimentation seemed to give an accurate time in milliseconds to turn 90 degrees.
        if (degrees < 0) {
            reverse = true;
        }
        if (reverse) {
            pulse = 1500 - turnSpeed;
        } else {
            pulse = 1500 + turnSpeed;
        }
        sLeft.update(pulse); //REPEATED CODE AGAIN!!! ;)
        sRight.update(pulse);
        setAction("TurnDegrees");
        timer = new Timer(turnTime);
    }

    /**
     * BoeBot turns left smoothly using a certain offset.
     */
    void smoothTurnLeft(){ // Maybe we want to give the offset as a parameter?
        sLeft.update(1575); //DUPLICATE IGNORED!
        sRight.update(1350);
        setState("Idle");
    }

    /**
     * BoeBot turns right smoothly using a certain offset.
     */
    void smoothTurnRight(){
        sLeft.update(1750); //do I have to repeat myself?
        sRight.update(1425);
        setState("Idle");
    }

    /**
     * Makes the BoeBot turn right infinitely.
     */
    void infRight(){
        sLeft.update(1600); //you know what I'd say.
        sRight.update(1600); // Maybe not use set speeds, but the currentSpeed instead?, or just slow in general
        setState("Idle");
    }

    /**
     * Makes the BoeBot turn left infinitely.
     */
    void infLeft(){
        sLeft.update(1400); //last time I swear.
        sRight.update(1400); // Maybe not use set speeds, but the currentSpeed instead?, or just slow in general
        setState("Idle");
    }

    /**
     * Makes the BoeBot speed up with an increase of 5.
     */
    void speedUp() {
        this.currentSpeed += 5;
        setSpeedForward(currentSpeed); //Maybe change to accelerate?
        setState("Idle");
    }

    /**
     * Makes the BoeBot slow down with a decrease of 5.
     */
    void slowDown() {
        this.currentSpeed -= 5;
        setSpeedForward(currentSpeed); //Maybe change to accelerate?
        setState("Idle");
    }

    /**void brake() {
        boolean brake = true;
        while (brake) {
            this.currentSpeed -= 20;
            if (currentSpeed == 0) {
                brake = false;
            }
        }
    }*/

}
