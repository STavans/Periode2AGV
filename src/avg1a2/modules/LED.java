package avg1a2.modules;

/**
 * This interface defines what public functions a boebot.modules.LED is required to support.
 * These functions are universally necessary functions needed for basic boebot.modules.LED operations.
 */
public interface LED {
    void Run();
    void On();
    void Off();
    void SetDelay(int delay);
    void SetOffset(int offset);
    void SetBrightness(int dutycycle);
}
