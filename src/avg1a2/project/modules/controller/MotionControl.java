package avg1a2.project.modules.controller;

import TI.BoeBot;
import TI.Servo;
import TI.Timer;
import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.sensor.button.Button;
import avg1a2.project.hardware.sensor.button.ButtonCallback;
import avg1a2.project.logic.State;
import avg1a2.project.modules.irconversion.IRConversionCallback;

/**
 * Class will have to be reworked to work with updatable in a infinite loop.
 * Not doing now, cause no time and I am tired.
 * - Faithfully Signed, Mick van der Werf, signing off for a while.
 */
public class MotionControl implements ButtonCallback {

    private Servo sLinks;
    private Servo sRecht;
    private Timer timer;
    private State state;
    private String action;
    private int turnDegrees;
    private int turnSpeed;
    private int turnTime;

    public MotionControl(){
        this.sLinks = new Servo(12);
        this.sRecht = new Servo(13);
        this.timer = new Timer(100);
        state = new State("Executing","Idle");
    }

    public void update() {
        if (!stateCheck()) {
            switch (action) {
                case "turnDegrees" :
                    turnDegrees();
                    break;
                case "none" :
                    break;
            }
        }
    }

    public boolean stateCheck() {
        if (state.getState().equals("Idle")) {
            return true;
        }
        else return false;
    }

    public void setState(String state) {
        this.state.setState(state);
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public void onButtonPress() {
        //do nothing
    }



    public void accelerateToSpeed(int targetSpeed){
        //
    }


    public void setSpeedForward(int speed){

        this.sLinks.update(1500 + speed);
        this.sRecht.update(1500 - speed);
        setState("Idle");
    }


    public void emergencyBrake(){

        this.sLinks.update(1500);
        this.sRecht.update(1500);
        setState("Idle");
    }



    public void turnDegrees(){
        if (timer != null && timer.timeout()) {
            sLinks.update(1500);
            sRecht.update(1500);
            setTurnDegrees(0,0);
            setAction("none");
            setState("Idle");

        }
    }
    public void setTurnDegrees(int degrees, int turnSpeed) {

        boolean reverse = false;
        int pulse;
        this.turnDegrees = Math.abs(degrees);
        this.turnSpeed = Math.abs(turnSpeed);
        turnTime = (int)(this.turnDegrees / (double)turnSpeed * 427);
        //multiplying by 427, after experimentation seemed to give an accurate time in milliseconds to turn.
        if (turnDegrees < 0) {
            reverse = true;
        }
        if (reverse) {
            pulse = 1500 - turnSpeed;
        } else {
            pulse = 1500 + turnSpeed;
        }
        sLinks.update(pulse);
        sRecht.update(pulse);
        setAction("turnDegrees");
        timer = new Timer(turnTime);
    }
}
