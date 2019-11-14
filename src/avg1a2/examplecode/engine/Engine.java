package avg1a2.examplecode.engine;

import TI.BoeBot;
import TI.Servo;

/**
 * This class operates calls towards the boebot wheels used for motion.
 */
class Engine {
    private Servo leftWheel;
    private Servo rightWheel;
    private int currentSpeed;
    private int turnDelay;

    /**
     * Constructor sets the initial attributes, including the wheels to be controlled and the current speed of the boebot.
     */
    Engine(int leftPin, int rightPin){
        this.leftWheel = new Servo(leftPin);
        this.rightWheel = new Servo(rightPin);
        this.currentSpeed = 0;
    }

    /**
     * Accelerates, or Decelerates the boebot to match a new target speed.
     * This function does not support instant brakes, but instead slows it down to a halt over time.
     * @param targetSpeed The new speed for the boebot to accelerate or decelerate towards.
     */
    void Accelerate(int targetSpeed){
        int targetAcceleration;
        int steps;
        int accellStep;
        int leftPulse = 1500 + currentSpeed;
        int rightPulse = 1500 - currentSpeed;
        targetAcceleration = targetSpeed - this.currentSpeed;
        if (targetAcceleration != 0) {
            steps = Math.abs(targetAcceleration / 5); //Decided to default accelerate in steps of 5 during tests, may change later.
            accellStep = targetAcceleration / steps;
            for (int i  = steps; i > 0; i--) {
                leftPulse += accellStep;
                rightPulse -= accellStep;
                Wheels(leftPulse, rightPulse);
                this.currentSpeed += accellStep;
                BoeBot.wait(100);
            }
        }
    }

    /**
     * Immediately stops the boebot movement in case of path obstruction.
     */
    void EmergencyBrake() {
        this.leftWheel.stop();
        this.rightWheel.stop();
        this.currentSpeed = 0;
    }

    /**
     * Turns the boebot in place at a given speed.
     * Is very inaccurate and would likely not be used much in practical application.
     * @param turnSpeed Speed with which the boebot should turn.
     */
    void Turn(int turnSpeed) {
        int prevSpeed = this.currentSpeed;
        int rightWheelTurn = 1500 + turnSpeed;
        int leftWheelTurn = 1500 + turnSpeed;
        Accelerate(0);
        Wheels(leftWheelTurn,rightWheelTurn);
        BoeBot.wait(1500); //Decided to wait for 1,5 seconds each turn by default in order to see the results clearly.
        Wheels(1500,1500);
        Accelerate(prevSpeed);
    }

    /**
     * Turns the boebot in place for a given set of degrees at a certain given speed.
     * @param degrees The degrees (between 0 and 360) for the boebot to turn.
     * @param turnSpeed The speed with which to turn the boebot.
     */
    void TurnDegrees(int degrees, int turnSpeed){
        int prevSpeed = this.currentSpeed;
        double turnTime = ((double)degrees / turnSpeed) * 425; //multiplying by 427, after experimentation seemed to give an accurate time in milliseconds to turn.
        int pulse = 1500 + turnSpeed;

        Accelerate(0);
        Wheels(pulse,pulse);
        BoeBot.wait((int)turnTime);
        Wheels(1500,1500);
        Accelerate(prevSpeed);
    }

    /**
     * Helper function to send wheel pulses.
     * @param leftPulse Pulse to send to the left wheel.
     * @param rightPulse Pulse to send to the right wheel.
     */
    private void Wheels(int leftPulse, int rightPulse){
        if (leftPulse <= 2500 && leftPulse >= 1500 && rightPulse <= 2500 && rightPulse >= 1500) {
            this.leftWheel.update(leftPulse);
            this.rightWheel.update(rightPulse);
        }
    }
}
