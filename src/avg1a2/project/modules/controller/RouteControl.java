package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.linedetection.LineDetection;
import avg1a2.project.hardware.sensor.linedetection.LineDetectionCallback;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.data.Route;

public class RouteControl implements LineDetectionCallback{
    private SignalControl signalControl;
    private MotionControl motionControl;
    private CollisionDetection collisionDetection;
    private Component lineDetection;
    private Route route;

    public RouteControl(SignalControl signalControl, MotionControl motionControl) {
        this.signalControl = signalControl;
        this.motionControl = motionControl;
    }

    public void runRoute(){



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
        signalControl.followRoute();
        motionControl.update();
        lineDetection.update();
        collisionDetection.update();
        motionControl.onFrontCollision();
        motionControl.emergencyCollision();
    }

    @Override
    public void onCrossroads() {
        motionControl.emergencyBrake();
    }

    @Override
    public void lineCorrectionLeft() {
        motionControl.updateWheels(0, 50);
    }

    @Override
    public void lineCorrectionRight() {
        motionControl.updateWheels(50,0);
    }

    @Override
    public void onLineLost() {
        motionControl.emergencyBrake();
    }

    @Override
    public void goForward() {
        motionControl.setTargetSpeed(30);
    }


}
