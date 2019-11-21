package avg1a2.project.engine;

public interface LED extends Component {
    void on();
    void off();
    void setDelay(int delay);
    void setOffset(int offSet);
    void setBrightness(int brightness);
}