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
        if (!this.state.ifState("Idle")) {
            this.motionControl.update();
            this.lineDetection.update();
            switch (this.state.getState()) {
                case "GoForward":
                    if (this.motionControl.isIdle()) {
                        this.motionControl.setTargetSpeed(speed);
                        this.crossRoadsTimer = new Timer(1000);
                        this.state.setState("Running");
                    }
                    break;
                case "Stop":
                    this.motionControl.setTargetSpeed(0);
                    this.state.setState("Servicing");
                    break;
                case "TurnLeft":
                    if (this.motionControl.isIdle()) {
                        this.motionControl.infLeft();
                        this.timer = new Timer(350);
                        this.state.setState("Turning");
                    }
                    break;
                case "TurnRight":
                    if (this.motionControl.isIdle()) {
                        this.motionControl.infRight();
                        this.timer = new Timer(350);
                        this.state.setState("Turning");
                    }
                    break;
                case "End":
                    this.motionControl.setTargetSpeed(0);
                    this.state.setState("Finished");
                    break;
            }
        }
    }

    /**
     * Starts the route and changes to state to do so.
     */
    void start() {
        this.route.reset();
        this.state.setState("Running");
    }

    /**
     * Stops and cancels the route.
     */
    void stop() {
        this.motionControl.setTargetSpeed(0);
        this.state.setState("Finished");
    }

    /**
     * Resumes the route as long as it is not finished.
     */
    void resume() {
        if ((this.state.ifState("Idle") || this.state.ifState("Servicing")) && !this.state.ifState("Finished")) {
            this.state.setState("Running");
        }
    }

    /**
     * Pauses the route and makes sure it brakes immediately.
     */
    void pause() {
        this.motionControl.setTargetSpeed(0);
        this.state.setState("Servicing");
    }

    /**
     * Function which is called on detecting a crossroads, it then reads the next step and sets the state accordingly.
     */
    @Override
    public void onCrossroads() {
        if (this.state.ifState("Running") && (this.crossRoadsTimer == null || this.crossRoadsTimer.timeout())) {
            switch (this.route.nextStep()) {
                case "Left" :
                    this.state.setState("TurnLeft");
                    break;
                case "Right" :
                    this.state.setState("TurnRight");
                    break;
                case "Forward" :
                    this.state.setState("GoForward");
                    break;
                case "Stop" :
                    this.state.setState("Stop");
                    break;
                case "End" :
                    this.state.setState("End");
                    break;
            }
        }
    }

    /**
     * Function to do leftward line correction.
     */
    @Override
    public void lineCorrectionLeft() {
        if (this.state.ifState("Running")) {
            this.motionControl.updateWheels(0, 30);
        }
    }

    /**
     * Function to do rightward line correction.
     */
    @Override
    public void lineCorrectionRight() {
        if (this.state.ifState("Running")) {
            this.motionControl.updateWheels(30,0);
        }
    }

    /**
     * Function to brake whenever the line was lost for too much time (defined in line detection).
     */
    @Override
    public void onLineLost() {
        if (this.state.ifState("Running")) {
            this.motionControl.setTargetSpeed(0);
        }
    }

    /**
     * Function to both, go forward on the forward step in the route and to define whenever the turn has been completed.
     */
    @Override
    public void goForward() {
        if (this.state.ifState("Turning") && this.motionControl.isIdle() && (this.timer.timeout())) {
            this.motionControl.setTargetSpeed(0);
            this.crossRoadsTimer = new Timer(1000);
            this.state.setState("Running");
        } else if (this.state.ifState("Running")) {
            this.motionControl.setTargetSpeed(speed);
            this.state.setState("Running");
        }
    }
}
