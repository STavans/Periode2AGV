package avg1a2.project.modules.controller;

import TI.Servo;
import TI.Timer;
import avg1a2.project.logic.State;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;

/**
 * Controller to manage any and all actions related to motion and is called/used by the other controllers.
 */
public class MotionControl implements CollisionDetectionCallback {
    private CollisionDetection collisionDetection;
    private SignalControl signalControl;
    private Servo sLeft;
    private Servo sRight;
    private Timer timer;
    private Timer speakerTime;
    private State state;
    private int currentSpeed;
    private int targetSpeed;

    /**
     * Constructor sets it's Servo's and it's currentSpeed at creation.
     */
    public MotionControl(Servo sLeft, Servo sRight, SignalControl signalControl){
        this.signalControl = signalControl;
        this.sLeft = sLeft;
        this.sRight = sRight;
        this.currentSpeed = 0;
    }

    /**
     * Updates the controller to check if any actions are still in progress and if so, to continue them.
     */
    public void update() {
        collisionDetection.update();
        turn();
        accelerateToSpeed();
    }

    public void setCollisionDetection(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection;
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
    void setTargetSpeed(int targetSpeed) {
        if (state.ifState("Idle") || targetSpeed == 0) {
            this.targetSpeed = targetSpeed;
            this.state.setState("Accelerating");
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
     * Function to allow the BoeBot to accelerate to the current target speed.
     */
    private void accelerateToSpeed() {
        if (this.state.ifState("Accelerating") || (state.ifState("Collision") && targetSpeed == 0)) {
            if (targetSpeed != currentSpeed) {
                if (targetSpeed < currentSpeed) {
                    setSpeedForward(currentSpeed - 5);
                } else {
                    setSpeedForward(currentSpeed + 5);
                }
            } else if (sLeft.getPulseWidth() != sRight.getPulseWidth()) {
                setSpeedForward(currentSpeed);
                state.setState("Idle");
            } else {
                state.setState("Idle");
            }
        }
    }

    /**
     * Sets a new speed for the BoeBot.
     * @param speed The new speed of the BoeBot.
     */
    private void setSpeedForward(int speed){
        if (state.ifState("Idle") || state.ifState("Accelerating") || (state.ifState("Collision") && targetSpeed == 0)) {
            this.sLeft.update(1500 + speed);
            this.sRight.update(1500 - speed);
            this.currentSpeed = speed;
        }
    }

    /**
     * Emergency brake for the BoeBot to come to a total standstill in case of emergency.
     */
    void emergencyBrake(){
        this.sLeft.update(1500);
        this.sRight.update(1500);
        this.currentSpeed = 0;
    }

    /**
     * Function to check if the turnDegrees function needs to be ended.
     */
    private void turn() {
        if (state.ifState("Turning") && timer != null && timer.timeout()) {
            updateWheels(0,0);
            setTurnDegrees(0,0);
            setState("Idle");
        }
    }

    /**
     * Initiates the BoeBot to start turning.
     * @param degrees The amount of degrees to turn.
     * @param turnSpeed The speed at which to turn.
     */
    void setTurnDegrees(int degrees, int turnSpeed) {
        if (state.ifState("Idle")) {
            boolean reverse = false;
            int pulse;
            int turnDegrees = Math.abs(degrees);
            turnSpeed = Math.abs(turnSpeed);
            int turnTime = (int)(turnDegrees / (double)turnSpeed * 427); //multiplying by 427, after experimentation seemed to give an accurate time in milliseconds to turn 90 degrees.
            if (degrees < 0) {
                reverse = true;
            }
            if (reverse) {
                pulse = -turnSpeed;
            } else {
                pulse = turnSpeed;
            }
            updateWheels(pulse,-pulse);
            timer = new Timer(turnTime);
            setState("Turning");
        }
    }

    /**
     * BoeBot turns left smoothly using a certain offset.
     */
    void smoothTurnLeft(){
        if (state.ifState("Idle")) {
            sLeft.update(1575);
            sRight.update(1350);
        }
    }

    /**
     * BoeBot turns right smoothly using a certain offset.
     */
    void smoothTurnRight(){
        if (state.ifState("Idle")) {
            sLeft.update(1750);
            sRight.update(1425);
        }
    }

    /**
     * Makes the BoeBot turn right infinitely.
     */
    void infRight(){
        if (state.ifState("Idle")) {
            sLeft.update(1500 + currentSpeed);
            sRight.update(1500 + currentSpeed);
        }
    }

    /**
     * Makes the BoeBot turn left infinitely.
     */
    void infLeft(){
        if (state.ifState("Idle")) {
            sLeft.update(1500 - currentSpeed);
            sRight.update(1500 - currentSpeed);
        }
    }

    /**
     * Makes the BoeBot speed up with an increase of 5.
     */
    void speedUp() {
        if (state.ifState("Idle")) {
            setSpeedForward(currentSpeed + 5);
        }
    }

    /**
     * Makes the BoeBot slow down with a decrease of 5.
     */
    void slowDown() {
        if (state.ifState("Idle")) {
            setSpeedForward(currentSpeed - 5);
        }
    }

    void updateWheels(int speedLeft, int speedRight) {
        if (state.ifState("Idle") || state.ifState("Turning")) {
            this.sLeft.update(1500 + speedLeft);
            this.sRight.update(1500 - speedRight);
        }
    }

    @Override
    public void onFrontCollision() {
        state.setState("Collision");
        signalControl.boeBotCollision();
        this.targetSpeed = 0;
    }

    @Override
    public void emergencyCollision() {
        state.setState("Collision");
        signalControl.boeBotCollision();
        signalControl.setWarningSpeakerOn();
        emergencyBrake();
    }

    @Override
    public void collisionDone() {
        if (state.ifState("Collision")) {
            state.setState("Idle");
            signalControl.boeBotOn();
        }
    }
}
