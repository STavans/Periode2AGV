package avg1a2.project.logic;

import TI.SerialConnection;
import TI.Servo;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothSensor;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.linedetection.LineDetection;
import avg1a2.project.hardware.sensor.ultrasonic.BackUltraSonicSensor;
import avg1a2.project.hardware.sensor.ultrasonic.FrontUltraSonicSensor;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.bluetoothconversion.BluetoothConversion;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.controller.*;
import avg1a2.project.modules.data.DataStore;
import avg1a2.project.modules.data.Route;
import avg1a2.project.modules.irconversion.IRConversion;

/**
 * Startup "script" which initializes and builds the necessary data in the DataStore.
 */
class Init {
    /**
     * Initializes a new DataStore.
     * Then calls the private static functions in order to build the data.
     * @return the filled DataStore to be used in the rest of the program.
     */
    static DataStore buildData() {
        DataStore dataStore = new DataStore();
        buildServos(dataStore);
        buildControllers(dataStore);
        buildState(dataStore);
        buildSignals(dataStore);
        buildCollisionDetection(dataStore);
        buildConversions(dataStore);
        buildSensors(dataStore);
        buildRoutes(dataStore);
        setSensors(dataStore);
        setModules(dataStore);
        setSignals(dataStore);
        setRoutes(dataStore);
        return dataStore;
    }

    /**
     * Sets the servo's in the DataStore.
     * @param dataStore The DataStore which it needs to fill with new servo's.
     */
    private static void buildServos(DataStore dataStore) {
        dataStore.setSLeft(new Servo(13));
        dataStore.setSRight(new Servo(12));
    }

    /**
     * Builds the Motion Control and adds it to the DataStore.
     * @param dataStore The DataStore which it needs to fill with new controllers.
     */
    private static void buildControllers(DataStore dataStore) {
        dataStore.setSignalControl(new SignalControl());
        dataStore.setMotionControl(new MotionControl(dataStore.getSLeft(),dataStore.getSRight(), dataStore.getSignalControl()));
        dataStore.setRemoteControl(new RemoteControl(dataStore.getMotionControl(),dataStore.getSignalControl()));
        dataStore.setRouteControl(new RouteControl(dataStore.getMotionControl()));
        dataStore.setBlueBotControl(new BlueBotControl(dataStore.getRouteControl(),dataStore.getSignalControl()));
    }

    /**
     * Builds all State objects used in the program and adds to to both the DataStore and sets it in the correct controller.
     * @param dataStore The DataStore which it needs to fill with a new States.
     */
    private static void buildState(DataStore dataStore) {
        dataStore.newProgramState(new State());
        dataStore.getProgramState().addState("Override");
        dataStore.getProgramState().addState("BlueBot");

        dataStore.newSignalState(new State());
        dataStore.getSignalState().addState("Idle");
        dataStore.getSignalState().addState("DriveFW");
        dataStore.getSignalState().addState("DriveBW");
        dataStore.getSignalState().addState("TurnL");
        dataStore.getSignalState().addState("TurnR");
        dataStore.getSignalState().addState("Collision");
        dataStore.getSignalControl().newState(dataStore.getSignalState());

        dataStore.newMotionState(new State());
        dataStore.getMotionState().addState("Idle");
        dataStore.getMotionState().addState("BackCollision");
        dataStore.getMotionState().addState("FrontCollision");
        dataStore.getMotionState().addState("FullCollision");
        dataStore.getMotionState().addState("Turning");
        dataStore.getMotionState().addState("Accelerating");
        dataStore.getMotionControl().newState(dataStore.getMotionState());

        dataStore.newRouteState(new State());
        dataStore.getRouteState().addState("GoForward");
        dataStore.getRouteState().addState("Stop");
        dataStore.getRouteState().addState("TurnLeft");
        dataStore.getRouteState().addState("TurnRight");
        dataStore.getRouteState().addState("Turning");
        dataStore.getRouteState().addState("Idle");
        dataStore.getRouteState().addState("Running");
        dataStore.getRouteState().addState("Servicing");
        dataStore.getRouteState().addState("Finished");
        dataStore.getRouteState().addState("End");
        dataStore.getRouteControl().setState(dataStore.getRouteState());
    }

