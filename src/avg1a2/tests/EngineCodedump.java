package avg1a2.tests;

import TI.BoeBot;
import TI.Servo;
import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.button.Button;
import avg1a2.project.hardware.sensor.button.ButtonCallback;
import avg1a2.project.logic.State;
import avg1a2.project.modules.irconversion.IRConversionCallback;

public class EngineCodedump {




    /**
     * Class will have to be reworked to work with updatable in a infinite loop.
     * Not doing now, cause no time and I am tired.
     * - Faithfully Signed, Mick van der Werf, signing off for a while.
     */

        private Servo sLinks;
        private Servo sRecht;
        private int currentSpeed;
        private Timer timer;
        private State state;
        private String action;
        private boolean firstRun;

        public EngineCodedump(){
            this.sLinks = new Servo(13);
            this.sRecht = new Servo(12);
            this.currentSpeed = 0;
            this.timer = new Timer(100);
            state = new State("Executing","Idle");
        }

        public void update() {
            if (stateCheck()) {
                switch (action) {
                    case "accelerateToSpeed" :
                        //
                        break;
                    case "setSpeedForward" :
                        //
                        break;
                    case "emergencyBrake" :
                        //
                        break;
                    case "turnDegrees" :
                        //
                        break;
                }
            }
        }

        public boolean stateCheck() throws IllegalStateException, RuntimeException {
            if (state.getState().equals("Idle")) {
                return true;
            } else if (state.getState().equals("Executing")) {
                throw new RuntimeException("The controller is already performing a task");
            } else {
                throw new IllegalStateException("The controller has entered an illegal state");
            }
        }

        public void setAction(String action) {
            this.action = action;
        }


        public void onButtonPress() {
            //do nothing
        }



        public void accelerateToSpeed(int toSpeed){
            /**int toGo;
             int leftServo;
             int rightServo;

             if (firstRun) {
             toGo = toSpeed - this.currentSpeed;
             leftServo += this.currentSpeed;
             rightServo -= this.currentSpeed;
             this.timer.mark();
             firstRun = false;

             }

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

             }*/
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

            /**int prevSpeed = this.currentSpeed;
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

             setSpeedForward(prevSpeed);*/

        }
    }
