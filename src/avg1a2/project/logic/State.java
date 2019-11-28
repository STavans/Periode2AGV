package avg1a2.project.logic;

import java.util.ArrayList;

public class State {
    private ArrayList<String> states;
    private String currentState;

    public State() {
        this.states = new ArrayList<>();
    }


    public void addState(String state) {
        states.add(state);
    }


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
}
