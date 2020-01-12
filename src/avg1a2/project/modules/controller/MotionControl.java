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
    private State state;
    private int turningCount;
    private int currentSpeed;
    private int targetSpeed;

    /**
     * Constructor sets it's Servo's and it's currentSpeed at creation.
     */
    public MotionControl(Servo sLeft, Servo sRight, SignalControl signalControl) {
        this.signalControl = signalControl;
        this.sLeft = sLeft;
        this.sRight = sRight;
        this.currentSpeed = 0;
        this.turningCount = 0;
    }

    /**
     * Updates the controller to check if any actions are still in progress and if so, to continue them.
     */
    public void update() {
        this.collisionDetection.update();
        updateUpdatable();
        updateSignals();
        this.signalControl.update();
    }

    /**
     * @params state  gets checked and depending on the state sends a signal to the signalControl,
     * it checks this by looking at the currentSpeed and it checks if there has been a collision
     */
    private void updateSignals() {
        switch (this.state.getState()) {
            case "Idle":
                if (this.currentSpeed > 0) {
                    this.signalControl.setState("DriveFW");
                } else if (this.currentSpeed < 0) {
                    this.signalControl.setState("DriveBW");
                } else {
                    this.signalControl.setState("Idle");
                }
                break;
            case "Accelerating":
                if (this.currentSpeed > 0) {
                    this.signalControl.setState("DriveFW");
                } else if (currentSpeed < 0) {
                    this.signalControl.setState("DriveBW");
                } else {
                    this.signalControl.setState("Idle");
                }
                break;
            case "FrontCollision":
                this.signalControl.setState("Collision");
                break;
            case "BackCollision":
                this.signalControl.setState("Collision");
                break;
            case "FullCollision":
                this.signalControl.setState("Collision");
                break;
            case "Turning":
                if (this.turningCount == 1) {
                    this.signalControl.setState("TurnR");
                } else if (this.turningCount == -1) {
                    this.signalControl.setState("TurnL");
                }
                break;
        }
    }

    /**
     * This method is to update the following methods:
     *
     * @method turn, accelerateToSpeed and brake.
     */
    private void updateUpdatable() {
        turn();
        accelerateToSpeed();
        brake();
    }

    /**
     * This method is to set the collision detection
     */
    public void setCollisionDetection(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection;
    }

    /**
     * Insert a new State object for the controller to use.
     *
     * @param state The State for the controller to use.
     */
    public void newState(State state) {
        this.state = state;
        this.state.setState("Idle");
    }

    /**
     * Set the current active state of the controller.
     *
     * @param state The new state the controller needs to be in.
     */
    public void setState(String state) {
        this.state.setState(state);
    }

    /**
     * Sets the targetSpeed for the BoeBot.
     *
     * @param targetSpeed Target Speed for the BoeBot to reach.
     */
    void setTargetSpeed(int targetSpeed) {
        if (this.state.ifState("Idle") || this.state.ifState("Accelerating")) {
            this.targetSpeed = targetSpeed;
            this.state.setState("Accelerating");
        }
    }

    /**
     * Checks if the controller is idle using it's state.
     *
     * @return True if the controller is idle, false if it's not.
     */
    boolean isIdle() {
        return this.state.ifState("Idle");
    }

    /**
     * Function to allow the BoeBot to accelerate to the current target speed.
     */
    private void accelerateToSpeed() {
        if (this.state.ifState("Accelerating")) {
            if (this.targetSpeed != this.currentSpeed) {
                if (this.targetSpeed < this.currentSpeed) {
                    setSpeedForward(this.currentSpeed - 10);
                } else {
                    setSpeedForward(this.currentSpeed + 10);
                }
            } else if (((this.sLeft.getPulseWidth() - this.currentSpeed) != 1500) && ((this.sRight.getPulseWidth() + this.currentSpeed) != 1500)) {
                setSpeedForward(this.currentSpeed);
            } else {
                this.state.setState("Idle");
            }
        }
    }

    /**
     * Sets a new speed for the BoeBot.
     * It also checks if the Boebot is already accelerating or idle, this is to avoid any malfunctions in the route/remote control
     *
     * @param speed The new speed of the BoeBot.
     */
    private void setSpeedForward(int speed) {
        if (this.state.ifState("Idle") || this.state.ifState("Accelerating")) {
            this.sLeft.update(1500 + speed);
            this.sRight.update(1500 - speed);
            this.currentSpeed = speed;
        }
    }

    /**
     * Emergency brake for the BoeBot to come to a total standstill in case of emergency.
     */
    void emergencyBrake() {
        this.sLeft.update(1500);
        this.sRight.update(1500);
        this.currentSpeed = 0;
    }

    /**
     * Function to check if the turnDegrees function needs to be ended.
     */
    private void turn() {
        if (this.state.ifState("Turning") && this.timer != null && this.timer.timeout()) {
            updateWheels(0, 0);
            setTurnDegrees(0, 0);
            setState("Idle");
        }
    }

    /**
     * Initiates the BoeBot to start turning.
     *
     * @param degrees   The amount of degrees to turn.
     * @param turnSpeed The speed at which to turn.
     */
    void setTurnDegrees(int degrees, int turnSpeed) {
        if (this.state.ifState("Idle")) {
            boolean reverse = false;
            int pulse;
            int turnDegrees = Math.abs(degrees);
            turnSpeed = Math.abs(turnSpeed);
            int turnTime = (int) (turnDegrees / (double) turnSpeed * 427); //multiplying by 427, after experimentation seemed to give an accurate time in milliseconds to turn 90 degrees.
            if (degrees < 0) {
                reverse = true;
            }
            if (reverse) {
                pulse = -turnSpeed;
                this.turningCount = -1;
            } else {
                pulse = turnSpeed;
                this.turningCount = 1;
            }
            updateWheels(pulse, -pulse);
            this.timer = new Timer(turnTime);
            setState("Turning");
        }
    }

    /**
     * BoeBot turns left smoothly using a certain offset.
     */
    void smoothTurnLeft() {
        if (this.state.ifState("Idle")) {
            this.sLeft.update(1575);
            this.sRight.update(1350);
        }
    }

    /**
     * BoeBot turns right smoothly using a certain offset.
     */
    void smoothTurnRight() {
        if (this.state.ifState("Idle")) {
            this.sLeft.update(1750);
            this.sRight.update(1425);

        }
    }

    /**
     * Makes the BoeBot turn right infinitely.
     */
    void infRight() {
        if (this.state.ifState("Idle")) {
            this.sLeft.update(1500 + this.currentSpeed);
            this.sRight.update(1500 + this.currentSpeed);
        }
    }

    /**
     * Makes the BoeBot turn left infinitely.
     */
    void infLeft() {
        if (this.state.ifState("Idle")) {
            this.sLeft.update(1500 - this.currentSpeed);
            this.sRight.update(1500 - this.currentSpeed);
        }
    }

    /**
     * Makes the BoeBot speed up with an increase of 5.
     */
    void speedUp() {
        if (this.state.ifState("Idle")) {
            setSpeedForward(this.currentSpeed + 5);
        }
    }

    /**
     * Makes the BoeBot slow down with a decrease of 5.
     */
    void slowDown() {
        if (this.state.ifState("Idle")) {
            setSpeedForward(this.currentSpeed - 5);
        }
    }

    /**
     * This method is used when a collision has been detected, and the function of this method is to slowly stop the Boebot.
     */
    private void brake() {
        if (this.state.ifState("FrontCollision") || this.state.ifState("BackCollision") || this.state.ifState("FullCollision")) {
            if (((this.sLeft.getPulseWidth() - this.currentSpeed) != 1500) && ((this.sRight.getPulseWidth() + this.currentSpeed) != 1500)) {
                updateWheels(this.currentSpeed, this.currentSpeed);
            } else if (this.currentSpeed != 0) {
                updateWheels(this.currentSpeed - 5, this.currentSpeed - 5);
                this.currentSpeed -= 5;
            }
        }
    }

    /**
     * This method keeps the servo's updated at all times
     * It also has state checks to decrease the amount of bugs in the code
     */
    void updateWheels(int speedLeft, int speedRight) {
        if (this.state.ifState("Idle") || state.ifState("Turning") || state.ifState("FrontCollision") || state.ifState("BackCollision")) {
            this.sLeft.update(1500 + speedLeft);
            this.sRight.update(1500 - speedRight);
        }
    }

    /**
     * This method only is used once the frontUltraSonic detects an object
     * The states are there to check if there is a full on collision or a front collision
     */
    @Override
    public void onFrontCollision() {
        if (!this.state.ifState("BackCollision") && !this.state.ifState("FullCollision")) {
            this.state.setState("FrontCollision");
        } else if (this.state.ifState("BackCollision")) {
            this.state.setState("FullCollision");
        }
    }

    /**
     * This method checks if an object is directly in front of it and acts accordingly by using the method emergency break
     * If there also is a backCollision then it sets it's state to fullCollision because it detects objects on either side
     */
    @Override
    public void onFrontEmergencyCollision() {
        if (!this.state.ifState("BackCollision") && !this.state.ifState("FullCollision")) {
            this.state.setState("FrontCollision");
        } else if (this.state.ifState("BackCollision")) {
            this.state.setState("FullCollision");
        }
        emergencyBrake();
    }

    /**
     * This method is called upon once the collisionDetection detects an object
     * The states are used to differentiate between a full on collision or a back collision
     */
    @Override
    public void onBackCollision() {
        if (!this.state.ifState("FrontCollision") && !this.state.ifState("FullCollision")) {
            this.state.setState("BackCollision");
        } else if (this.state.ifState("FrontCollision")) {
            this.state.setState("FullCollision");
        }
    }

    /**
     * This method is called upon once the collisionDetection detects an object right in front of the backUltraSonic
     * The states are there to differentiate between a full on collision and a backEmergencyCollision
     */
    @Override
    public void onBackEmergencyCollision() {
        if (!this.state.ifState("FrontCollision") && !this.state.ifState("FullCollision")) {
            this.state.setState("BackCollision");
        } else if (this.state.ifState("FrontCollision")) {
            this.state.setState("FullCollision");
        }
        emergencyBrake();
    }

    /**
     * This method is called upon once the collisionDetection doesn't detect an object in front of it anymore
     * The states are used to differentiate between a full on collision and a back collision
     */
    @Override
    public void frontCollisionDone() {
        switch (this.state.getState()) {
            case "FrontCollision":
                setState("Idle");
                break;
            case "FullCollision":
                setState("BackCollision");
                break;
        }
    }

    /**
     * This method is called upon once the collisionDetection doesn't detect an object in front of it anymore
     * The states are used to differentiate between a full on collision and a back collision
     */
    @Override
    public void backCollisionDone() {
        switch (this.state.getState()) {
            case "BackCollision":
                setState("Idle");
                break;
            case "FullCollision":
                setState("FrontCollision");
                break;
        }
    }
}
