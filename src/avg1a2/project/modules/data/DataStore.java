package avg1a2.project.modules.data;

import TI.Servo;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothSensor;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.linedetection.LineDetection;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor2;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.logic.State;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.controller.*;
import avg1a2.project.modules.irconversion.IRConversion;

import java.util.HashMap;

/**
 * DataStore which stores all objects used in the program.
 */
public class DataStore {
    private MotionControl motionControl;
    private RemoteControl remoteControl;
    private BlueBotControl blueBotControl;
    private CollisionDetection collisionDetection;
    private SignalControl signalControl;
    private RouteControl routeControl;
    private IRConversion irConversion;
    private State programState;
    private State motionState;
    private State routeState;
    private Component irSensor;
    private Component ultrasonicSensor;
    private Component backUltraSonicSensor;
    private Component bluetoothSensor;
    private Component lineDetection;
    private Component warningSpeaker;
    private HashMap<String, Route> routes;
    private HashMap<String, LedGroup> ledGroups;
    private Servo sLeft;
    private Servo sRight;

    /**
     * Constructor initiates the hashMap used for ledGroups.
     */
    public DataStore() {
        ledGroups = new HashMap<>();
        routes = new HashMap<>();
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
     * Sets the BluetoothControl in the DataStore.
     */
    public void setBlueBotControl(BlueBotControl blueBotControl) {
        this.blueBotControl = blueBotControl;
    }

    /**
     * Gets the BlueBotControl from the DataStore.
     * @return BlueBotControl object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public BlueBotControl getBlueBotControl() throws IllegalArgumentException {
        if (blueBotControl == null) {
            throw new IllegalArgumentException("BlueBot Control has not been initialized");
        } else {
            return this.blueBotControl;
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

    public void setSignalControl(SignalControl signalControl){
        this.signalControl = signalControl;
    }

    public SignalControl getSignalControl(){
        if(this.signalControl == null){
            throw new IllegalArgumentException("The signal controls have not been initialized");
        } else {
            return this.signalControl;
        }
    }

    public void setRouteControl(RouteControl routeControl){
        this.routeControl = routeControl;
    }

    public RouteControl getRouteControl(){
        if(this.routeControl == null){
            throw new IllegalArgumentException("The route controls have not been initialized");
        } else {
            return this.routeControl;
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
     * Sets the routeState in the DataStore.
     */
    public void newRouteState(State routeState) {
        this.routeState = routeState;
    }

    /**
     * Gets the routeState from the DataStore.
     * @return routeState object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public State getRoutState() throws IllegalArgumentException {
        if (routeState == null) {
            throw new IllegalArgumentException("State has not been initialized");
        } else {
            return this.routeState;
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
     * Sets the UltrasonicSensor2 in the DataStore.
     */
    public void setUltrasonicSensor(UltrasonicSensor2 ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
    }

    /**
     * Gets the UltrasonicSensor2 from the DataStore.
     * @return UltrasonicSensor2 object.
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
     * Sets the UltrasonicSensor2 in the DataStore.
     */
    public void setBackUltrasonicSensor(UltrasonicSensor2 backUltraSonicSensor) {
        this.backUltraSonicSensor = backUltraSonicSensor;
    }

    /**
     * Gets the UltrasonicSensor2 from the DataStore.
     * @return UltrasonicSensor2 object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getBackUltrasonicSensor() throws IllegalArgumentException {
        if (backUltraSonicSensor == null) {
            throw new IllegalArgumentException("Ultrasonic Sensor has not been initialized");
        } else {
            return this.backUltraSonicSensor;
        }
    }

    /**
     * Sets the BluetoothSensor in the DataStore.
     */
    public void setBluetoothSensor(BluetoothSensor bluetoothSensor) {
        this.bluetoothSensor = bluetoothSensor;
    }

    /**
     * Gets the BluetoothSensor from the DataStore.
     * @return BluetoothSensor object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getBluetoothSensor() throws IllegalArgumentException {
        if (bluetoothSensor == null) {
            throw new IllegalArgumentException("Bluetooth Sensor has not been initialized");
        } else {
            return this.bluetoothSensor;
        }
    }

    /**
     * Sets the LineDetection in the DataStore.
     */
    public void setLineDetection(LineDetection lineDetection) {
        this.lineDetection = lineDetection;
    }

    /**
     * Gets the LineDetection from the DataStore.
     * @return LineDetection object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getLineDetection() throws IllegalArgumentException {
        if (lineDetection == null) {
            throw new IllegalArgumentException("Line detection has not been initialized");
        } else {
            return this.lineDetection;
        }
    }

    /**
     * Sets the warningSpeaker in the DataStore.
     */
    public void setWarningSpeaker(Speaker speaker) {
        this.warningSpeaker = speaker;
    }

    /**
     * Gets the speaker from the DataStore.
     * @return speaker object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getWarningSpeaker() throws IllegalArgumentException {
        if (warningSpeaker == null) {
            throw new IllegalArgumentException("WarningSpeaker has not been initialized");
        } else {
            return this.warningSpeaker;
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
     * Gets the Route from the DataStore.
     * @param route Name of the Route to get.
     * @return Route object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Route getRoute(String route) {
        if (routes.containsKey(route)) {
            return routes.get(route);
        } else {
            throw new IllegalArgumentException("The Route has not been added");
        }
    }

    /**
     * Adds the route to the routes hashMap.
     * @param name Name of the route to add.
     * @param route The route itself.
     */
    public void addRoute(String name, Route route) {
        routes.put(name,route);
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
