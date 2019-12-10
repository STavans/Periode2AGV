package avg1a2.examplecode.maze;

public interface LineDetectionCallback {
    void onCrossroads();
    void lineCorrectionLeft();
    void lineCorrectionRight();
    void noDetection();
    void stop();
}
