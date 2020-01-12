package avg1a2.project.modules.controller;

import avg1a2.project.logic.State;
import avg1a2.project.modules.irconversion.IRConversion;
import avg1a2.project.modules.irconversion.IRConversionCallback;

/**
 * Controller to mange remote control operations.
 */
public class RemoteControl implements IRConversionCallback {
    private MotionControl motionControl;
    private SignalControl signalControl;
    private IRConversion irConversion;
    private State programState;


    /**
     * Constructor sets the motionController to use.
     * @param motionControl the MotionControl to use.
     */
    public RemoteControl(MotionControl motionControl, SignalControl signalControl) {
        this.motionControl = motionControl;
        this.signalControl = signalControl;
    }

    /**
     * Sets the IrConversion to use.
     * @param irConversion The IrConversion to use.
     */
    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    public void setProgramState(State programState) {
        this.programState = programState;
    }

    /**
     * Updates the controller, which also updates all of it's own updates.
     */
    public void run() {
        this.irConversion.update();
        this.motionControl.update();
    }

    /**
     * Makes the BoeBot go left diagonally,.
     */
    public void leftDiagonal() {
        this.motionControl.setTurnDegrees(-45,50);
    }

    /**
     * Makes the BoeBot go forward.
     */
    public void forward() {
        this.motionControl.setTargetSpeed(200);
    }

    /**
     * Makes to BoeBot go right diagonally.
     */
    public void rightDiagonal() {
        this.motionControl.setTurnDegrees(45,50);
    }

    /**
     * Makes the BoeBot turn left.
     */
    public void leftTurn() {
        this.motionControl.setTurnDegrees(-90,50);
    }

    /**
     * Makes the BoeBot stop.
     */
    public void emergencyBrake() {
        this.motionControl.emergencyBrake();
    }

    /**
     * Makes the BoeBot turn right.
     */
    public void rightTurn() {
        this.motionControl.setTurnDegrees(90,50);
    }

    /**
     * Makes the BoeBot go left back diagonally.
     */
    public void leftBackDiagonal() {
        this.motionControl.setTurnDegrees(-135,50);
    }

    /**
     * Makes the BoeBot go backwards.
     */
    public void reverse() {
        this.motionControl.setTargetSpeed(-200); //maybe this function should instead if it is going forward, now make it go backward at the same speed?
    }

    /**
     * Makes the BoeBot go back diagonally.
     */
    public void rightBackDiagonal() {
        this.motionControl.setTurnDegrees(135,50);
    }

    /**
     * Switches the BoeBot State.
     */
    public void changeState() {
        this.motionControl.setState("Idle");
        this.programState.setState("BlueBot");
        this.signalControl.blueBot();
    }

    /**
     * Makes the BoeBot turn right infinitely.
     */
    public void infiniteRightTurn() {
        this.motionControl.infRight();
    }

    /**
     * Makes the BoeBot turn left infinitely.
     */
    public void infiniteLeftTurn() {
        this.motionControl.infLeft();
    }

    /**
     * Makes the BoeBot turn left smoothly.
     */
    public void smoothTurnLeft() {
        this.motionControl.smoothTurnLeft();
    }

    /**
     * Makes the BoeBot turn right smoothly.
     */
    public void smoothTurnRight() {
        this.motionControl.smoothTurnRight();
    }

    /**
     * Makes the BoeBot speed up slowly.
     */
    public void speedUp() {
        this.motionControl.speedUp();
    }

    /**
     * Makes the BoeBot slow down slowly.
     */
    public void slowDown() {
        this.motionControl.slowDown();
    }

    /**
     * Makes the Boebot stop immediately
     */
    public void brake() {
        this.motionControl.setTargetSpeed(0);
    }
}