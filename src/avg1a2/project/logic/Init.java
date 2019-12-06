package avg1a2.project.logic;

import TI.SerialConnection;
import TI.Servo;
import avg1a2.project.hardware.sensor.bluetooth.BluetoothSensor;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.controller.BlueBotControl;
import avg1a2.project.modules.controller.MotionControl;
import avg1a2.project.modules.controller.RemoteControl;
import avg1a2.project.modules.controller.SignalControl;
import avg1a2.project.modules.data.DataStore;
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
        buildIrConversion(dataStore);
        buildSensors(dataStore);
        setSensors(dataStore);
        setModules(dataStore);
        setSignals(dataStore);
        return dataStore;
    }

    /**
     * Sets the servo's in the DataStore.
     * @param dataStore The DataStore which it needs to fill with new servo's.
     */
    private static void buildServos(DataStore dataStore) {
        dataStore.setSLeft(new Servo(12));
        dataStore.setSRight(new Servo(13));
    }

    /**
     * Builds the Motion Control and adds it to the DataStore.
     * @param dataStore The DataStore which it needs to fill with new controllers.
     */
    private static void buildControllers(DataStore dataStore) {
        dataStore.setMotionControl(new MotionControl(dataStore.getSLeft(),dataStore.getSRight()));
        dataStore.setRemoteControl(new RemoteControl(dataStore.getMotionControl()));
        dataStore.setBlueBotControl(new BlueBotControl());
        dataStore.setSignalControl(new SignalControl());
    }

    /**
     * Builds all State objects used in the program and adds to to both the DataStore and sets it in the correct controller.
     * @param dataStore The DataStore which it needs to fill with a new States.
     */
    private static void buildState(DataStore dataStore) {
        dataStore.newProgramState(new State());
        dataStore.getProgramState().addState("Override");
        dataStore.getProgramState().addState("BlueBot");

        dataStore.newMotionState(new State());
        dataStore.getMotionState().addState("Idle");
        dataStore.getMotionState().addState("Executing");
        dataStore.getMotionControl().newState(dataStore.getMotionState());

        dataStore.newMotionAction(new State());
        dataStore.getMotionAction().addState("TurnDegrees");
        dataStore.getMotionAction().addState("None");
        dataStore.getMotionControl().newAction(dataStore.getMotionAction());
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

        LedGroup turnLeft = new LedGroup();
        turnLeft.addLed("turnLED1", new NeoPixel(0, 255, 255, 0));
        turnLeft.addLed("turnLED2", new NeoPixel(3, 255, 255, 0));
        dataStore.addLedGroup("turnLeftLEDs", turnLeft);

        LedGroup turnRight = new LedGroup();
        turnRight.addLed("turnRightLED1", new NeoPixel(2, 255, 255, 0));
        turnRight.addLed("turnRightLED2", new NeoPixel(5, 255, 255, 0));
        dataStore.addLedGroup("turnRightLEDs", turnRight);

        dataStore.setWarningSpeaker(new Speaker(2, 1000, 500));
    }

    /**
     * Builds the Collision detection object, and makes sure it's supplied with the correct ledGroups.
     * @param dataStore The DataStore which it needs to fill with a new CollisionDetection.
     */
    private static void buildCollisionDetection(DataStore dataStore) {
        dataStore.setCollisionDetection(new CollisionDetection(dataStore.getRemoteControl(), dataStore.getSignalControl()));
    }

    /**
     * Builds the IrConversion object and adds it to the dataStore.
     * @param dataStore The DataStore which it needs to fill with a new IrConversion.
     */
    private static void buildIrConversion(DataStore dataStore) {
        dataStore.setIrConversion(new IRConversion(dataStore.getRemoteControl()));
    }

    /**
     * Builds the Sensors used by the program and adds it to the DataStore.
     * @param dataStore The DataStore which it needs to fill with new Sensors.
     */
    private static void buildSensors(DataStore dataStore) {
        dataStore.setIrSensor(new IRSensor(15,dataStore.getIrConversion()));
        dataStore.setUltrasonicSensor(new UltrasonicSensor(9,8,dataStore.getCollisionDetection()));
        dataStore.setBluetoothSensor(new BluetoothSensor(new SerialConnection(115200),dataStore.getBlueBotControl()));
    }

    /**
     * Adds all set sensors to the correct objects inside the DataStore.
     * @param dataStore The DataStore in which to set the Sensors.
     */
    private static void setSensors(DataStore dataStore) {
        dataStore.getCollisionDetection().setUltrasonicSensor(dataStore.getUltrasonicSensor());
        dataStore.getIrConversion().setIrSensor(dataStore.getIrSensor());
        dataStore.getBlueBotControl().setBluetoothSensor(dataStore.getBluetoothSensor());
    }

    /**
     * Sets the required modules to the correct controllers.
     * @param dataStore The DataStore in which to set the collisionDetection.
     */
    private static void setModules(DataStore dataStore) {
        dataStore.getRemoteControl().setCollisionDetection(dataStore.getCollisionDetection());
        dataStore.getRemoteControl().setIrConversion(dataStore.getIrConversion());
    }

    private static void setSignals(DataStore dataStore) {
        dataStore.getSignalControl().setIdle(dataStore.getLedGroup("idle"));
        dataStore.getSignalControl().setCollision(dataStore.getLedGroup("collision"));
        dataStore.getSignalControl().setTurnLeftLEDs(dataStore.getLedGroup("turnLeftLEDs"));
        dataStore.getSignalControl().setTurnRightLEDs(dataStore.getLedGroup("turnRightLEDs"));
        dataStore.getSignalControl().setWarningSpeaker(dataStore.getWarningSpeaker());
    }
}
