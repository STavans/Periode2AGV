package avg1a2.project.hardware.sensor.linedetection;

/**
 * Interface which defines all the functions/situations the LineDetection needs to supply for.
 */
public interface LineDetectionCallback {
    void onCrossroads();
    void lineCorrectionLeft();
    void lineCorrectionRight();
    void onLineLost();
    void goForward();
}
