package avg1a2.project.hardware.sensor.linedetection;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.Component;

/**
 * Composition of several light sensitive sensors, which will work together to detect a line and crossroads.
 */
public class LineDetection implements Component {
    private int threshold;
    private int leftSensor;
    private int backRightSensor;
    private int midSensor;
    private int frontRightSensor;
    private int delay;
    private LineDetectionCallback callback;
    private Timer timer;

    /**
     * Constructor sets the Attributes.
     * @param threshold
     * @param leftSensor
     * @param backRightSensor
     * @param midSensor
     * @param frontRightSensor
     * @param callback
     * @param delay
     */
    public LineDetection(int threshold, int leftSensor, int backRightSensor, int midSensor, int frontRightSensor, LineDetectionCallback callback, int delay) {
        this.threshold = threshold;
        this.leftSensor = leftSensor;
        this.backRightSensor = backRightSensor;
        this.midSensor = midSensor;
        this.frontRightSensor = frontRightSensor;
        this.callback = callback;
        this.delay = delay;
        this.timer = new Timer(this.delay);
    }

    @Override
    public void update() {
        int dataLeftSensor = BoeBot.analogRead(leftSensor);
        int dataMidSensor = BoeBot.analogRead(midSensor);
        int dataFrontRightSensor = BoeBot.analogRead(frontRightSensor);
        int dataBackRightSensor = BoeBot.analogRead(backRightSensor);

        if (dataMidSensor > threshold && dataBackRightSensor > threshold) {
            callback.onCrossroads();
            timer = new Timer(delay);
        }
        if (dataMidSensor > threshold) {
            callback.goForward();
            timer = new Timer(delay);
        }
        if (dataLeftSensor > threshold && dataFrontRightSensor < threshold) {
            callback.lineCorrectionLeft();
            timer = new Timer(delay);
        }
        if (dataFrontRightSensor > threshold && dataLeftSensor < threshold) {
            callback.lineCorrectionRight();
            timer = new Timer(delay);
        }
        if (dataFrontRightSensor < threshold && dataLeftSensor < threshold && dataMidSensor < threshold) {
            if (timer.timeout()) {
                callback.onLineLost();
            } else {
                callback.lineCorrectionLeft();
            }
        }
    }
}
