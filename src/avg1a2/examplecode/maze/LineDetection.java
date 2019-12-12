package avg1a2.examplecode.maze;

import TI.Timer;

public class LineDetection {
    private LightSensor left;
    private LightSensor middle;
    private LightSensor right;
    private LightSensor back;
    private LineDetectionCallback callback;
    private Timer timer;

    public LineDetection(LightSensor left, LightSensor middle, LightSensor right, LightSensor back, LineDetectionCallback callback) {
        this.left = left;
        this.middle = middle;
        this.right = right;
        this.back = back;
        this.callback = callback;
        timer = new Timer(50);
    }

    public void update() {
//        System.out.println(left.read());
//        System.out.println(middle.read());
//        System.out.println(right.read());
//        System.out.println(back.read());

        int dataMidSensor = middle.read();
        int dataFrontRightSensor = right.read();
        int dataLeftSensor = left.read();
        int dataBackRightSensor = back.read();



        if (dataMidSensor > 900 && dataBackRightSensor > 900) {
            timer = new Timer(200);
            callback.onCrossroads();
        } else if (dataMidSensor > 900) {
            timer = new Timer(200);
            callback.noDetection();
        }

        if (dataLeftSensor > 900 && dataFrontRightSensor < 900) {
            timer = new Timer(200);
            callback.lineCorrectionLeft();
        } else if (dataFrontRightSensor > 900 && dataLeftSensor < 900) {
            timer = new Timer(200);
            callback.lineCorrectionRight();
        }

        if (timer.timeout()) {
            callback.stop();
        }
    }
}
