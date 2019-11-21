package avg1a2.examplecode.modules;

import TI.BoeBot;

import java.util.HashMap;

public class IR {
    private int pin;

    public IR(int pin) {
        this.pin = pin;
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
            return SignalConverter(pulse);
        }
        error.put("command",-1);
        error.put("id",-1);
        return error;
    }

    private HashMap<String, Integer> SignalConverter(int signal[]) {
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
}
