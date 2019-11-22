package avg1a2.project.modules.controller;

import TI.BoeBot;
import TI.Servo;
import TI.Timer;

public class MotionControl {

    private Servo sLinks;
    private Servo sRecht;
    private int currentSpeed;
    private Timer timer;

    public MotionControl(){

        this.sLinks = new Servo(12);
        this.sRecht = new Servo(13);
        this.currentSpeed = 0;
        this.timer = new Timer(100);
    }


    public void accelerateToSpeed(int toSpeed){

        int toGo = toSpeed - this.currentSpeed;
        int leftServo = 1500 + this.currentSpeed;
        int rightServo = 1500 - this.currentSpeed;
        this.timer.mark();
        if(toGo > 0){

            int i = 0;
            while(i < toGo){

                if(this.timer.timeout()){

                this.sLinks.update(leftServo + i);
                this.sRecht.update(rightServo - i);

                i++;
                }


            }
            this.currentSpeed = this.currentSpeed + i;

        }else if(toGo < 0){

            int i = 0;
            while(i > toGo){

                if(this.timer.timeout()){

                    this.sLinks.update(leftServo + i);
                    this.sRecht.update(rightServo - i);

                    i--;
                }


            }
            this.currentSpeed = this.currentSpeed - i;

        }
    }


    public void setSpeedForward(int speed){

        this.sLinks.update(1500 + speed);
        this.sRecht.update(1500 - speed);
    }


    public void emergencyBrake(){

        this.sLinks.update(1500);
        this.sRecht.update(1500);
        this.currentSpeed = 0;
    }



        public void turnDegrees(int degrees, int turnSpeed){

            int prevSpeed = this.currentSpeed;
            double turnTime = ((double)degrees / turnSpeed) * 425; //multiplying by 427, after experimentation seemed to give an accurate time in milliseconds to turn.
            this.timer.mark();

            setSpeedForward(0);
            if(degrees > 0) {
                this.sLinks.update(1500 + turnSpeed);
                this.sRecht.update(1500 + turnSpeed);

                if(this.timer.timeout()){
                this.sLinks.update(1500);
                this.sRecht.update(1500);

                }
            }
            else{
                turnTime = turnTime * -1;
                this.sLinks.update(1500 - turnSpeed);
                this.sRecht.update(1500 - turnSpeed);
                if(this.timer.timeout()) {
                    this.sLinks.update(1500);
                    this.sRecht.update(1500);
                }

            }

            setSpeedForward(prevSpeed);

        }


}
