package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.linedetection.LineDetectionCallback;
import avg1a2.project.logic.State;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.data.Route;

public class RouteControl implements LineDetectionCallback, CollisionDetectionCallback {
    private SignalControl signalControl;
    private MotionControl motionControl;
    private CollisionDetection collisionDetection;
    private Component lineDetection;
    private Route route;
    private State state;
    private State action;
    private int speed;

    public RouteControl(SignalControl signalControl, MotionControl motionControl) {
        this.signalControl = signalControl;
        this.motionControl = motionControl;
        this.speed = 20;
    }

    public void setCollisionDetection(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection;
    }

    public void setLineDetection(Component lineDetection) {
        this.lineDetection = lineDetection;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void run(){
        if (!state.ifState("Collision")) {
            motionControl.update();
            lineDetection.update();
            collisionDetection.update();

            switch (state.getState()) {
                case "GoForward":
                    if (motionControl.isIdle()) {
                        motionControl.setTargetSpeed(speed);
                        state.setState("Idle");
                    }
                    break;
                case "Stop":
                    motionControl.setTargetSpeed(0);
                    state.setState("Idle");
                    break;
                case "TurnLeft":
                    switch (action.getState()) {
                        case "Brake":
                            motionControl.setTargetSpeed(0);
                            action.setState("Turn");
                            break;
                        case "Turn":
                            if (motionControl.isIdle()) {
                                motionControl.infLeft();
                                action.setState("None");
                                state.setState("Turning");
                            }
                            break;
                    }
                    break;
                case "TurnRight":
                    switch (action.getState()) {
                        case "Brake":
                            motionControl.setTargetSpeed(0);
                            action.setState("Turn");
                            break;
                        case "Turn":
                            if (motionControl.isIdle()) {
                                motionControl.infRight();
                                action.setState("None");
                                state.setState("Turning");
                            }
                            break;
                    }
            }
        }
    }

    @Override
    public void onCrossroads() {
        switch (route.nextStep()) {
            case "Left" :
                state.setState("TurnLeft");
                break;
            case "Right" :
                state.setState("TurnRight");
                break;
            case "Forward" :
                state.setState("GoForward");
                break;
            case "Stop" :
                state.setState("Stop");
                break;
        }
    }

    @Override
    public void lineCorrectionLeft() {
        if (!state.ifState("Turning")) {
            motionControl.updateWheels(0, speed);
        }
    }

    @Override
    public void lineCorrectionRight() {
        if (!state.ifState("Turning")) {
            motionControl.updateWheels(speed,0);
        }
    }

    @Override
    public void onLineLost() {
        if (!state.ifState("Turning")) {
            motionControl.setTargetSpeed(0);
        }
    }

    @Override
    public void goForward() {
        if ((state.ifState("Idle") || state.ifState("Turning")) && motionControl.isIdle()) {
            motionControl.setTargetSpeed(speed);
            state.setState("Idle");
        }
    }

    @Override
    public void onFrontCollision() {
        motionControl.setTargetSpeed(0);
        state.setState("Collision");
    }

    @Override
    public void emergencyCollision() {
        motionControl.emergencyBrake();
        state.setState("Collision");
    }
}
