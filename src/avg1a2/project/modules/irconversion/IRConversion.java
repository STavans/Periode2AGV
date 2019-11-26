package avg1a2.project.modules.irconversion;

import avg1a2.project.hardware.sensor.ir.IRCallback;
import avg1a2.project.hardware.sensor.ir.IRSensor;

public class IRConversion implements IRCallback {
    //hier komen de individuele knopcodes van de afstandsbediening
    private IRConversionCallback callback;

    public IRConversion(IRConversionCallback callback){
        this.callback = callback;
    }

    @Override
    public void onSignal(int signal) {
        switch (signal){
            case 0b0000000 :
                callback.leftDiagonal();
                break;
            case 0b0000001 :
                callback.forward();
                break;
            case 0b0000010 :
                callback.rightDiagonal();
                break;
            case 0b0000011 :
                callback.leftTurn();
                break;
            case 0b0000100 :
                callback.stop();
                break;
            case 0b0000101 :
                callback.rightTurn();
                break;
            case 0b0000110 :
                callback.leftBackDiagonal();
                break;
            case 0b0000111 :
                callback.reverse();
                break;
            case 0b0001000 :
                callback.rightBackDiagonal();
                break;
            case 0b0010100 :
                callback.mute();
                break;
            case 0b0010101 :
                callback.switchOn();
                break;
            case 0b0011010 :
                callback.infiniteRightTurn();
                break;
            case 0b0011011 :
                callback.infiniteLeftTurn();
                break;
            case 0b0010000 :
                callback.square();
                break;
            case 0b0010001 :
                callback.triangle();
                break;
        }

    }
}

