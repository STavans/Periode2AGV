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
    private CollisionDetection collisionDetection;

    public RemoteControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    public void setCollisionDetection(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection;
    }

    public void run(DataStore dataStore) {
        if (collisionDetection != null) {
            collisionDetection.update();
            dataStore.getIrConversion().update();
            motionControl.update();
        }
    }

    public void onFrontCollision() {
        motionControl.setState("Idle");
        motionControl.setAction("");
        stop();
    }

    @Override
    public void leftDiagonal() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-45,200);
            //forward();
        }
    }

    @Override
    public void forward() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            motionControl.setSpeedForward(200);
        }
    }

    @Override
    public void rightDiagonal() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(45,200);
            //forward();
        }
    }

    @Override
    public void leftTurn() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()){
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-90,200);
            //forward();
        }
    }

    @Override
    public void stop() {

        if (motionControl.stateCheck()) {
            motionControl.setState("Executing");
            motionControl.emergencyBrake();
        }
    }

    @Override
    public void rightTurn() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(90,200);
            //forward();
        }
    }

    @Override
    public void leftBackDiagonal() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            stop();
            motionControl.setState("Executing");
            motionControl.setTurnDegrees(-135,200);
            //forward();
        }
    }

    @Override
    public void reverse() {
        if (motionControl.stateCheck()) {
            motionControl.setState("Executing");
            motionControl.setSpeedForward(-200);
            //forward();
        }
    }

    @Override
    public void rightBackDiagonal() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
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
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            //
        }
    }

    @Override
    public void infiniteLeftTurn() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            //
        }
    }

    @Override
    public void square() {
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
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
        if (motionControl.stateCheck() && !collisionDetection.isCollision()) {
            motionControl.setState("Executing");
            for (int i = 0; i < 3; i++) {
                stop();
                motionControl.setTurnDegrees(60,200);
                forward();
            }
        }
    }
}
