package avg1a2.examplecode.maze;

import TI.BoeBot;
import TI.Servo;
import TI.Timer;
import avg1a2.examplecode.maze.ultrasonic.*;

/**public class Controller implements LineDetectionCallback, UltraSonicCallback {
    private State state;
    private State state2;
    private Servo servoLeft;
    private Servo servoRight;
    private LineDetection lineDetection;
    private UltrasonicSensor2 ultrasonicSensor;
    private Memory memory;
    private boolean path;
    private boolean collision;
    private Timer timer;

     /public Controller(Servo servoLeft, Servo servoRight) {
         this.servoLeft = servoLeft;
         this.servoRight = servoRight;
         this.state = new State();
         this.state.addState("Discovery");
         this.state.addState("Smart");
         this.state2 = new State();
         this.state2.addState("Riding");
         this.state2.addState("Turning");
         this.state2.setState("Riding");
         this.lineDetection = new LineDetection(new LightSensor(2),new LightSensor(1), new LightSensor(0), new LightSensor(3),this);
         this.ultrasonicSensor = new UltrasonicSensor2(1,0,this);
         this.memory = new Memory();
     }

     public void run() {
         ultrasonicSensor.update();
         //System.out.println(path);
         if (!collision) {
             if (!memory.isActive()) {
                 state.setState("Discovery");
             }
             if (state.ifState("Discovery")) {
                 lineDetection.update();
             } else if (state.ifState("Smart")) {
                 memory.run();
             }
         }
     }

    @Override
    public void onCrossroads() {
         if (state2.ifState("Riding") && (timer == null || timer.timeout())) {
             System.out.println("Reached Here!");
             this.state2.setState("Turning");
             BoeBot.wait(100);
             servoLeft.update(1500);
             servoRight.update(1500);

             servoLeft.update(1550);
             servoRight.update(1550);
             timer = new Timer(200);
             timer.mark();
         }
     }

    @Override
    public void lineCorrectionLeft() {
         if (state2.ifState("Riding")) {
             servoRight.update(1550);
             servoLeft.update(1500);
         }
    }

    @Override
    public void lineCorrectionRight() {
         if (state2.ifState("Riding")) {
             servoRight.update(1500);
             servoLeft.update(1550);
         }
    }

    @Override
    public void noDetection() {
         if (state2.ifState("Riding")) {
             servoLeft.update(1550);
             servoRight.update(1450);
         }
        if (state2.ifState("Turning") && (timer == null || timer.timeout())) {
            servoLeft.update(1500);
            servoRight.update(1500);
            BoeBot.wait(500);
            if (path) {
                servoLeft.update(1550);
                servoRight.update(1450);
                timer = new Timer(100);
                timer.mark();
                state2.setState("Riding");
            } else {
                servoLeft.update(1450);
                servoRight.update(1450);
                timer = new Timer(200);
                timer.mark();
            }
        }
    }

    @Override
    public void stop() {
         if (state2.ifState("Riding")) {
             servoRight.update(1500);
             servoLeft.update(1500);
         }
    }

    @Override
    public void onUltraSonic() {
        this.path = false;
        this.collision = true;
    }

    @Override
    public void closeUltraSonic() {
         this.path = false;
         this.collision = true;
    }

    @Override
    public void onNoCollision() {
        this.path = true;
        this.collision = false;
    }
}*/
