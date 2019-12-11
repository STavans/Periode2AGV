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

    public LineDetection(int threshhold, int leftSensor, int backRightSensor, int midSensor, int frontRightSensor, LineDetectionCallback callback) {
        this.threshold = threshhold;
        this.leftSensor = leftSensor;
        this.backRightSensor = backRightSensor;
        this.midSensor = midSensor;
        this.frontRightSensor = frontRightSensor;
        this.callback = callback;
    }

    @Override
    public void update() {
            System.out.println("Left sensor is: " + BoeBot.analogRead(leftSensor));
            System.out.println("Middle sensor is: " + BoeBot.analogRead(midSensor));
            System.out.println("Right sensor is: " + BoeBot.analogRead(frontRightSensor));
            System.out.println("Back right sensor is: " + BoeBot.analogRead(backRightSensor));

        int dataLeftSensor = BoeBot.analogRead(leftSensor);
        int dataMidSensor = BoeBot.analogRead(midSensor);
        int dataFrontRightSensor = BoeBot.analogRead(frontRightSensor);
        int dataBackRightSensor = BoeBot.analogRead(backRightSensor);

        if (dataMidSensor > threshold) {
            callback.goForward();
        }
        if (dataLeftSensor > threshold && dataFrontRightSensor < threshold) {
            callback.lineCorrectionLeft();
        }
        if (dataFrontRightSensor > threshold && dataLeftSensor < threshold) {
            callback.lineCorrectionRight();
        }
        if (dataFrontRightSensor < threshold && dataLeftSensor < threshold && dataMidSensor < threshold) {
            callback.onLineLost();
        }
        if (dataMidSensor > threshold && dataBackRightSensor > threshold) {
            callback.onCrossroads();
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
