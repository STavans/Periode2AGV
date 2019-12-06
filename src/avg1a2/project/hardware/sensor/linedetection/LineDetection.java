package avg1a2.project.hardware.sensor.linedetection;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.Component;

public class LineDetection implements Component {
    private int threshhold;
    private int leftSensor;
    private int backRightSensor;
    private int midSensor;
    private int frontRightSensor;
    private LineDetectionCallback callback;
    private Timer timer;

    public LineDetection(int threshhold, int leftSensor, int backRightSensor, int midSensor, int frontRightSensor, LineDetectionCallback callback) {
        this.threshhold = threshhold;
        this.leftSensor = leftSensor;
        this.backRightSensor = backRightSensor;
        this.midSensor = midSensor;
        this.frontRightSensor = frontRightSensor;
        this.callback = callback;
    }

    @Override
    public void update() {
        if (timer == null || timer.timeout()) {
            System.out.println("Left sensor is: " + BoeBot.analogRead(leftSensor));
            System.out.println("Middle sensor is: " + BoeBot.analogRead(midSensor));
            System.out.println("Right sensor is: " + BoeBot.analogRead(frontRightSensor));
            System.out.println("Back right sensor is: " + BoeBot.analogRead(backRightSensor));
            timer = new Timer(50);
        }

        int dataLeftSensor = BoeBot.analogRead(leftSensor);
        int dataMidSensor = BoeBot.analogRead(midSensor);
        int dataFrontRightSensor = BoeBot.analogRead(frontRightSensor);
        int dataBackRightSensor = BoeBot.analogRead(backRightSensor);

        if (dataMidSensor > threshhold && dataBackRightSensor > threshhold) {
            callback.onCrossroads();
        }
        if (dataLeftSensor > threshhold && dataFrontRightSensor < threshhold) {
            callback.lineCorrectionLeft();
        }
        if (dataFrontRightSensor > threshhold && dataLeftSensor < threshhold) {
            callback.lineCorrectionRight();
        }
        if (dataMidSensor > threshhold) {
            callback.goForward();
        }
        if (dataMidSensor < threshhold && dataFrontRightSensor < threshhold && dataBackRightSensor < threshhold && dataLeftSensor < threshhold && midSensor < dataMidSensor) {
            callback.onLineLost();
        }
    }
}
