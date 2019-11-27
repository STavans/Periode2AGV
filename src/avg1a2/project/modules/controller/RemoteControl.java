package avg1a2.project.modules.controller;

import TI.Timer;
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
    private LedGroup group;
    private boolean collision;
    private Timer timer;

    public RemoteControl(MotionControl motionControl) {
        this.motionControl = motionControl;
        LedGroup group = new LedGroup();
        group.addLed("1",new NeoPixel(0,50,255,0,0));
        group.addLed("2",new NeoPixel(1,50,255,0,0));
        group.addLed("3",new NeoPixel(2,50,255,0,0));
        group.addLed("4",new NeoPixel(3,50,255,0,0));
        group.addLed("5",new NeoPixel(4,50,255,0,0));
        group.addLed("6",new NeoPixel(5,50,255,0,0));
        this.group = group;
        collision = false;
    }

    public void run(DataStore dataStore) {
       dataStore.getCollisionDetection().update();
       dataStore.getIrConversion().update();
       motionControl.update();
       resume();
    }

    public void resume() {
        if (timer != null && timer.timeout()) {
            group.off();
            collision = false;
        }
    }

    public void onFrontCollision() {
        group.on();
        motionControl.setState("Idle");
        motionControl.setAction("");
        stop();
        collision = true;
        timer = new Timer(500);
    }

    @Override
    public void leftDiagonal() {
        if (motionControl.stateCheck() && !collision) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-45,200);
            //forward();
        }
    }

    @Override
    public void forward() {
        if (motionControl.stateCheck() && !collision) {
            motionControl.setState("Executing");
            motionControl.setSpeedForward(200);
        }
    }

    @Override
    public void rightDiagonal() {
        if (motionControl.stateCheck() && !collision) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(45,200);
            //forward();
        }
    }

    @Override
    public void leftTurn() {
        if (motionControl.stateCheck() && !collision){
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-90,200);
            //forward();
        }
    }

    @Override
    public void stop() {

        if (motionControl.stateCheck() && !collision) {
            motionControl.setState("Executing");
            motionControl.emergencyBrake();
        }
    }

    @Override
    public void rightTurn() {
        if (motionControl.stateCheck() && !collision) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(90,200);
            //forward();
        }
    }

    @Override
    public void leftBackDiagonal() {
        if (motionControl.stateCheck() && !collision) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-135,200);
            //forward();
        }
    }

    @Override
    public void reverse() {
        if (motionControl.stateCheck() && !collision) {
            stop();
            motionControl.setState("Executing");
            motionControl.setSpeedForward(-200);
            //forward();
        }
    }

    @Override
    public void rightBackDiagonal() {
        if (motionControl.stateCheck() && !collision) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(135,200);
            //forward();
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
        if (motionControl.stateCheck() && !collision) {
            motionControl.setState("Executing");
            //
        }
    }

    @Override
    public void infiniteLeftTurn() {
        if (motionControl.stateCheck() && !collision) {
            motionControl.setState("Executing");
            //
        }
    }

    @Override
    public void square() {
        if (motionControl.stateCheck() && !collision) {
            motionControl.setState("Executing");
            for (int i = 0; i < 4; i++) {
                stop();
                motionControl.setTurnDegrees(90,200);
                forward();
            }
        }
    }

    @Override
    public void triangle() {
        if (motionControl.stateCheck() && !collision) {
            motionControl.setState("Executing");
            for (int i = 0; i < 3; i++) {
                stop();
                motionControl.setTurnDegrees(60,200);
                forward();
            }
        }
    }
}
