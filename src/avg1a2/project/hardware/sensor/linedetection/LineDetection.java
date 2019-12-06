package avg1a2.project.hardware.sensor.linedetection;

import TI.BoeBot;
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
        System.out.println("Front left sensor is: " +  BoeBot.analogRead(frontLeftSensor));
        System.out.println("Front right sensor is: " +  BoeBot.analogRead(frontRightSensor));
        System.out.println("Front back left sensor sensor is: " +  BoeBot.analogRead(backLeftSensor));
        System.out.println("Front back right sensor is: " +  BoeBot.analogRead(backRightSensor));

        if(frontLeftSensor > threshhold && frontRightSensor > threshhold && backLeftSensor > threshhold && backRightSensor > threshhold){
            callback.onCrossroads();
        }
        if(frontLeftSensor < threshhold){
            callback.lineCorrectionRight();
        }
        if(frontRightSensor < threshhold){
            callback.lineCorrectionLeft();
        }
        if(frontLeftSensor > threshhold && frontRightSensor > threshhold){
            callback.goForward();
        }
        if(frontLeftSensor < threshhold && frontRightSensor < threshhold && backLeftSensor < threshhold && backRightSensor < threshhold){
            callback.onLineLost();
        }
    }
}
