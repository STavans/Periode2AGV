package avg1a2.project.hardware.engine;


import TI.Servo;
import avg1a2.project.hardware.Component;

public class Wheels implements Component {

    private Servo left;
    private Servo right;
    private int speedLeft;
    private int speedRight;

    public Wheels(int pinLeftServo, int pinRightServo){

        this.left = new Servo(pinLeftServo);
        this.right = new Servo(pinRightServo);
        this.speedLeft = 0;
        this.speedRight = 0;
    }


    public void setLeftSpeed(int leftSpeed) {

        this.left.update(1500 + leftSpeed);
        this.speedLeft = leftSpeed;
    }

    public void setRightSpeed(int rightSpeed) {

        this.right.update(1500 + rightSpeed);
        this.speedRight = rightSpeed;
    }

    public int getLeftSpeed(){
        return this.speedLeft;
    }

    public int getRightSpeed(){
        return this.speedRight;
    }

    public void update() {

    }

}
