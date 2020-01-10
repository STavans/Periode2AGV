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
    private int speed;

    public RouteControl(MotionControl motionControl) {
        this.motionControl = motionControl;
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
        if (!state.ifState("Idle")) {
            motionControl.update();
            lineDetection.update();
            switch (state.getState()) {
                case "GoForward":
                    if (motionControl.isIdle()) {
                        System.out.println("Going forward!");
                        motionControl.setTargetSpeed(speed);
                        crossRoadsTimer = new Timer(1000);
                        System.out.println("na dat de nieuwe timer gezet is");
                        state.setState("Running");
                    }
                    break;
                case "Stop":
                    motionControl.setTargetSpeed(0);
                    state.setState("Servicing");
                    break;
                case "TurnLeft":
                    if (motionControl.isIdle()) {
                        System.out.println("Starting to turn left");
                        motionControl.infLeft();
                        timer = new Timer(350);
                        state.setState("Turning");
                    }
                    break;
                case "TurnRight":
                    if (motionControl.isIdle()) {
                        System.out.println("Starting to turn right");
                        motionControl.infRight();
                        timer = new Timer(350);
                        state.setState("Turning");
                    }
                    break;
                case "End":
                    motionControl.setTargetSpeed(0);
                    state.setState("Finished");
                    break;
            }
        }
    }

    void start() {
        route.reset();
        state.setState("Running");
    }

    void stop() {
        motionControl.setTargetSpeed(0);
        state.setState("Finished");
    }

    void resume() {
        if (state.ifState("Idle") && !state.ifState("Finished")) {
            state.setState("Running");
        }
    }

    void pause() {
        if (state.ifState("Running")) {
            motionControl.setTargetSpeed(0);
            state.setState("Idle");
        }
    }

    @Override
    public void onCrossroads() {
        if (state.ifState("Running") && (crossRoadsTimer == null || crossRoadsTimer.timeout())) {
            System.out.println("Crossroad detected");
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
                case "End" :
                    state.setState("End");
                    break;
            }
        }
    }

    @Override
    public void lineCorrectionLeft() {
        if (state.ifState("Running")) {
            System.out.println("Correcting left");
            motionControl.updateWheels(0, 30);
        }
    }

    @Override
    public void lineCorrectionRight() {
        if (state.ifState("Running")) {
            System.out.println("Correcting right");
            motionControl.updateWheels(30,0);
        }
    }

    @Override
    public void onLineLost() {
        if (state.ifState("Running") && !state.ifState("Turning")) {
            motionControl.setTargetSpeed(0);
        }
    }

    @Override
    public void goForward() {
        if (state.ifState("Turning") && motionControl.isIdle() && (timer.timeout())) {
            motionControl.setTargetSpeed(0);
            crossRoadsTimer = new Timer(1000);
            state.setState("Running");
        } else if (state.ifState("Running")) {
            System.out.println("Forward");
            motionControl.setTargetSpeed(speed);
            state.setState("Running");
        }
    }
}
