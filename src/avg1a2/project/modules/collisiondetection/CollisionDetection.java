package avg1a2.project.modules.collisiondetection;

import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;
import avg1a2.project.hardware.signal.led.LedGroup;

/**
 * Manages all CollisionDetection in the program, whenever one is detected, this will be signalled to the controllers.
 */
public class CollisionDetection implements UltraSonicCallback {
    private CollisionDetectionCallback collisionDetectionCallback;
    private LedGroup idle;
    private LedGroup collision;
    private Component warningSpeaker;
    private Component runningSpeaker;
    private Component ultrasonicSensor;
    private Timer timer;
    private boolean isCollision;
    private boolean mute;

    /**
     * Constructor sets the required parameters, except for the sensor to use.
     * @param collisionDetection The controller to which to signal the collision to.
     * @param idle The LedGroup to use whenever no collision has been detected.
     * @param collision The LedGroup to use whenever a collision has been detected.
     * @param warningSpeaker Speaker to use whenever a collision has been detected.
     */
    public CollisionDetection(CollisionDetectionCallback collisionDetection, LedGroup idle, LedGroup collision, Component warningSpeaker){
        this.collisionDetectionCallback = collisionDetection;
        this.idle = idle; //Might want to manage all LEDs from a different location once we implement more.
        this.collision = collision; //Might want to manage all LEDs from a different location once we implement more.
        this.warningSpeaker = warningSpeaker;
        this.runningSpeaker = runningSpeaker;
        this.isCollision = false;
        this.mute = true;
        idle.on();
    }

    /**
     * Set the ultrasonic sensor to use while scanning for detections..
     * @param ultrasonicSensor The sensor to update in order to check for collisions.
     */
    public void setUltrasonicSensor(Component ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
    }

    /**
     * Function to easily check if there is a collision.
     * @return true if collision is detected, false if not.
     */
    public boolean isCollision() {
        return isCollision;
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
        if (isCollision) {
            if (timer != null && timer.timeout()) {
                isCollision = false;
                idle.on();
            }
            warningSpeaker.update(); // needs to be updated to support the update functionality.
        }
        if (!mute && !isCollision) {
            runningSpeaker.update();
        }
    }

    /**
     *Calls the collisionDetectionCallback
     **/
    public void onUltraSonic() {
        if (!isCollision) {
            collisionDetectionCallback.onFrontCollision();
        }
        collision.on();
        isCollision = true;
        timer = new Timer(500);
    }

    public void mute() {
        this.mute = !this.mute;
    }
}
