package avg1a2.project.modules.collisiondetection;

import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.controller.MotionControl;

public class CollisionDetection implements UltraSonicCallback {

    private MotionControl motionControl;
    private CollisionDetectionCallback collisionDetectionCallback;
    private Component ultrasonicSensor;
    private Timer timer;
    private boolean collision;
    private LedGroup group;
    private Speaker warningSpeaker;
    private LedGroup ledGroup;

    /**
     * @param collisionDetection gets initialised in the constructor
     **/
    public CollisionDetection(CollisionDetectionCallback collisionDetection,MotionControl motionControl , LedGroup group, Speaker warningSpeaker, LedGroup ledGroup){
        this.collisionDetectionCallback = collisionDetection;
        this.group = group;
        this.collision = false;
        this.warningSpeaker = warningSpeaker;
        this.ledGroup = ledGroup;
    }

    public void setUltrasonicSensor(Component ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
    }

    public boolean isCollision() {
        return collision;
    }

    /**
     *This updates the ultrasonic sensor
     **/
    public void update(){
        if (ultrasonicSensor == null) {
            throw new RuntimeException("Ultrasonic sensor has not been assigned");
        } else {
            ultrasonicSensor.update();
        }
        if (timer != null && timer.timeout() && collision) {
            collision = false;
            ledGroup.on();
        }
        if (motionControl.stateCheck()) {
            warningSpeaker.Beep();
        }
    }

    public void collisionWhisker(){

    }

    /**
     *Calls the collisionDetectionCallback
     **/
    @Override
    public void onUltraSonic() {
        group.on();
        collision = true;
        timer = new Timer(500);
        collisionDetectionCallback.onFrontCollision();
    }
}
