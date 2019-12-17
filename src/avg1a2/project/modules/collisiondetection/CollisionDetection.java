package avg1a2.project.modules.collisiondetection;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;

/**
 * Manages all CollisionDetection in the program, whenever one is detected, this will be signalled to the controllers.
 */
public class CollisionDetection implements UltraSonicCallback {
    private CollisionDetectionCallback callback;
    private Component ultrasonicSensor;
    private Component backUltrasonicSensor;

    /**
     * Constructor sets the required parameters, except for the sensor to use.
     * @param callback The controller to which to signal the collision to.
     */
    public CollisionDetection(CollisionDetectionCallback callback){
        this.callback = callback;
    }

    /**
     * Set the ultrasonic sensor to use while scanning for detections..
     * @param ultrasonicSensor The sensor to update in order to check for collisions.
     */
    public void setUltrasonicSensor(Component ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
    }

    /**
     * Set the ultrasonic sensor to use while scanning for detections..
     * @param backUltrasonicSensor The sensor to update in order to check for collisions.
     */
    public void setBackUltrasonicSensor(Component backUltrasonicSensor) {
        this.backUltrasonicSensor = backUltrasonicSensor;
    }

    /**
     *This updates the ultrasonic sensor
     **/
    public void update() {
        if (ultrasonicSensor == null) {
            throw new RuntimeException("Ultrasonic sensor has not been assigned");
        } else {
            ultrasonicSensor.update();
        }

        if (backUltrasonicSensor == null) {
            throw new RuntimeException("Back Ultrasonic sensor has not been assigned");
        } else {
            backUltrasonicSensor.update();
        }
    }

    /**
     *Calls the collisionDetectionCallback
     **/
    public void onUltraSonic() {
        callback.onFrontCollision();
    }

    public void closeUltraSonic(){
        callback.emergencyCollision();
    }

    @Override
    public void farUltraSonic() {
        callback.collisionDone();
    }
}
