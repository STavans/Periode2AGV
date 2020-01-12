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
    private LineDetectionCallback callback;
    private Timer timer;

    /**
     * Constructor sets the Attributes.
     * @param leftSensor The LightSensor pin and the front left of the BoeBot.
     * @param midSensor The LightSensor pin and the front middle of the BoeBot.
     * @param frontRightSensor The LightSensor pin and the front right of the BoeBot.
     * @param backRightSensor The LightSensor pin and the back right of the BoeBot.
     * @param threshold The value which will separate black from white in order to detect lines.
     * @param delay The delay which the LineSensor should use to detect line lost.
     * @param callback The callback which to signal if an event is detected.
     */
    public LineDetection(int leftSensor, int midSensor, int frontRightSensor, int backRightSensor, int threshold,  int delay, LineDetectionCallback callback) {
        this.threshold = threshold;
        this.leftSensor = leftSensor;
        this.backRightSensor = backRightSensor;
        this.midSensor = midSensor;
        this.frontRightSensor = frontRightSensor;
        this.callback = callback;
        this.timer = new Timer(delay);
    }

    /**
     * Updatable to be continuously run. Will also immediately check the situation and call the proper callbacks.
     */
    @Override
    public void update() {
        int dataLeftSensor = BoeBot.analogRead(this.leftSensor);
        int dataMidSensor = BoeBot.analogRead(this.midSensor);
        int dataFrontRightSensor = BoeBot.analogRead(this.frontRightSensor);
        int dataBackRightSensor = BoeBot.analogRead(this.backRightSensor);

//        System.out.println("Line sensors: " + dataLeftSensor + "\t" + dataMidSensor + "\t" + dataFrontRightSensor + "\t" + dataBackRightSensor);

        if (dataMidSensor > this.threshold && dataBackRightSensor > this.threshold) {
            this.callback.onCrossroads();
            this.timer.mark();
        }
        if (dataMidSensor > this.threshold) {
            this.callback.goForward();
            this.timer.mark();
        }
        if (dataLeftSensor > this.threshold && dataFrontRightSensor < this.threshold) {
            this.callback.lineCorrectionLeft();
            this.timer.mark();
        }
        if (dataFrontRightSensor > this.threshold && dataLeftSensor < this.threshold) {
            this.callback.lineCorrectionRight();
            this.timer.mark();
        }
        if (dataFrontRightSensor < this.threshold && dataLeftSensor < this.threshold && dataMidSensor < this.threshold) {
            if (this.timer.timeout()) { //Timer to make sure the BoeBot has a chance to correct himself slightly.
                this.callback.onLineLost();
            }
            //else {
            //    callback.goForward();
            //}
//            */ Gives problems with correction after right turn*/
        }
    }
}
