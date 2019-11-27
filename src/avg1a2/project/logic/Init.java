package avg1a2.project.logic;

import avg1a2.project.hardware.engine.Wheels;
import avg1a2.project.hardware.sensor.button.Button;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.sensor.whisker.Whisker;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.controller.MotionControl;
import avg1a2.project.modules.controller.PcControl;
import avg1a2.project.modules.controller.RemoteControl;
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
        buildControllers(dataStore);
        buildState(dataStore);
        buildEngine(dataStore);
        buildCollisionDetection(dataStore);
        buildIrConversion(dataStore);
        buildCommandLayouts(dataStore);
        buildRoutes(dataStore);
        buildSensors(dataStore);
        setSensors(dataStore);
        return dataStore;
    }

    /**
     * Builds all configured routes into the dataStore, these will be created and defined here.
     * @param dataStore The DataStore which it needs to fill with routes.
     */
    private static void buildRoutes(DataStore dataStore) {

    }

    /**
     * Builds all configured CommandLayouts into the DataStore, these will be created and defined here.
     * @param dataStore The DataStore which it needs to fill with CommandLayouts.
     */
    private static void buildCommandLayouts(DataStore dataStore) {

    }

    /**
     * Builds the Motion Control and adds it to the DataStore.
     * @param dataStore The DataStore which it needs to fill with a new Motion Control.
     */
    private static void buildControllers(DataStore dataStore) {
        dataStore.setMotionControl(new MotionControl());
        dataStore.setRemoteControl(new RemoteControl(dataStore.getMotionControl()));
        dataStore.setPcControl(new PcControl());
    }

    private static void buildState(DataStore dataStore) {
        dataStore.setState(new State("Override","Routing"));
    }

    private static void buildCollisionDetection(DataStore dataStore) {
        LedGroup group = new LedGroup();
        group.addLed("1",new NeoPixel(0,50,255,0,0));
        group.addLed("2",new NeoPixel(1,50,255,0,0));
        group.addLed("3",new NeoPixel(2,50,255,0,0));
        group.addLed("4",new NeoPixel(3,50,255,0,0));
        group.addLed("5",new NeoPixel(4,50,255,0,0));
        group.addLed("6",new NeoPixel(5,50,255,0,0));


        LedGroup ledGroup = new LedGroup();

        ledGroup.addLed("1", new NeoPixel(0, 255,255,255));
        ledGroup.addLed("2", new NeoPixel(1, 255,255,255));
        ledGroup.addLed("3", new NeoPixel(2, 255,255,255));
        ledGroup.addLed("4", new NeoPixel(3, 255,255,255));
        ledGroup.addLed("5", new NeoPixel(4, 255,255,255));
        ledGroup.addLed("6", new NeoPixel(5, 255,255,255));


        dataStore.setCollisionDetection(new CollisionDetection(dataStore.getRemoteControl(),group, new Speaker(2, 1000, 500), ledGroup));
    }

    private static void buildIrConversion(DataStore dataStore) {
        dataStore.setIrConversion(new IRConversion(dataStore.getRemoteControl()));
    }

    private static void buildEngine(DataStore dataStore){
        dataStore.setWheels(new Wheels(12,13));
    }

    private static void buildSensors(DataStore dataStore) {
        dataStore.setButton(new Button(1,dataStore.getMotionControl()));
        dataStore.setIrSensor(new IRSensor(15,dataStore.getIrConversion()));
        dataStore.setUltrasonicSensor(new UltrasonicSensor(1,0,dataStore.getCollisionDetection()));
        dataStore.setWhiskerLeft(new Whisker(1));
        dataStore.setWhiskerRight(new Whisker(2));
    }

    private static void setSensors(DataStore dataStore) {
        dataStore.getCollisionDetection().setUltrasonicSensor(dataStore.getUltrasonicSensor());
        dataStore.getIrConversion().setIrSensor(dataStore.getIrSensor());
        dataStore.getRemoteControl().setCollisionDetection(dataStore.getCollisionDetection());
    }

    //private static void buildSignals() {
    //
    //}
}
