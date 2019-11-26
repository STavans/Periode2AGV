package avg1a2.project.modules.data;

import avg1a2.project.hardware.Component;
import avg1a2.project.hardware.engine.Wheels;
import avg1a2.project.hardware.sensor.button.Button;
import avg1a2.project.hardware.sensor.ir.IRSensor;
import avg1a2.project.hardware.sensor.ultrasonic.UltrasonicSensor;
import avg1a2.project.hardware.sensor.whisker.Whisker;
import avg1a2.project.modules.collisiondetection.CollisionDetection;
import avg1a2.project.modules.controller.MotionControl;
import avg1a2.project.modules.controller.PcControl;
import avg1a2.project.modules.controller.RemoteControl;
import avg1a2.project.modules.irconversion.IRConversion;
import java.util.HashMap;

public class DataStore {
    private HashMap<String, Route> routes;
    private HashMap<String, CommandLayout> commandLayouts;
    private MotionControl motionControl;
    private PcControl pcControl;
    private RemoteControl remoteControl;
    private CollisionDetection collisionDetection;
    private IRConversion irConversion;
    private Component wheels;
    private Component button;
    private Component irSensor;
    private Component ultrasonicSensor;
    private Component whiskerLeft;
    private Component whiskerRight;

    public DataStore() {
        routes = new HashMap<>();
        commandLayouts = new HashMap<>();
    }

    public HashMap<String, Route> getRoutes() {
        return routes;
    }

    public void addRoute(String routeName, Route route) throws IllegalArgumentException{
        routes.put(routeName,route);
    }

    public HashMap<String, CommandLayout> getCommandLayouts() {
        return commandLayouts;
    }

    public void addCommandLayout(String layoutName, CommandLayout commandLayout) {
        commandLayouts.put(layoutName,commandLayout);
    }

    public void setMotionControl(MotionControl motionControl) {
        this.motionControl = motionControl;
    }

    public MotionControl getMotionControl() throws IllegalArgumentException {
        if (motionControl == null) {
            throw new IllegalArgumentException("Motion Control has not been initialized");
        } else {
            return this.motionControl;
        }
    }

    public void setPcControl(PcControl pcControl) {
        this.pcControl = pcControl;
    }

    public PcControl getPcControl() throws IllegalArgumentException {
        if (pcControl == null) {
            throw new IllegalArgumentException("Pc Control has not been initialized");
        } else {
            return this.pcControl;
        }
    }

    public void setRemoteControl(RemoteControl remoteControl) {
        this.remoteControl = remoteControl;
    }

    public RemoteControl getRemoteControl() throws IllegalArgumentException {
        if (remoteControl == null) {
            throw new IllegalArgumentException("Remote Control has not been initialized");
        } else {
            return this.remoteControl;
        }
    }

    public void setCollisionDetection(CollisionDetection collisionDetection) {
        this.collisionDetection = collisionDetection;
    }

    public CollisionDetection getCollisionDetection() throws IllegalArgumentException {
        if (collisionDetection == null) {
            throw new IllegalArgumentException("Collision Detection has not been initialized");
        } else {
            return this.collisionDetection;
        }
    }

    public void setIrConversion(IRConversion irConversion) {
        this.irConversion = irConversion;
    }

    public IRConversion getIrConversion() throws IllegalArgumentException {
        if (irConversion == null) {
            throw new IllegalArgumentException("Ir Conversion has not been initialized");
        } else {
            return this.irConversion;
        }
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public Component getWheels() throws IllegalArgumentException {
        if (wheels == null) {
            throw new IllegalArgumentException("Wheels has not been initialized");
        } else {
            return this.wheels;
        }
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Component getButton() throws IllegalArgumentException {
        if (button == null) {
            throw new IllegalArgumentException("Button has not been initialized");
        } else {
            return this.button;
        }
    }

    public void setIrSensor(IRSensor irSensor) {
        this.irSensor = irSensor;
    }

    public Component getIrSensor() throws IllegalArgumentException {
        if (irSensor == null) {
            throw new IllegalArgumentException("Ir Sensor has not been initialized");
        } else {
            return this.irSensor;
        }
    }

    public void setUltrasonicSensor(UltrasonicSensor ultrasonicSensor) {
        this.ultrasonicSensor = ultrasonicSensor;
    }

    public Component getUltrasonicSensor() throws IllegalArgumentException {
        if (ultrasonicSensor == null) {
            throw new IllegalArgumentException("Ultrasonic Sensor has not been initialized");
        } else {
            return this.ultrasonicSensor;
        }
    }

    public void setWhiskerLeft(Whisker whisker) {
        this.whiskerLeft = whisker;
    }

    public Component getWhiskerLeft() throws IllegalArgumentException {
        if (whiskerLeft == null) {
            throw new IllegalArgumentException("Left Whisker has not been initialized");
        } else {
            return this.whiskerLeft;
        }
    }

    public void setWhiskerRight(Whisker whisker) {
        this.whiskerRight = whisker;
    }

    public Component getWhiskerRight() throws IllegalArgumentException {
        if (whiskerRight == null) {
            throw new IllegalArgumentException("Right Whisker has not been initialized");
        } else {
            return this.whiskerRight;
        }
    }
}
