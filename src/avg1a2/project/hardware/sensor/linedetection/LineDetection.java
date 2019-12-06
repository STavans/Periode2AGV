package avg1a2.project.hardware.sensor.linedetection;

import avg1a2.project.hardware.Component;

public class LineDetection implements Component {
    private int threshhold;
    private int backLeftSensor;
    private int frontLeftSensor;
    private int frontRightSensor;
    private int backRightSensor;
    private LineDetectionCallback callback;

    public LineDetection(int threshhold, int backLeftSensor, int frontLeftSensor, int frontRightSensor, int backRightSensor, LineDetectionCallback callback) {
        this.threshhold = threshhold;
        this.backLeftSensor = backLeftSensor;
        this.frontLeftSensor = frontLeftSensor;
        this.frontRightSensor = frontRightSensor;
        this.backRightSensor = backRightSensor;
        this.callback = callback;
    }

    @Override
    public void update() {

    }
}
