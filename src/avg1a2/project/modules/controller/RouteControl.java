package avg1a2.project.modules.controller;

import avg1a2.project.hardware.sensor.linedetection.LineDetectionCallback;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.collisiondetection.CollisionDetectionCallback;
import avg1a2.project.modules.data.Route;

public class RouteControl implements LineDetectionCallback, CollisionDetectionCallback {
    private SignalControl signalControl;
    private MotionControl motionControl;
    private CollisionDetection collisionDetection;
    private Route route;

    public RouteControl() {

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
}
