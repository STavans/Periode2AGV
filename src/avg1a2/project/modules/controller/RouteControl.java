package avg1a2.project.modules.controller;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.linedetection.LineDetection;
import avg1a2.project.hardware.sensor.linedetection.LineDetectionCallback;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.data.Route;

public class RouteControl implements LineDetectionCallback, CollisionDetectionCallback {
    private SignalControl signalControl;
    private MotionControl motionControl;
    private CollisionDetection collisionDetection;
    private Component lineDetection;
    private Route route;

    public RouteControl(SignalControl signalControl, MotionControl motionControl) {
        this.signalControl = signalControl;
        this.motionControl = motionControl;
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

    @Override
    public void onCrossroads() {

    }

    @Override
    public void lineCorrectionLeft() {

    }

    @Override
    public void lineCorrectionRight() {

    }

    @Override
    public void onLineLost() {

    }

    @Override
    public void onFrontCollision() {

    }

    @Override
    public void emergencyCollision() {

    }
}
