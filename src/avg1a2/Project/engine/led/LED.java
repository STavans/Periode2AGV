package avg1a2.project.engine.led;

import avg1a2.project.engine.Component;

public interface LED extends Component {
    void on();
    void off();
    void setDelay(int delay);
    void setOffset(int offSet);
}