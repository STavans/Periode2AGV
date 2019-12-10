package avg1a2.project.modules.irconversion;

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
        if (irSensor == null) {
            throw new RuntimeException("IR sensor has not been set");
        } else {
            irSensor.update();
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
                callback.leftDiagonal();
                break;
            case 0b10000001 :
                callback.forward();
                break;
            case 0b10000010 :
                callback.rightDiagonal();
                break;
            case 0b10000011 :
                callback.leftTurn();
                break;
            case 0b10000100 :
                callback.brake();
                break;
            case 0b10000101 :
                callback.rightTurn();
                break;
            case 0b10000110 :
                callback.leftBackDiagonal();
                break;
            case 0b10000111 :
                callback.reverse();
                break;
            case 0b10001000 :
                callback.rightBackDiagonal();
                break;
            case 0b10010101 :
                if (programState.ifState("Override")) {
                    callback.changeState();
                } else {
                    override.override();
                }
                break;
            case 0b10011010 :
                callback.infiniteRightTurn();
                break;
            case 0b10011011 :
                callback.infiniteLeftTurn();
                break;
            case 0b10010011 :
                callback.smoothTurnLeft();
                break;
            case 0b10010010 :
                callback.smoothTurnRight();
                break;
            case 0b10011110 :
                callback.speedUp();
                break;
            case 0b10011111 :
                callback.slowDown();
                break;
            case 0b10010001 :
                callback.emergencyBrake();
                break;

        }

    }
}

