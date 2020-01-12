package avg1a2.project.modules.collisiondetection;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ultrasonic.BackUltraSonicCallback;
import avg1a2.project.hardware.sensor.ultrasonic.FrontUltraSonicCallback;

/**
 * Manages all CollisionDetection in the program, whenever one is detected, this will be signalled to the controllers.
 */
public class CollisionDetection implements FrontUltraSonicCallback, BackUltraSonicCallback {
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
        if (this.ultrasonicSensor == null) {
            throw new RuntimeException("Ultrasonic sensor has not been assigned");
        } else {
            this.ultrasonicSensor.update();
        }

        if (this.backUltrasonicSensor == null) {
            throw new RuntimeException("Back Ultrasonic sensor has not been assigned");
        } else {
            this.backUltrasonicSensor.update();
        }
    }

    /*
     *This method is called upon when the backUltraSonic sensor detects an object from a farther distance than the backEmergencyUltraSonic
     */
    @Override
    public void onBackUltraSonic() {
        this.callback.onBackCollision();
    }

    /*
     *This method is called upon when the backUltraSonic detects an object directly in front of it
     */
    @Override
    public void onBackCloseUltraSonic() {
        this.callback.onBackEmergencyCollision();
    }

    /*
     *This method is called upon once the backUltraSonic does NOT detect an object
     */
    @Override
    public void onBackFarUltraSonic() {
        this.callback.backCollisionDone();
    }

    /*
     *This method is called upon when the frontUltraSonic detects an object from a further distance than the frontEmergencyUltraSonic
     */
    @Override
    public void onFrontUltraSonic() {
        this.callback.onFrontCollision();
    }

    /*
     *This method is called upon once the frontUltraSonic detects an object directly in front of it
     */
    @Override
    public void onFrontCloseUltraSonic() {
        this.callback.onFrontEmergencyCollision();
    }

    /*
     *This method is called upon once the frontUltraSonic does not detect an object
     */
    @Override
    public void onFrontFarUltraSonic() {
        this.callback.frontCollisionDone();
    }
}
