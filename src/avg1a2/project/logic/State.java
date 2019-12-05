package avg1a2.project.logic;

import java.util.ArrayList;

/**
 * Class uses Strings to identify States, each state object will keep track of all set states and throw an error at all rejectsfornow ones as a safety measure.
 */
public class State {
    private ArrayList<String> states;
    private String currentState;

    /**
     * Constructor only initializes a new list.
     */
    State() {
        this.states = new ArrayList<>();
    }

    /**
     * Adds a state to the ArrayList and makes it available as a proper state.
     * @param state The State to add.
     */
    public void addState(String state) {
        states.add(state);
    }

    /**
     * Sets the current active State, will throw an error if trying to set a state that hasn't been added.
     * @param state State you want to set as active.
     * @throws IllegalArgumentException Error message to indicate an error in the state name, or it hasn't been added.(Catches mostly typos)
     */
    public void setState(String state) throws IllegalArgumentException {
        if (states.contains(state)) {
            currentState = state;
        } else {
            throw new IllegalArgumentException("The requested state has not been defined.");
        }
    }

    /**
     * Getter function to get the current state, returns the state based on a boolean attribute.
     * @return The current state of this object.
     */
    public String getState() throws IllegalStateException {
        if (currentState != null) {
            return currentState;
        } else {
            throw new IllegalStateException("No State has been set");
        }
    }

    /**
     * Checks if the given state is the current active state and returns true or false.
     * @param state The State to check.
     * @return true if state is active, false if it's not.
     */
    public Boolean ifState(String state) throws IllegalArgumentException {
        if (!states.contains(state)) {
            throw new IllegalArgumentException("The requested state has not been set.");
        }
        if (state.equals(currentState)) {
            return true;
        } else {
            return false;
        }
    }
}
