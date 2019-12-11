package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.linedetection.LineDetectionCallback;
import avg1a2.project.logic.State;
import avg1a2.project.modules.data.Route;

public class RouteControl implements LineDetectionCallback {
    private MotionControl motionControl;
    private Component lineDetection;
    private Route route;
    private State state;
    private int speed;

    public RouteControl(MotionControl motionControl) {
        this.motionControl = motionControl;
        this.speed = 20;
    }

    public void setLineDetection(Component lineDetection) {
        this.lineDetection = lineDetection;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setState(State state) {
        this.state = state;
        this.state.setState("Idle");
    }

    public void run() {
        motionControl.update();
        lineDetection.update();

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
                motionControl.setTargetSpeed(0);
                if (motionControl.isIdle()) {
                    motionControl.infLeft();
                    state.setState("Turning");
                }
                break;
            case "TurnRight":
                motionControl.setTargetSpeed(0);
                if (motionControl.isIdle()) {
                    motionControl.infRight();
                    state.setState("Turning");
                }
                break;
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
}
