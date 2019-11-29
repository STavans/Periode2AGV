package avg1a2.project.modules.irconversion;

/**
 * Conversion callback which calls functions based on the code received.
 */
public interface IRConversionCallback {
    void leftDiagonal();
    void forward();
    void rightDiagonal();
    void leftTurn();
    void stop();
    void rightTurn();
    void leftBackDiagonal();
    void reverse();
    void rightBackDiagonal();
    void mute();
    void switchOn();
    void infiniteRightTurn();
    void infiniteLeftTurn();
    void square();
    void triangle();
    void smoothTurnLeft();
    void smoothTurnRight();
    void speedUp();
    void slowDown();


}
