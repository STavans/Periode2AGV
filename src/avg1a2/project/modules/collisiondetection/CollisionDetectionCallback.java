package avg1a2.project.modules.collisiondetection;

/**
 * Callback used by the CollisionDetection to signal to the controllers.
 */
public interface CollisionDetectionCallback {
    void onFrontCollision();
    void onBackCollision();
    void onFrontEmergencyCollision();
    void onBackEmergencyCollision();
    void collisionDone();
}