    /**
     * Builds the used ledGroups and stores them in the dataStore.
     * @param dataStore The DataStore which it needs to fill with a new LedGroups.
     */
    private static void buildSignals(DataStore dataStore) {
        LedGroup idle = new LedGroup(); //used by the collision detector to signify an idle state.
        idle.addLed("idle1",new NeoPixel(0,50,255,255,255));
        idle.addLed("idle2",new NeoPixel(1,50,255,255,255));
        idle.addLed("idle3",new NeoPixel(2,50,255,255,255));
        idle.addLed("idle4",new NeoPixel(3,50,255,255,255));
        idle.addLed("idle5",new NeoPixel(4,50,255,255,255));
        idle.addLed("idle6",new NeoPixel(5,50,255,255,255));
        dataStore.addLedGroup("idle",idle);

        LedGroup collision = new LedGroup(); // used by the collision detector to signify a collided state.
        collision.addLed("collision1", new NeoPixel(0, 255,0,0));
        collision.addLed("collision2", new NeoPixel(1, 255,0,0));
        collision.addLed("collision3", new NeoPixel(2, 255,0,0));
        collision.addLed("collision4", new NeoPixel(3, 255,0,0));
        collision.addLed("collision5", new NeoPixel(4, 255,0,0));
        collision.addLed("collision6", new NeoPixel(5, 255,0,0));
        dataStore.addLedGroup("collision",collision);

        LedGroup forward = new LedGroup();
        forward.addLed("forwardLED1", new NeoPixel(3, 0, 255, 0));
        forward.addLed("forwardLED2", new NeoPixel(4, 0, 255, 0));
        forward.addLed("forwardLED3", new NeoPixel(5, 0, 255, 0));
        dataStore.addLedGroup("forward", forward);

        LedGroup reverse = new LedGroup();
        reverse.addLed("reverseLED1", new NeoPixel(0, 176, 112, 23));
        reverse.addLed("reverseLED2", new NeoPixel(1, 176, 112, 23));
        reverse.addLed("reverseLED3", new NeoPixel(2, 176, 112, 23));
        dataStore.addLedGroup("reverse", reverse);

        LedGroup turnLeft = new LedGroup();
        turnLeft.addLed("turnLED1", new NeoPixel(2, 255, 255, 0));
        turnLeft.addLed("turnLED2", new NeoPixel(3, 255, 255, 0));
        dataStore.addLedGroup("turnLeftLEDs", turnLeft);

        LedGroup turnRight = new LedGroup();
        turnRight.addLed("turnRightLED1", new NeoPixel(0, 255, 255, 0));
        turnRight.addLed("turnRightLED2", new NeoPixel(5, 255, 255, 0));
        dataStore.addLedGroup("turnRightLEDs", turnRight);

        LedGroup followRoute = new LedGroup();
        followRoute.addLed("followRoute1", new NeoPixel(0, 0,255,255));
        followRoute.addLed("followRoute2", new NeoPixel(1, 0,255,255));
        followRoute.addLed("followRoute3", new NeoPixel(2, 0,255,255));
        followRoute.addLed("followRoute4", new NeoPixel(3, 0,255,255));
        followRoute.addLed("followRoute5", new NeoPixel(4, 0,255,255));
        followRoute.addLed("followRoute6", new NeoPixel(5, 0,255,255));
        dataStore.addLedGroup("followRouteLEDs", followRoute);
    }

    /**
     * Builds the Collision detection object, and makes sure it's supplied with the correct ledGroups.
     * @param dataStore The DataStore which it needs to fill with a new CollisionDetection.
     */
    private static void buildCollisionDetection(DataStore dataStore) {
        dataStore.setCollisionDetection(new CollisionDetection(dataStore.getMotionControl()));
    }

    /**
     * Builds the Conversion object and adds it to the dataStore.
     * @param dataStore The DataStore which it needs to fill with a new Conversions.
     */
    private static void buildConversions(DataStore dataStore) {
        dataStore.setIrConversion(new IRConversion(dataStore.getRemoteControl(),dataStore.getBlueBotControl(),dataStore.getProgramState()));
        dataStore.setBluetoothConversion(new BluetoothConversion(dataStore.getBlueBotControl()));
    }

