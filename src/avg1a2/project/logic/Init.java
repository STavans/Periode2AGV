package avg1a2.project.logic;

import avg1a2.project.hardware.engine.Wheels;
import avg1a2.project.hardware.sensor.button.Button;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.sensor.whisker.Whisker;
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
        dataStore.setRemoteControl(new RemoteControl());
        dataStore.setPcControl(new PcControl());
        dataStore.setMotionControl(new MotionControl());
    }

    private static void buildState(DataStore dataStore) {
        dataStore.setState(new State("Override","Routing"));
    }

    private static void buildCollisionDetection(DataStore dataStore) {
        dataStore.setCollisionDetection(new CollisionDetection(dataStore.getRemoteControl()));
    }

    private static void buildIrConversion(DataStore dataStore) {
        dataStore.setIrConversion(new IRConversion(dataStore.getRemoteControl()));
    }

    private static void buildEngine(DataStore dataStore){
        dataStore.setWheels(new Wheels(13,14));
    }

    private static void buildSensors(DataStore dataStore) {
        dataStore.setButton(new Button(1,dataStore.getMotionControl()));
        dataStore.setIrSensor(new IRSensor(1,dataStore.getIrConversion()));
        dataStore.setUltrasonicSensor(new UltrasonicSensor(1,2,dataStore.getCollisionDetection()));
        dataStore.setWhiskerLeft(new Whisker(1));
        dataStore.setWhiskerRight(new Whisker(2));
    }

    //private static void buildSignals() {
    //
    //}
}
