package avg1a2.examplecode.maze.ultrasonic;

/**
 * Callback for the ultraSonic to use whenever it detects a collision.
 */
public interface UltraSonicCallback {
    void onUltraSonic();
    void closeUltraSonic();
    void onNoCollision();
}
