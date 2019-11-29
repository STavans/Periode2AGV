package avg1a2.project.modules.irconversion;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.modules.data.CommandLayout;

public class IRConversion implements IRCallback {
    //hier komen de individuele knopcodes van de afstandsbediening
    private IRConversionCallback callback;
    private Component irSensor;

    public IRConversion(IRConversionCallback callback){
        this.callback = callback;
    }

    public void setIrSensor(Component irSensor) {
        this.irSensor = irSensor;
    }

    public void update() {
        if (irSensor == null) {
            throw new RuntimeException("IR sensor has not been set");
        } else {
            irSensor.update();
        }
    }


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
                callback.stop();
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
            case 0b10010100 :
                callback.mute();
                break;
            case 0b10010101 :
                callback.switchOn();
                break;
            case 0b10011010 :
                callback.infiniteRightTurn();
                break;
            case 0b10011011 :
                callback.infiniteLeftTurn();
                break;
            case 0b10010000 :
                callback.square();
                break;
            case 0b10010001 :
                callback.triangle();
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
        }

    }
}

