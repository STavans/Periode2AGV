package avg1a2.project.modules.data;

import TI.Servo;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.logic.State;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.controller.MotionControl;
import avg1a2.project.modules.controller.RemoteControl;
import avg1a2.project.modules.irconversion.IRConversion;
import java.util.HashMap;

/**
 * DataStore which stores all objects used in the program.
 */
public class DataStore {
    private MotionControl motionControl;
    private RemoteControl remoteControl;
    private CollisionDetection collisionDetection;
    private IRConversion irConversion;
    private State programState;
    private State motionState;
    private State motionAction;
    private Component irSensor;
    private Component ultrasonicSensor;
    private Component speaker;
    private HashMap<String, LedGroup> ledGroups;
    private Servo sLeft;
    private Servo sRight;

    /**
     * Constructor initiates the hashMap used for ledGroups.
     */
    public DataStore() {
        ledGroups = new HashMap<>();
    }

    /**
     * Sets the motionControl in the DataStore.
     */
    public void setMotionControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    /**
     * Gets the MotionControl from the DataStore.
     * @return MotionControl object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public MotionControl getMotionControl() throws IllegalArgumentException {
        if (motionControl == null) {
            throw new IllegalArgumentException("Motion Control has not been initialized");
        } else {
            return this.motionControl;
        }
    }

    /**
     * Sets the RemoteControl in the DataStore.
     */
    public void setRemoteControl(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    /**
     * Gets the RemoteControl from the DataStore.
     * @return RemoteControl object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public RemoteControl getRemoteControl() throws IllegalArgumentException {
        if (remoteControl == null) {
            throw new IllegalArgumentException("Remote Control has not been initialized");
        } else {
            return this.remoteControl;
        }
    }

    /**
     * Sets the CollisionDetection in the DataStore.
     */
    public void setCollisionDetection(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection;
    }

    /**
     * Gets the CollisionDetection from the DataStore.
     * @return CollisionDetection object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public CollisionDetection getCollisionDetection() throws IllegalArgumentException {
        if (collisionDetection == null) {
            throw new IllegalArgumentException("Collision Detection has not been initialized");
        } else {
            return this.collisionDetection;
        }
    }

    /**
     * Sets the IRConversion in the DataStore.
     */
    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    /**
     * Gets the IRConversion from the DataStore.
     * @return IRConversion object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public IRConversion getIrConversion() throws IllegalArgumentException {
        if (irConversion == null) {
            throw new IllegalArgumentException("Ir Conversion has not been initialized");
        } else {
            return this.irConversion;
        }
    }

    /**
     * Sets the programState in the DataStore.
     */
    public void newProgramState(State programState) {
        this.programState = programState;
    }

    /**
     * Gets the programState from the DataStore.
     * @return programState object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public State getProgramState() throws IllegalArgumentException {
        if (programState == null) {
            throw new IllegalArgumentException("State has not been initialized");
        } else {
            return this.programState;
        }
    }

    /**
     * Sets the motionState in the DataStore.
     */
    public void newMotionState(State motionState) {
        this.motionState = motionState;
    }

    /**
     * Gets the motionState from the DataStore.
     * @return motionState object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public State getMotionState() throws IllegalArgumentException {
        if (motionState == null) {
            throw new IllegalArgumentException("State has not been initialized");
        } else {
            return this.motionState;
        }
    }

    /**
     * Sets the motionAction in the DataStore.
     */
    public void newMotionAction(State motionAction) {
        this.motionAction = motionAction;
    }

    /**
     * Gets the motionAction from the DataStore.
     * @return motionAction object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public State getMotionAction() throws IllegalArgumentException {
        if (motionAction == null) {
            throw new IllegalArgumentException("State has not been initialized");
        } else {
            return this.motionAction;
        }
    }

    /**
     * Sets the irSensor in the DataStore.
     */
    public void setIrSensor(IRSensor irSensor) {
        this.irSensor = irSensor;
    }

    /**
     * Gets the irSensor from the DataStore.
     * @return irSensor object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getIrSensor() throws IllegalArgumentException {
        if (irSensor == null) {
            throw new IllegalArgumentException("Ir Sensor has not been initialized");
        } else {
            return this.irSensor;
        }
    }

    /**
     * Sets the UltrasonicSensor in the DataStore.
     */
    public void setUltrasonicSensor(UltrasonicSensor ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
    }

    /**
     * Gets the UltrasonicSensor from the DataStore.
     * @return UltrasonicSensor object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getUltrasonicSensor() throws IllegalArgumentException {
        if (ultrasonicSensor == null) {
            throw new IllegalArgumentException("Ultrasonic Sensor has not been initialized");
        } else {
            return this.ultrasonicSensor;
        }
    }

    /**
     * Sets the speaker in the DataStore.
     */
    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    /**
     * Gets the speaker from the DataStore.
     * @return speaker object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getSpeaker() throws IllegalArgumentException {
        if (speaker == null) {
            throw new IllegalArgumentException("Speaker has not been initialized");
        } else {
            return this.speaker;
        }
    }

    /**
     * Gets the ledGroups from the DataStore.
     * @return ledGroups object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public HashMap<String, LedGroup> getLedGroups() {
        if (ledGroups == null) {
            throw new IllegalArgumentException("LedGroup has not been initialized");
        } else {
            return ledGroups;
        }
    }

    /**
     * Gets the ledGroup from the DataStore.
     * @param ledGroup Name of the LedGroup to get.
     * @return ledGroup object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public LedGroup getLedGroup(String ledGroup) {
        if (ledGroups.containsKey(ledGroup)) {
            return ledGroups.get(ledGroup);
        } else {
            throw new IllegalArgumentException("The LedGroup has not been added");
        }
    }

    /**
     * Adds the ledGroup to the ledGroups hashMap.
     * @param groupName Name of the LedGroup to add.
     * @param ledGroup The LedGroup itself.
     */
    public void addLedGroup(String groupName, LedGroup ledGroup) {
        ledGroups.put(groupName,ledGroup);
    }

    /**
     * Sets the left servo in the DataStore.
     */
    public void setSLeft(Servo sLeft) {
        this.sLeft = sLeft;
    }

    /**
     * Gets the left servo from the DataStore.
     * @return left servo object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Servo getSLeft() {
        if (sLeft == null) {
            throw new IllegalArgumentException("Left servo has not been initialized");
        } else {
            return this.sLeft;
        }
    }

    /**
     * Sets the right servo in the DataStore.
     */
    public void setSRight(Servo sRight) {
        this.sRight = sRight;
    }

    /**
     * Gets the right servo from the DataStore.
     * @return right servo object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Servo getSRight() {
        if (sRight == null) {
            throw new IllegalArgumentException("Right servo has not been initialized");
        } else {
            return this.sRight;
        }
    }
}
