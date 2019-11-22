package avg1a2.examplecode.modules.UltraSonic;

import TI.BoeBot;
import TI.Timer;

public class Test {

    int pin = 0;
    int pin2 = 1;
    private Timer timer = new Timer(50);
    boolean idle = false;

    UltraSonicCallback ultraSonicCallback;

    public Test(UltraSonicCallback ultraSonicCallback) {
        this.ultraSonicCallback = ultraSonicCallback;
    }

    public void update() {
        if (timer.timeout() || timer == null) {
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
        BoeBot.digitalWrite(pin, true);
        BoeBot.wait(1);
        BoeBot.digitalWrite(pin, false);
        int pulse = BoeBot.pulseIn(pin2,true,10000);
        if (pulse > 100) {
            return pulse;
        }
        else {
            return 0;
        }
    }

    public int calculateDistance(int pulse){
        int distance = pulse / 58;
        return distance;
    }
}
