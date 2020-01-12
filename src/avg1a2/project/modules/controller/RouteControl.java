package avg1a2.project.modules.controller;

import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.linedetection.LineDetectionCallback;
import avg1a2.project.logic.State;
import avg1a2.project.modules.data.Route;

/**
 * Controls the active route for the BoeBot to use.
 */
public class RouteControl implements LineDetectionCallback {
    private MotionControl motionControl;
    private Component lineDetection;
    private Route route;
    private State state;
    private Timer timer;
    private Timer crossRoadsTimer;
    private int speed;

    /**
     * Constructor to set default speed and the MotionControl to use.
     * @param motionControl MotionControl to use.
     */
    public RouteControl(MotionControl motionControl) {
        this.motionControl = motionControl;
        this.speed = 60;
    }

    /**
     * Sets the lineDetection to use.
     * @param lineDetection LineDetection to use.
     */
    public void setLineDetection(Component lineDetection) {
        this.lineDetection = lineDetection;
    }

    /**
     * Sets the active route.
     * @param route The route to use.
     */
    public void setRoute(Route route) {
        this.route = route;
    }

    /**
     * Sets the State object (from the Init) To use in the controller.
     * @param state State object to use.
     */
    public void setState(State state) {
        this.state = state;
        this.state.setState("Idle");
    }

    /**
     * Runnable object which updates the components and runs specific tasks based on the current state/route step.
     */
    public void run() {
        if (!state.ifState("Idle")) {
            motionControl.update();
            lineDetection.update();
            switch (state.getState()) {
                case "GoForward":
                    if (motionControl.isIdle()) {
                        motionControl.setTargetSpeed(speed);
                        crossRoadsTimer = new Timer(1000);
                        state.setState("Running");
                    }
                    break;
                case "Stop":
                    motionControl.setTargetSpeed(0);
                    state.setState("Servicing");
                    break;
                case "TurnLeft":
                    if (motionControl.isIdle()) {
                        motionControl.infLeft();
                        timer = new Timer(350);
                        state.setState("Turning");
                    }
                    break;
                case "TurnRight":
                    if (motionControl.isIdle()) {
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

    /**
     * Starts the route and changes to state to do so.
     */
    void start() {
        route.reset();
        state.setState("Running");
    }

    /**
     * Stops and cancels the route.
     */
    void stop() {
        motionControl.setTargetSpeed(0);
        state.setState("Finished");
    }

    /**
     * Resumes the route as long as it is not finished.
     */
    void resume() {
        if ((state.ifState("Idle") || state.ifState("Servicing")) && !state.ifState("Finished")) {
            state.setState("Running");
        }
    }

    /**
     * Pauses the route and makes sure it brakes immediately.
     */
    void pause() {
        motionControl.setTargetSpeed(0);
        state.setState("Servicing");
    }

    /**
     * Function which is called on detecting a crossroads, it then reads the next step and sets the state accordingly.
     */
    @Override
    public void onCrossroads() {
        if (state.ifState("Running") && (crossRoadsTimer == null || crossRoadsTimer.timeout())) {
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

    /**
     * Function to do leftward line correction.
     */
    @Override
    public void lineCorrectionLeft() {
        if (state.ifState("Running")) {
            motionControl.updateWheels(0, 30);
        }
    }

    /**
     * Function to do rightward line correction.
     */
    @Override
    public void lineCorrectionRight() {
        if (state.ifState("Running")) {
            motionControl.updateWheels(30,0);
        }
    }

    /**
     * Function to brake whenever the line was lost for too much time (defined in line detection).
     */
    @Override
    public void onLineLost() {
        if (state.ifState("Running")) {
            motionControl.setTargetSpeed(0);
        }
    }

    /**
     * Function to both, go forward on the forward step in the route and to define whenever the turn has been completed.
     */
    @Override
    public void goForward() {
        if (state.ifState("Turning") && motionControl.isIdle() && (timer.timeout())) {
            motionControl.setTargetSpeed(0);
            crossRoadsTimer = new Timer(1000);
            state.setState("Running");
        } else if (state.ifState("Running")) {
            motionControl.setTargetSpeed(speed);
            state.setState("Running");
        }
    }
}
