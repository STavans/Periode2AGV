package avg1a2.project.modules.controller;

import TI.Timer;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.irconversion.IRConversion;
import avg1a2.project.modules.irconversion.IRConversionCallback;

/**
 * Controller to mange remote control operations.
 */
public class RemoteControl implements CollisionDetectionCallback, IRConversionCallback {
    private MotionControl motionControl;
    private CollisionDetection collisionDetection;
    private IRConversion irConversion;
    private Timer timer;

    /**
     * Constructor sets the motionController to use.
     * @param motionControl the MotionControl to use.
     */
    public RemoteControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    /**
     * Sets the Collision Detection to use.
     * @param collisionDetection Collision Detection to use.
     */
    public void setCollisionDetection(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection;
    }

    /**
     * Sets the IrConversion to use.
     * @param irConversion The IrConversion to use.
     */
    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    /**
     * Updates the controller, which also updates all of it's own updates.
     */
    public void run() {
        if (collisionDetection != null) {
            collisionDetection.update();
            irConversion.update();
            motionControl.update();
            //todo error catching.
        }
    }

    /**
     * Function the callback calls whenever there is a front collision detected.
     */
    public void onFrontCollision() {
        motionControl.setState("Idle");
        motionControl.setAction("None");
        brake(); //Maybe we need to let it brake instead?
    }

    /**
     * Makes the BoeBot go left diagonally,.
     */
    public void leftDiagonal() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            brake(); //stop or brake?
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-45,50);
        }
    }

    /**
     * Makes the BoeBot go forward.
     */
    public void forward() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.setSpeedForward(200); //maybe this function should instead if it is going backwards, now make it go forward at the same speed?
        }
    }

    /**
     * Makes to BoeBot go right diagonally.
     */
    public void rightDiagonal() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            brake(); //stop or brake?
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(45,50);
        }
    }

    /**
     * Makes the BoeBot turn left.
     */
    public void leftTurn() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()){
            brake(); //stop or brake?
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-90,50);
        }
    }

    /**
     * Makes the BoeBot stop.
     */
    public void emergencyBrake() { //Stop or brake?
        if (motionControl.isIdle()) {
            motionControl.setState("Executing");
            motionControl.emergencyBrake();
        }
    }

    /**
     * Makes the BoeBot turn right.
     */
    public void rightTurn() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            brake(); //stop or brake?
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(90,50);
        }
    }

    /**
     * Makes the BoeBot go left back diagonally.
     */
    public void leftBackDiagonal() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            brake(); //stop or brake?
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-135,50);
        }
    }

    /**
     * Makes the BoeBot go backwards.
     */
    public void reverse() {
        if (motionControl.isIdle()) {
            motionControl.setState("Executing");
            motionControl.setSpeedForward(-200); //maybe this function should instead if it is going forward, now make it go backward at the same speed?
        }
    }

    /**
     * Makes the BoeBot go back diagonally.
     */
    public void rightBackDiagonal() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            brake(); //stop or brake?
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(135,50);
        }
    }

    /**
     * Switches the BoeBot State.
     */
    public void switchOn() {
        //todo override for following route
    }

    /**
     * Makes the BoeBot turn right infinitely.
     */
    public void infiniteRightTurn() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.infRight();
        }
    }

    /**
     * Makes the BoeBot turn left infinitely.
     */
    public void infiniteLeftTurn() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.infLeft();
        }
    }

    /**
     * Makes the BoeBot turn left smoothly.
     */
    public void smoothTurnLeft() {

        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.smoothTurnLeft();
        }
    }

    /**
     * Makes the BoeBot turn right smoothly.
     */
    public void smoothTurnRight() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.smoothTurnRight();
        }
    }

    /**
     * Makes the BoeBot speed up slowly.
     */
    public void speedUp() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.speedUp();
        }
    }

    /**
     * Makes the BoeBot slow down slowly.
     */
    public void slowDown() {
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.slowDown();
        }
    }

    public void brake(){
        if (motionControl.isIdle() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.brake();
        }
    }
}