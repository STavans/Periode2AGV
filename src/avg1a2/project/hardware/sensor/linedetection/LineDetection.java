package avg1a2.project.hardware.sensor.linedetection;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.Component;

public class LineDetection implements Component {
    private int threshold;
    private int leftSensor;
    private int backRightSensor;
    private int midSensor;
    private int frontRightSensor;
    private LineDetectionCallback callback;
    private Timer timer;

    public LineDetection(int threshold, int leftSensor, int backRightSensor, int midSensor, int frontRightSensor, LineDetectionCallback callback) {
        this.threshold = threshold;
        this.leftSensor = leftSensor;
        this.backRightSensor = backRightSensor;
        this.midSensor = midSensor;
        this.frontRightSensor = frontRightSensor;
        this.callback = callback;
        timer = new Timer(500);
    }

    @Override
    public void update() {

        int dataLeftSensor = BoeBot.analogRead(leftSensor);
        int dataMidSensor = BoeBot.analogRead(midSensor);
        int dataFrontRightSensor = BoeBot.analogRead(frontRightSensor);
        int dataBackRightSensor = BoeBot.analogRead(backRightSensor);

        if (dataMidSensor > threshold && dataBackRightSensor > threshold) {
            callback.onCrossroads();
            timer = new Timer(500);
        }
        if (dataMidSensor > threshold) {
            callback.goForward();
            timer = new Timer(500);
        }
        if (dataLeftSensor > threshold && dataFrontRightSensor < threshold) {
            callback.lineCorrectionLeft();
            timer = new Timer(500);
        }
        if (dataFrontRightSensor > threshold && dataLeftSensor < threshold) {
            callback.lineCorrectionRight();
            timer = new Timer(500);
        }
        if (dataFrontRightSensor < threshold && dataLeftSensor < threshold && dataMidSensor < threshold) {
            if (timer.timeout()) {
                callback.onLineLost();
            }
        }


        /**
        if (dataFrontRightSensor < threshold && dataLeftSensor < threshold && dataMidSensor < threshold) {
            callback.onLineLost();
        } else if (dataMidSensor > threshold && dataBackRightSensor > threshold) {
            callback.onCrossroads();
        } else if (dataLeftSensor > threshold && dataFrontRightSensor < threshold) {
            callback.lineCorrectionLeft();
        } else if (dataFrontRightSensor > threshold && dataLeftSensor < threshold) {
            callback.lineCorrectionRight();
        } else if (dataMidSensor > threshold) {
            callback.goForward();
        }*/






    }
}
