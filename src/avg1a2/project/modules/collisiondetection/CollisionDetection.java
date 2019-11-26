package avg1a2.project.modules.collisiondetection;

import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.controller.MotionControl;

public class CollisionDetection implements UltraSonicCallback {

    private CollisionDetectionCallback collisionDetectionCallback;

    /**
     * @param collisionDetection gets initialised in the constructor
     **/
    public CollisionDetection(CollisionDetectionCallback collisionDetection){
        this.collisionDetectionCallback = collisionDetection;
    }

    /**
     *This updates the ultrasonic sensor
     **/
    public void update(){
        UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(0, 1, this, true);
        while (true) {
            ultrasonicSensor.update();
        }
    }

    public void collisionWhisker(){

    }

    /**
     *Calls the collisionDetectionCallback
     **/
    @Override
    public void onUltraSonic() {

        collisionDetectionCallback.onFrontCollision();
    }
}
