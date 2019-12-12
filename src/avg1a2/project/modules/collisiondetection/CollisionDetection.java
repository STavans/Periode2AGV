package avg1a2.project.modules.collisiondetection;

import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;
import avg1a2.project.modules.controller.SignalControl;

/**
 * Manages all CollisionDetection in the program, whenever one is detected, this will be signalled to the controllers.
 */
public class CollisionDetection implements UltraSonicCallback {
    private CollisionDetectionCallback callback;
    private Component ultrasonicSensor;
    private boolean isCollision;
    //Cleanup maybe use state

    /**
     * Constructor sets the required parameters, except for the sensor to use.
     * @param callback The controller to which to signal the collision to.
     * @param
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
     *This updates the ultrasonic sensor
     **/
    public void update() {
        if (ultrasonicSensor == null) {
            throw new RuntimeException("Ultrasonic sensor has not been assigned");
        } else {
            ultrasonicSensor.update();
        }
    }

    /**
     *Calls the collisionDetectionCallback
     **/
    public void onUltraSonic() {
        callback.onFrontCollision();
    }

    /**
     * TODO Cleanup this code, and fix the collision callbacks
     * TODO Look at the motionControl class
     */
    public void closeUltraSonic(){
        callback.emergencyCollision();


    }
}
