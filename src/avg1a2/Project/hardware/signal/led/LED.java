package avg1a2.project.hardware.signal.led;

import avg1a2.project.hardware.Component;

public interface LED extends Component {
    void on();
    void off();
    void setDelay(int delay);
    void setOffSet(int offSet);
    void setBrightness(int brightness);
}