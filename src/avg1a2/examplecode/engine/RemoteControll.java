package avg1a2.examplecode.engine;


import avg1a2.examplecode.modules.Speaker;

public class RemoteControll {
    private int id;
    private Engine engine;
    private Speaker speaker;
    private int savedFreq;
    private boolean muted = true;
    private boolean started = false;

    public RemoteControll(int id, Engine engine, Speaker speaker) {
        this.id = id;
        this.engine = engine;
        this.speaker = speaker;
    }

    public void Start() {
        engine.Accelerate(200);
        started = true;
    }

    public void Forward(){
        if (started && engine.getCurrentSpeed() < 0) {
            engine.Accelerate(Math.abs(engine.getCurrentSpeed()));
        }
    }

    public void Backward(){
        if (started && engine.getCurrentSpeed() > 0) {
            engine.Accelerate(-engine.getCurrentSpeed());
        }
    }

    public void Left(){
        if (started) {
            engine.TurnDegrees(-90,engine.getCurrentSpeed());
        }
    }

    public void Right(){
        if (started) {
            engine.TurnDegrees(90,engine.getCurrentSpeed());
        }
    }

    public void Stop(){
        engine.EmergencyBrake();
        started = false;
    }

    public void increaseSpeed(){
        if (started) {
            if (Math.abs(engine.getCurrentSpeed()) <= 100) {
                engine.Accelerate(engine.getCurrentSpeed() + 20);
            }
        }
    }

    public void DecreaseSpeed(){
        if (started) {
            if (Math.abs(engine.getCurrentSpeed()) <= 200 && engine.getCurrentSpeed() > 0) {
                engine.Accelerate(engine.getCurrentSpeed() - 20);
            }
        }
    }

    public void Donut() {
        if (started) {
            engine.Turn(engine.getCurrentSpeed());
        }
    }

    public void Alarm() {
        speaker.Beep();
    }

    public void mute() {
        if (muted) {
            speaker.SetFrequency(savedFreq);
            muted = true;
        } else {
            this.savedFreq = speaker.getFrequency();
            speaker.SetFrequency(0);
            muted = false;
        }
    }

    public int getId() {
        return id;
    }
}
