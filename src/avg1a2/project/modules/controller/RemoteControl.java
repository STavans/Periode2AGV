package avg1a2.project.modules.controller;

import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.data.DataStore;
import avg1a2.project.modules.irconversion.IRConversionCallback;

public class RemoteControl implements CollisionDetectionCallback, IRConversionCallback {


    public void run(DataStore dataStore) {
        CollisionDetection collisionDetectionUpdate = new CollisionDetection(this);
        collisionDetectionUpdate.update();
    }

    public void onFrontCollision() {
        MotionControl motionControl = new MotionControl();
        motionControl.emergencyBrake();

        LedGroup group = new LedGroup();
        group.addLed(" neo0",new NeoPixel(0, 10, 255, 0, 0));
        group.addLed(" neo1",new NeoPixel(1, 10, 255, 0, 0));
        group.addLed(" neo2",new NeoPixel(2, 10, 255, 0, 0));
        group.addLed(" neo3",new NeoPixel(3, 10, 255, 0, 0));
        group.addLed(" neo4",new NeoPixel(4, 10, 255, 0, 0));
        group.addLed(" neo5",new NeoPixel(5, 10, 255, 0, 0));
        group.on();

        Speaker speaker = new Speaker(3,1000, 10000);
        speaker.Beep();
    }

    public void onCommand1() {
        
    }
}
