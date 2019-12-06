package avg1a2.project.hardware.sensor.linedetection;

import TI.BoeBot;
import TI.Timer;
import avg1a2.project.hardware.Component;

public class LineDetection implements Component {
    private int threshhold;
    private int backLeftSensor;
    private int frontLeftSensor;
    private int frontRightSensor;
    private int backRightSensor;
    private LineDetectionCallback callback;
    private Timer timer;

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
        if (timer == null || timer.timeout()) {
            System.out.println("Front left sensor is: " +  BoeBot.analogRead(frontLeftSensor));
            System.out.println("Front right sensor is: " +  BoeBot.analogRead(frontRightSensor));
//            System.out.println("Front back left sensor sensor is: " +  BoeBot.analogRead(backLeftSensor));
//            System.out.println("Front back right sensor is: " +  BoeBot.analogRead(backRightSensor));

            int dataFrontLeft = BoeBot.analogRead(frontLeftSensor);
            int dataFrontRight = BoeBot.analogRead(frontRightSensor);
            int dataBackLeft = BoeBot.analogRead(backLeftSensor);
            int dataBackRight = BoeBot.analogRead(backRightSensor);




            if(dataFrontLeft > threshhold && dataFrontRight > threshhold && dataBackLeft > threshhold && dataBackRight > threshhold){
                callback.onCrossroads();
            }
            if(dataFrontLeft < threshhold){
                callback.lineCorrectionRight();
            }
            if(dataFrontRight < threshhold){
                callback.lineCorrectionLeft();
            }
            if(dataFrontLeft > threshhold && dataFrontRight > threshhold){
                callback.goForward();
            }
            if(dataFrontLeft < threshhold && dataFrontRight < threshhold && dataBackLeft < threshhold && dataBackRight < threshhold){
                callback.onLineLost();
            }
            timer = new Timer(2000);
        }
    }
}
