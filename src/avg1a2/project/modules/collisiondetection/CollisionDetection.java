package avg1a2.project.modules.collisiondetection;

import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ultrasonic.UltraSonicCallback;
import avg1a2.project.modules.controller.SignalControl;

/**
 * Manages all CollisionDetection in the program, whenever one is detected, this will be signalled to the controllers.
 */
public class CollisionDetection implements UltraSonicCallback {
    private CollisionDetectionCallback remoteCallback;
    private CollisionDetectionCallback routeCallback;
    private SignalControl signalControl;
    private Component ultrasonicSensor;
    private Timer timer;
    private boolean isCollision;

    /**
     * Constructor sets the required parameters, except for the sensor to use.
     * @param remoteCallback The controller to which to signal the collision to.
     * @param
     */
    public CollisionDetection(CollisionDetectionCallback remoteCallback, CollisionDetectionCallback routeCallback,SignalControl signalControl){
        this.signalControl = signalControl;
        this.remoteCallback = remoteCallback;
        this.routeCallback = routeCallback;
        this.isCollision = false;
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
                signalControl.boeBotOn();
            }
            signalControl.setWarningSpeakerOn(); // needs to be updated to support the update functionality.
        }

    }

    /**
     *Calls the collisionDetectionCallback
     **/
    public void onUltraSonic() {
        if (!isCollision) {
            remoteCallback.onFrontCollision(); //todo split up the calls.
            routeCallback.routeCollision();
        }
        this.signalControl.boeBotCollision();
        isCollision = true;
        timer = new Timer(500);
    }

    public void closeUltraSonic(){
        if(!isCollision){
            remoteCallback.emergencyCollision(); //todo split up the calls.
            routeCallback.routeEmergencyCollision();
        }
        signalControl.boeBotCollision();
        isCollision = true;
        timer = new Timer(500);

    }
}
