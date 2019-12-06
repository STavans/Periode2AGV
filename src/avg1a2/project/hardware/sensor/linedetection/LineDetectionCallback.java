package avg1a2.project.hardware.sensor.linedetection;

public interface LineDetectionCallback {
    void onCrossroads();
    void lineCorrectionLeft();
    void lineCorrectionRight();
    void onLineLost();
}
