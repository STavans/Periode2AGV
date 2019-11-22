package avg1a2.project.hardware.sensor.ultrasonic;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.sensor.Sensor;
import avg1a2.project.modules.UltraSonicCallback;

public class UltrasonicSensor{
    private int pin1;
    private int pin2;
    private int timeOut;
    private int timer;
    private UltraSonicCallback ultraSonicCallback;
    private boolean isOn;

    public UltrasonicSensor(int pin1, int pin2, int timeOut, int timer, UltraSonicCallback ultraSonicCallBack, boolean isOn) {
        this.pin1 = pin1;
        this.pin2 = pin2;
        this.timer = timer;
        this.timeOut = timeOut;
        this.ultraSonicCallback = ultraSonicCallBack;
        this.isOn = isOn;
    }

//    public int objectDetection(){
//        Timer timer = new Timer(this.timeOut);
//
//        if(timer.timeout()){
//            BoeBot.digitalWrite(pin1,false);
//            this.isOn = false;
//            BoeBot.pulseIn(this.pin1, true, this.timer);
//            int distance = (this.timer * 343) /2;
//            this.ultraSonicCallback.onUltraSonic();
//            return distance;
//
//        } else {
//            BoeBot.digitalWrite(pin1,true);
//            this.isOn = true;
//
//        }
//        return 0;
//    }
//
//    @Override
//    public boolean isActive() {
//        return false;
//    }
//
//    @Override
//    public void update() {
//        if(objectDetection() > 25){
//            this.ultraSonicCallback.onUltraSonic();
//            System.out.println("Hij rijdt");
//        } else if(objectDetection() < 25){
//            System.out.println("Hij moet stoppen");
//        }
//
//    }
//
    public void detectObject() {
        while (true) {
            BoeBot.digitalWrite(0, true);
            BoeBot.uwait(300);
            BoeBot.digitalWrite(0, false);

            int pulse = BoeBot.pulseIn(1, true, 10000);

            System.out.println("Pulse: " + pulse);
            BoeBot.uwait(500);
//            Hij geeft een -2 als de sensor een object detecteerd
        }
    }

}
