package avg1a2.project.modules.controller;

import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.data.DataStore;
import avg1a2.project.modules.irconversion.IRConversionCallback;
import com.sun.xml.internal.ws.api.pipe.Engine;

import javax.xml.crypto.Data;

public class RemoteControl implements CollisionDetectionCallback, IRConversionCallback {
    private MotionControl motionControl;

    public RemoteControl() {
    }

    public void run(DataStore dataStore) {
       dataStore.getCollisionDetection().update();
       dataStore.getIrSensor().update();
       if (motionControl == null) {
           motionControl = dataStore.getMotionControl();
       }
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


    @Override
    public void leftDiagonal() {
        System.out.println("WORKS!");
        stop();
        motionControl.turnDegrees(45, 200);
        forward();

    }

    @Override
    public void forward() {
        System.out.println("WORKS!");
        motionControl.accelerateToSpeed(200);
    }

    @Override
    public void rightDiagonal() {
        System.out.println("WORKS!");
        stop();
        motionControl.turnDegrees(-45, 200);
        forward();

    }

    @Override
    public void leftTurn() {
        System.out.println("WORKS!");
        stop();
        motionControl.turnDegrees(90, 200);
        forward();

    }

    @Override
    public void stop() {
        System.out.println("WORKS!");
        motionControl.emergencyBrake();

    }

    @Override
    public void rightTurn() {
        System.out.println("WORKS!");
        stop();
        motionControl.turnDegrees(-90, 200);
        forward();

    }


    @Override
    public void leftBackDiagonal() {
        System.out.println("WORKS!");
        stop();
        motionControl.turnDegrees(135, 200);
        forward();

    }

    @Override
    public void reverse() {
        System.out.println("WORKS!");
        stop();
        motionControl.setSpeedForward(-200);
        forward();

    }

    @Override
    public void rightBackDiagonal() {
        System.out.println("WORKS!");
        stop();
        motionControl.turnDegrees(-135, 200);
        forward();

    }

    public void mute() {
        System.out.println("WORKS!");
        Speaker speaker = new Speaker(1, 1000, 20);
        speaker.mute();

    }

    public void switchOn() {
        System.out.println("WORKS!");
        motionControl.onButtonPress();

    }

    @Override
    public void infiniteRightTurn() {
        System.out.println("WORKS!");
    }

    @Override
    public void infiniteLeftTurn() {
        System.out.println("WORKS!");
    }

    @Override
    public void square() {
        System.out.println("WORKS!");


        for(int i = 0; i < 4; i++){
            stop();
            motionControl.turnDegrees(90, 200);
            forward();
        }

    }

    @Override
    public void triangle() {
        System.out.println("WORKS!");

        for(int i = 0; i < 3; i++){
            stop();
            motionControl.turnDegrees(60,200);
            forward();
        }

    }

}
