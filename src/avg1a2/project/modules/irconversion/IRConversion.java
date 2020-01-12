package avg1a2.project.modules.irconversion;

import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.logic.State;


/**
 * Converts the IR signal to match it with one of it's callback functions based on the code received.
 */
public class IRConversion implements IRCallback {
    private IRConversionCallback callback;
    private IROverridable override;
    private Component irSensor;
    private State programState;
    private Timer timer;

    /**
     * Constructor sets the callback to signal on detection.
     * @param callback Callback to signal on detection of a IR signal.
     */
    public IRConversion(IRConversionCallback callback, IROverridable override, State programState){
        this.callback = callback;
        this.override = override;
        this.programState = programState;
    }

    /**
     * Sets the sensor to use to scan for IR signals.
     * @param irSensor the sensor to use.
     */
    public void setIrSensor(Component irSensor) {
        this.irSensor = irSensor;
    }

    /**
     * Updates the converted, allowing it to continuously scan for IR signals.
     */
    public void update() {
        if (this.irSensor == null) {
            throw new RuntimeException("IR sensor has not been set");
        } else {
            this.irSensor.update();
        }
    }

    /**
     * Callback function called from the sensor on IR detection.
     * Compares the code received to a
     * @param signal Signal received by the sensor.
     */
    @Override
    public void onSignal(int signal) {
        switch (signal) {
            case 0b10000000 :
                if (this.programState.ifState("Override")) {
                    this.callback.leftDiagonal();
                }
                break;
            case 0b10000001 :
                if (this.programState.ifState("Override")) {
                    this.callback.forward();
                }
                break;
            case 0b10000010 :
                if (this.programState.ifState("Override")) {
                    this.callback.rightDiagonal();
                }
                break;
            case 0b10000011 :
                if (this.programState.ifState("Override")) {
                    this.callback.leftTurn();
                }
                break;
            case 0b10000100 :
                if (this.programState.ifState("Override")) {
                    this.callback.brake();
                }
                break;
            case 0b10000101 :
                if (this.programState.ifState("Override")) {
                    this.callback.rightTurn();
                }
                break;
            case 0b10000110 :
                if (this.programState.ifState("Override")) {
                    this.callback.leftBackDiagonal();
                }
                break;
            case 0b10000111 :
                if (this.programState.ifState("Override")) {
                    this.callback.reverse();
                }
                break;
            case 0b10001000 :
                if (this.programState.ifState("Override")) {
                    this.callback.rightBackDiagonal();
                }
                break;
            case 0b10010101 :
                if (this.timer == null || this.timer.timeout()) {
                    if (this.programState.ifState("Override")) {
                        this.callback.changeState();
                        this.timer = new Timer(1000);
                    } else {
                        this.override.override();
                        this.timer = new Timer(1000);
                    }
                }
                break;
            case 0b10011010 :
                if (this.programState.ifState("Override")) {
                    this.callback.infiniteRightTurn();
                }
                break;
            case 0b10011011 :
                if (this.programState.ifState("Override")) {
                    this.callback.infiniteLeftTurn();
                }
                break;
            case 0b10010011 :
                if (this.programState.ifState("Override")) {
                    this.callback.smoothTurnLeft();
                }
                break;
            case 0b10010010 :
                if (this.programState.ifState("Override")) {
                    this.callback.smoothTurnRight();
                }
                break;
            case 0b10011110 :
                if (this.programState.ifState("Override")) {
                    this.callback.speedUp();
                }
                break;
            case 0b10011111 :
                if (this.programState.ifState("Override")) {
                    this.callback.slowDown();
                }
                break;
            case 0b10010001 :
                if (this.programState.ifState("Override")) {
                    this.callback.emergencyBrake();
                }
                break;

        }

    }
}