    /**
     * Builds the Sensors used by the program and adds it to the DataStore.
     * @param dataStore The DataStore which it needs to fill with new Sensors.
     */
    private static void buildSensors(DataStore dataStore) {
        dataStore.setIrSensor(new IRSensor(1,dataStore.getIrConversion()));
        dataStore.setUltrasonicSensor(new FrontUltraSonicSensor(7,8,dataStore.getCollisionDetection()));
        dataStore.setBackUltrasonicSensor(new BackUltraSonicSensor(4,5,dataStore.getCollisionDetection()));
        dataStore.setBluetoothSensor(new BluetoothSensor(new SerialConnection(115200),dataStore.getBluetoothConversion()));
        dataStore.setLineDetection(new LineDetection(3,2,1,0,900,500,dataStore.getRouteControl()));
    }

    /**
     * Builds the Routes used by the program and adds it to the DataStore.
     * @param dataStore The DataStore which it needs to fill with new Routes.
     */
    private static void buildRoutes(DataStore dataStore) {
        Route route = new Route();
        route.addStep("Left");
        route.addStep("Right");
        route.addStep("Forward");
        route.addStep("Forward");
        route.addStep("Left");
        route.addStep("Stop");
        dataStore.addRoute("Default",route);
    }

    /**
     * Adds all set sensors to the correct objects inside the DataStore.
     * @param dataStore The DataStore in which to set the Sensors.
     */
    private static void setSensors(DataStore dataStore) {
        dataStore.getCollisionDetection().setUltrasonicSensor(dataStore.getUltrasonicSensor());
        dataStore.getCollisionDetection().setBackUltrasonicSensor(dataStore.getBackUltrasonicSensor());
        dataStore.getIrConversion().setIrSensor(dataStore.getIrSensor());
        dataStore.getBluetoothConversion().setBluetoothSensor(dataStore.getBluetoothSensor());
    }

    /**
     * Sets the required modules & states to the correct controllers.
     * @param dataStore The DataStore in which to set the Modules.
     */
    private static void setModules(DataStore dataStore) {
        dataStore.getRemoteControl().setIrConversion(dataStore.getIrConversion());
        dataStore.getRemoteControl().setProgramState(dataStore.getProgramState());
        dataStore.getRouteControl().setLineDetection(dataStore.getLineDetection());
        dataStore.getBlueBotControl().setProgramState(dataStore.getProgramState());
        dataStore.getBlueBotControl().setIrConversion(dataStore.getIrConversion());
        dataStore.getBlueBotControl().setBluetoothConversion(dataStore.getBluetoothConversion());
        dataStore.getMotionControl().setCollisionDetection(dataStore.getCollisionDetection());
    }

    /**
     * sets the led groups to the signal Control
     * @param dataStore The DataStore in which to set the Signals.
     */

    private static void setSignals(DataStore dataStore) {
        dataStore.getSignalControl().setRemoteControl(dataStore.getLedGroup("idle"));
        dataStore.getSignalControl().setCollision(dataStore.getLedGroup("collision"));
        dataStore.getSignalControl().setTurnLeftLEDs(dataStore.getLedGroup("turnLeftLEDs"));
        dataStore.getSignalControl().setTurnRightLEDs(dataStore.getLedGroup("turnRightLEDs"));
        dataStore.getSignalControl().setBlueBot(dataStore.getLedGroup("followRouteLEDs"));
        dataStore.getSignalControl().setForward(dataStore.getLedGroup("forward"));
        dataStore.getSignalControl().setReverse(dataStore.getLedGroup("reverse"));
        dataStore.getSignalControl().blueBot();
        dataStore.getSignalControl().boeBotOn();
    }

    /**
     * Sets the default route to the SignalControl.
     * @param dataStore The DataStore in which to set the Routes.
     */
    private static void setRoutes(DataStore dataStore) {
        dataStore.getRouteControl().setRoute(dataStore.getRoute("Default"));
    }
}
