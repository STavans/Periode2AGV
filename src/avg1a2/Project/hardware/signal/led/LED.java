package avg1a2.project.hardware.signal.led;

import avg1a2.project.hardware.Component;

/**
 * Interface describes what led objects need to contain.
 */
public interface LED extends Component {
    void on();
    void off();
    void setDelay(int delay);
    void setOffset(int offSet);
}