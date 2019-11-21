package avg1a2.project.hardware.sensor.ir;

import TI.BoeBot;
import avg1a2.project.hardware.sensor.Sensor;

import java.util.HashMap;

public class IRSensor implements Sensor {

    private int pin;
    private IRCallback irCallback;

    public IRSensor(int pin, IRCallback irCallback) {
        this.pin = pin;
        this.irCallback = irCallback;
    }

    public HashMap<String, Integer> Scan(){
        HashMap<String, Integer> error = new HashMap<>();
        int pulseLen = BoeBot.pulseIn(8,false,30000);
        if (pulseLen > 2000) {
            int pulse[] = new int[12];
            for (int i = 0; i <12; i++) {
                pulse[i] = BoeBot.pulseIn(8,false,20000);
            }
            //System.out.println(Integer.toBinaryString(SignalConverter(pulse).get("command")));
            return signalIn(pulse);
        }
        error.put("command",-1);
        error.put("id",-1);
        return error;
    }

    private HashMap<String, Integer> signalIn (int signal[]) {
        HashMap<String, Integer> conversion = new HashMap<>();

        int command = 0;
        int id = 0;
        int bit;
        int counter = 0;

        for (int i = 0; i < signal.length; i++) {
            if (signal[i] > 1000) {
                bit = 1;
            } else if (signal[i] < 0){
                conversion.put("command",-1);
                conversion.put("id",-1);
                return conversion;
            } else {
                bit = 0;
            }

            if (counter < 7) {
                if (bit == 1) {
                    command = command | 1 << counter;
                }
            } else if (counter < 12) {
                if (bit == 1) {
                    id = id | 1 << counter;
                }
            }
            counter++;
        }
        conversion.put("command",command);
        conversion.put("id",id);
        return conversion;
    }

    public boolean getSignal(){
        int pulseLength = BoeBot.pulseIn(8, false, 30000);

        if (pulseLength > 2000){
            return true;
        }
        return false;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void update() {
        if (getSignal() == true){
            this.irCallback.getSignal(this);
        }
    }
}
