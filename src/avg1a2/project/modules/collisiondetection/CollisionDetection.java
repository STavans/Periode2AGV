package avg1a2.project.modules.collisiondetection;

import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.controller.MotionControl;

public class CollisionDetection implements UltraSonicCallback {

    private CollisionDetectionCallback collisionDetectionCallback;

    public CollisionDetection(CollisionDetectionCallback collisionDetection){
        this.collisionDetectionCallback = collisionDetection;
    }

    /**
     *
     */
    public void update(){
        UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(0, 1, this);
        ultrasonicSensor.update();
    }

    public void collisionWhisker(){

    }

    /**
     *
     */
    @Override
    public void onUltraSonic() {

        collisionDetectionCallback.onFrontCollision();
    }
}
