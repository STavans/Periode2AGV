package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.signal.Speaker;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.data.DataStore;
import avg1a2.project.modules.irconversion.IRConversionCallback;


public class RemoteControl implements CollisionDetectionCallback, IRConversionCallback {

    private MotionControl motionControl;
    public RemoteControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    public void run(DataStore dataStore) {
       dataStore.getCollisionDetection().update();
       dataStore.getIrConversion().update();
    }

    public void onFrontCollision() {
        stop();

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
        if (motionControl.stateCheck()) {
            stop();
            motionControl.turnDegrees(-45,200);
            forward();
        }
    }

    @Override
    public void forward() {
        if (motionControl.stateCheck()) {
            motionControl.accelerateToSpeed(200);
        }
    }

    @Override
    public void rightDiagonal() {
        if (motionControl.stateCheck()) {
            stop();
            motionControl.turnDegrees(45,200);
            forward();
        }
    }

    @Override
    public void leftTurn() {
        if (motionControl.stateCheck()) {
            stop();
            motionControl.turnDegrees(-90,200);
            forward();
        }
    }

    @Override
    public void stop() {
        if (motionControl.stateCheck()) {
            motionControl.emergencyBrake();
        }
    }

    @Override
    public void rightTurn() {
        if (motionControl.stateCheck()) {
            stop();
            motionControl.turnDegrees(90,200);
            forward();
        }
    }

    @Override
    public void leftBackDiagonal() {
        if (motionControl.stateCheck()) {
            stop();
            motionControl.turnDegrees(-135,200);
            forward();
        }
    }

    @Override
    public void reverse() {
        if (motionControl.stateCheck()) {
            stop();
            motionControl.setSpeedForward(200);
            forward();
        }
    }

    @Override
    public void rightBackDiagonal() {
        if (motionControl.stateCheck()) {
            stop();
            motionControl.turnDegrees(135,200);
            forward();
        }
    }

    @Override
    public void mute() {

    }

    @Override
    public void switchOn() {

    }

    @Override
    public void infiniteRightTurn() {
        if (motionControl.stateCheck()) {
            //
        }
    }

    @Override
    public void infiniteLeftTurn() {
        if (motionControl.stateCheck()) {
            //
        }
    }

    @Override
    public void square() {
        if (motionControl.stateCheck()) {
            for (int i = 0; i < 4; i++) {
                stop();
                motionControl.turnDegrees(90,200);
                forward();
            }
        }
    }

    @Override
    public void triangle() {
        if (motionControl.stateCheck()) {
            for (int i = 0; i < 3; i++) {
                stop();
                motionControl.turnDegrees(60,200);
                forward();
            }
        }
    }

}
