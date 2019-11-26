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


        }

    }
}

