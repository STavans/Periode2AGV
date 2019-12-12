package avg1a2.project.modules.controller;

import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.linedetection.LineDetectionCallback;
import avg1a2.project.logic.State;
import avg1a2.project.modules.data.Route;

public class RouteControl implements LineDetectionCallback {
    private MotionControl motionControl;
    private Component lineDetection;
    private Route route;
    private State state;
    private Timer timer;
    private Timer crossRoadsTimer;
    private SignalControl signalControl;
    private int speed;

    public RouteControl(MotionControl motionControl, SignalControl signalControl) {
        this.motionControl = motionControl;
        this.signalControl = signalControl;
        this.speed = 40;
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
        signalControl.followRoute();

        switch (state.getState()) {
            case "GoForward":
                if (motionControl.isIdle()) {
                    motionControl.setTargetSpeed(speed);
                    crossRoadsTimer = new Timer(1000);
                    state.setState("Idle");
                }
                break;
            case "Stop":
                motionControl.setTargetSpeed(0);
                state.setState("Finished");
                break;
            case "TurnLeft":
                if (motionControl.isIdle()) {
                    motionControl.infLeft();
                    timer = new Timer(300);
                    state.setState("Turning");
                }
                break;
            case "TurnRight":
                if (motionControl.isIdle()) {
                    motionControl.infRight();
                    timer = new Timer(300);
                    state.setState("Turning");
                }
                break;
        }
    }

    @Override
    public void onCrossroads() {
        if (state.ifState("Idle") && (crossRoadsTimer == null || crossRoadsTimer.timeout())) {
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
    }

    @Override
    public void lineCorrectionLeft() {
        if (state.ifState("Idle")) {
            motionControl.updateWheels(0, speed);
        }
    }

    @Override
    public void lineCorrectionRight() {
        if (state.ifState("Idle")) {
            motionControl.updateWheels(speed,0);
        }
    }

    @Override
    public void onLineLost() {
        if (state.ifState("Idle")) {
            motionControl.setTargetSpeed(0);
        }
    }

    @Override
    public void goForward() {
        if (state.ifState("Turning") && motionControl.isIdle() && (timer.timeout())) {
            motionControl.setTargetSpeed(0);
            crossRoadsTimer = new Timer(1000);
            state.setState("Idle");
        } else if (state.ifState("Idle")) {
            motionControl.setTargetSpeed(speed);
            state.setState("Idle");
        }
    }

    void stop() {
        motionControl.setTargetSpeed(0);
    }
}
