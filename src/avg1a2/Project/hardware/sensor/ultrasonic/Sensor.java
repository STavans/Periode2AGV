package avg1a2.project.hardware.sensor.ultrasonic;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.modules.UltraSonicCallback;

public class Sensor {
    private int pin1;
    private int pin2;
    private int timeOut;
    private int timer;
    private UltraSonicCallback ultraSonicCallback;

    public Sensor(int pin1, int pin2, int timeOut, int timer, UltraSonicCallback ultraSonicCallBack) {
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.timer = timer;
        this.timeOut = timeOut;
        this.ultraSonicCallback = ultraSonicCallBack;
    }

    public void objectDetection(){
        Timer timer = new Timer(this.timeOut);
        timer.mark();

        if(timer.timeout()){
            BoeBot.digitalWrite(pin1,false);
            int distance = (this.timeOut * 343) /2;
            this.ultraSonicCallback.onUltraSonic(distance);

        } else {
            BoeBot.digitalWrite(pin1,true);
        }
    }
//
//    public void detectObject() {
//        while (true) {
//            BoeBot.digitalWrite(0, true);
//            BoeBot.uwait(300);
//            BoeBot.digitalWrite(0, false);
//
//            int pulse = BoeBot.pulseIn(1, true, 10000);
//
//            System.out.prinotln("Pulse: " + pulse);
//            BoeBot.uwait(500);
//            Hij geeft een -2 als de sensor een object detecteerd
//        }
//    }

}
