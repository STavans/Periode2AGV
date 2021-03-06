package avg1a2.project.modules.data;

import TI.Servo;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothSensor;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.linedetection.LineDetection;
import avg1a2.project.hardware.sensor.ultrasonic.BackUltraSonicSensor;
import avg1a2.project.hardware.sensor.ultrasonic.FrontUltraSonicSensor;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.logic.State;
import avg1a2.project.modules.bluetoothconversion.BluetoothConversion;
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
    private BluetoothConversion bluetoothConversion;
    private State programState;
    private State motionState;
    private State routeState;
    private State signalState;
    private Component irSensor;
    private Component frontUltraSonicSensor;
    private Component backUltraSonicSensor;
    private Component bluetoothSensor;
    private Component lineDetection;
    private HashMap<String, Route> routes;
    private HashMap<String, LedGroup> ledGroups;
    private Servo sLeft;
    private Servo sRight;

    /**
     * Constructor initiates the hashMap used for ledGroups.
     */
    public DataStore() {
        this.ledGroups = new HashMap<>();
        this.routes = new HashMap<>();
    }

    /**
     * Sets the motionControl in the DataStore.
     * @param motionControl The MotionControl which to add to the DataStore.
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
        if (this.motionControl == null) {
            throw new IllegalArgumentException("Motion Control has not been initialized");
        } else {
            return this.motionControl;
        }
    }

    /**
     * Sets the RemoteControl in the DataStore.
     * @param remoteControl The remoteControl which to add to the DataStore.
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
        if (this.remoteControl == null) {
            throw new IllegalArgumentException("Remote Control has not been initialized");
        } else {
            return this.remoteControl;
        }
    }

    /**
     * Sets the BluetoothControl in the DataStore.
     * @param blueBotControl The blueBotControl which to add to the DataStore.
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
        if (this.blueBotControl == null) {
            throw new IllegalArgumentException("BlueBot Control has not been initialized");
        } else {
            return this.blueBotControl;
        }
    }

    /**
     * Sets the CollisionDetection in the DataStore.
     * @param collisionDetection The collisionDetection which to add to the DataStore.
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
        if (this.collisionDetection == null) {
            throw new IllegalArgumentException("Collision Detection has not been initialized");
        } else {
            return this.collisionDetection;
        }
    }

    /**
     * Sets the SignalControl in the DataStore.
     * @param signalControl The signalControl which to add to the DataStore.
     */
    public void setSignalControl(SignalControl signalControl){
        this.signalControl = signalControl;
    }

    /**
     * Gets the signalControl from the DataStore.
     * @return signalControl object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public SignalControl getSignalControl(){
        if(this.signalControl == null){
            throw new IllegalArgumentException("The signal controls have not been initialized");
        } else {
            return this.signalControl;
        }
    }

    /**
     * Sets the routeControl in the DataStore.
     * @param routeControl The routeControl which to add to the DataStore.
     */
    public void setRouteControl(RouteControl routeControl){
        this.routeControl = routeControl;
    }

    /**
     * Gets the routeControl from the DataStore.
     * @return routeControl object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public RouteControl getRouteControl(){
        if(this.routeControl == null){
            throw new IllegalArgumentException("The route controls have not been initialized");
        } else {
            return this.routeControl;
        }
    }

    /**
     * Sets the irConversion in the DataStore.
     * @param irConversion The irConversion which to add to the DataStore.
     */
    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    /**
     * Gets the irConversion from the DataStore.
     * @return irConversion object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public IRConversion getIrConversion() throws IllegalArgumentException {
        if (this.irConversion == null) {
            throw new IllegalArgumentException("Ir Conversion has not been initialized");
        } else {
            return this.irConversion;
        }
    }

    /**
     * Sets the bluetoothConversion in the DataStore.
     * @param bluetoothConversion The bluetoothConversion which to add to the DataStore.
     */
    public void setBluetoothConversion(BluetoothConversion bluetoothConversion) {
        this.bluetoothConversion = bluetoothConversion;
    }

    /**
     * Gets the BluetoothConversion from the DataStore.
     * @return BluetoothConversion object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public BluetoothConversion getBluetoothConversion() throws IllegalArgumentException {
        if (this.bluetoothConversion == null) {
            throw new IllegalArgumentException("Bluetooth Conversion has not been initialized");
        } else {
            return this.bluetoothConversion;
        }
    }

    /**
     * Sets the programState in the DataStore.
     * @param programState The State which to add to the DataStore.
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
        if (this.programState == null) {
            throw new IllegalArgumentException("State has not been initialized");
        } else {
            return this.programState;
        }
    }

    /**
     * Sets the motionState in the DataStore.
     * @param motionState The State which to add to the DataStore.
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
        if (this.motionState == null) {
            throw new IllegalArgumentException("State has not been initialized");
        } else {
            return this.motionState;
        }
    }

    /**
     * Sets the signalState in the DataStore.
     * @param signalState The State which to add to the DataStore.
     */
    public void newSignalState(State signalState){
        this.signalState = signalState;
    }

    /**
     * Gets the signalState from the DataStore.
     * @return signalState object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public State getSignalState(){
        if(this.signalState == null){
            throw new IllegalArgumentException("SignalState has not been initialized");
        } else {
            return this.signalState;
        }
    }

    /**
     * Sets the routeState in the DataStore.
     * @param routeState The State which to add to the DataStore.
     */
    public void newRouteState(State routeState) {
        this.routeState = routeState;
    }

    /**
     * Gets the routeState from the DataStore.
     * @return routeState object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public State getRouteState() throws IllegalArgumentException {
        if (this.routeState == null) {
            throw new IllegalArgumentException("State has not been initialized");
        } else {
            return this.routeState;
        }
    }

    /**
     * Sets the irSensor in the DataStore.
     * @param irSensor The Sensor which to add to the DataStore.
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
        if (this.irSensor == null) {
            throw new IllegalArgumentException("Ir Sensor has not been initialized");
        } else {
            return this.irSensor;
        }
    }

    /**
     * Sets the FrontUltraSonicSensor in the DataStore.
     * @param frontUltraSonicSensor Sensor which to add to the DataStore.
     */
    public void setUltrasonicSensor(FrontUltraSonicSensor frontUltraSonicSensor) {
        this.frontUltraSonicSensor = frontUltraSonicSensor;
    }

    /**
     * Gets the FrontUltraSonicSensor from the DataStore.
     * @return FrontUltraSonicSensor object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getUltrasonicSensor() throws IllegalArgumentException {
        if (this.frontUltraSonicSensor == null) {
            throw new IllegalArgumentException("Front Ultrasonic Sensor has not been initialized");
        } else {
            return this.frontUltraSonicSensor;
        }
    }

    /**
     * Sets the backUltraSonicSensor in the DataStore.
     * @param backUltraSonicSensor Sensor which to add to the DataStore.
     */
    public void setBackUltrasonicSensor(BackUltraSonicSensor backUltraSonicSensor) {
        this.backUltraSonicSensor = backUltraSonicSensor;
    }

    /**
     * Gets the backUltraSonicSensor from the DataStore.
     * @return backUltraSonicSensor object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Component getBackUltrasonicSensor() throws IllegalArgumentException {
        if (this.backUltraSonicSensor == null) {
            throw new IllegalArgumentException("Back Ultrasonic Sensor has not been initialized");
        } else {
            return this.backUltraSonicSensor;
        }
    }

    /**
     * Sets the BluetoothSensor in the DataStore.
     * @param bluetoothSensor Sensor which to add to the DataStore.
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
        if (this.bluetoothSensor == null) {
            throw new IllegalArgumentException("Bluetooth Sensor has not been initialized");
        } else {
            return this.bluetoothSensor;
        }
    }

    /**
     * Sets the LineDetection in the DataStore.
     * @param lineDetection LineDetection which to add to the DataStore.
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
        if (this.lineDetection == null) {
            throw new IllegalArgumentException("Line detection has not been initialized");
        } else {
            return this.lineDetection;
        }
    }

    /**
     * Gets the ledGroup from the DataStore.
     * @param ledGroup Name of the LedGroup to get.
     * @return ledGroup object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public LedGroup getLedGroup(String ledGroup) {
        if (this.ledGroups.containsKey(ledGroup)) {
            return this.ledGroups.get(ledGroup);
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
        this.ledGroups.put(groupName,ledGroup);
    }

    /**
     * Gets the Route from the DataStore.
     * @param route Name of the Route to get.
     * @return Route object.
     * @throws IllegalArgumentException Exception if object is not set.
     */
    public Route getRoute(String route) {
        if (this.routes.containsKey(route)) {
            return this.routes.get(route);
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
        this.routes.put(name,route);
    }

    /**
     * Sets the left servo in the DataStore.
     * @param sLeft Servo which to add to the DataStore.
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
        if (this.sLeft == null) {
            throw new IllegalArgumentException("Left servo has not been initialized");
        } else {
            return this.sLeft;
        }
    }

    /**
     * Sets the right servo in the DataStore.
     * @param sRight Servo which to add to the DataStore.
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
        if (this.sRight == null) {
            throw new IllegalArgumentException("Right servo has not been initialized");
        } else {
            return this.sRight;
        }
    }
}
