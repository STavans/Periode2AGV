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
        switch (Integer.toBinaryString(signal)){
            case "10000000" :
                callback.leftDiagonal();
                break;
            case "10000001" :
                callback.forward();
                break;
            case "10000010" :
                callback.rightDiagonal();
                break;
            case "10000011" :
                callback.leftTurn();
                break;
            case "10000100" :
                callback.stop();
                break;
            case "10000101" :
                callback.rightTurn();
                break;
            case "10000110" :
                callback.leftBackDiagonal();
                break;
            case "10000111" :
                callback.reverse();
                break;
            case "10001000" :
                callback.rightBackDiagonal();
                break;
            case "10010100" :
                callback.mute();
                break;
            case "10010101" :
                callback.switchOn();
                break;
            case "10011010" :
                callback.infiniteRightTurn();
                break;
            case "10011011" :
                callback.infiniteLeftTurn();
                break;
            case "10010000" :
                callback.square();
                break;
            case "10010001" :
                callback.triangle();
                break;
        }

    }
}

