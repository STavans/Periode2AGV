package avg1a2.project.modules.collisiondetection;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;

public class CollisionDetection implements UltraSonicCallback {

    private CollisionDetectionCallback collisionDetectionCallback;
    private Component ultrasonicSensor;

    /**
     * @param collisionDetection gets initialised in the constructor
     **/
    public CollisionDetection(CollisionDetectionCallback collisionDetection){
        this.collisionDetectionCallback = collisionDetection;
    }

    public void setUltrasonicSensor(Component ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
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
