package avg1a2.project.logic;

/**
 * Manages the active state of this object, which can be used to redirect branches of command.
 * Currently only has the ability to hold 2 different states, may change in the future dependent on if it is deemed useful or necessary.
 */
public class State {
    private String active;
    private String inactive;
    private boolean state;

    /**
     * Constructor to set the state of the object.
     * @param active Active state represented by a String for the state name.
     * @param inactive Inactive state represented by a String for the state name.
     */
    State(String active, String inactive) {
        this.active = active;
        this.inactive = inactive;
        this.state = false;
    }

    /**
     * Sets the current state based on received input. If the state was not defined for this object, it throws an error.
     * @param state The state to which to change this object, represented by a String.
     * @throws IllegalArgumentException Error message to notify the state that was given was not defined in the object.
     */
    public void setState(String state) throws IllegalArgumentException {
        if (state.equals(active)) {
            this.state = true;
        } else if (state.equals(inactive)) {
            this.state = false;
        } else {
            throw new IllegalArgumentException("Not a valid state.");
        }
    }

    /**
     * Getter function to get the current state, returns the state based on a boolean attribute.
     * @return The current state of this object.
     */
    String getState() {
        if (state) {
            return active;
        } else {
            return inactive;
        }
    }
}
