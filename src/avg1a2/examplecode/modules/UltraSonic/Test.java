package avg1a2.examplecode.modules.ultrasonic;

import TI.BoeBot;
import TI.Timer;

public class Test {

    int pin = 0;
    int pin2 = 1;
    private Timer timer = new Timer(50);
    private Timer timer2 = new Timer(1);
    boolean state = true;
    boolean listening = false;

    UltraSonicCallback ultraSonicCallback;

    public Test(UltraSonicCallback ultraSonicCallback) {
        this.ultraSonicCallback = ultraSonicCallback;
    }

    public void update() {
        if (timer == null || timer.timeout()) {
            int scan = ultraScan();
            if (scan > 100) {
                int distance = calculateDistance(scan);
                if (distance < 50) {
                    ultraSonicCallback.onSignal();
                }
            }
            timer = new Timer(50);
        }
    }

    public int ultraScan(){
        toggle();
        if (!listening) {
            int pulse = BoeBot.pulseIn(pin2,true,10000);
            if (pulse > 100) {
                return pulse;
            }
            else {
                return 0;
            }
        }
        return 0;
    }

    public int calculateDistance(int pulse){
        int distance = pulse / 58;
        return distance;
    }

    public void toggle() {
        if (timer2 == null || timer2.timeout() )
            if (listening) {
                BoeBot.digitalWrite(pin, state);
            } else {
                BoeBot.digitalWrite(pin,state);
            }
            state = !state;
            listening = !listening;
            timer2 = new Timer(1);
    }
}
