package avg1a2.examplecode.maze;

import java.util.ArrayList;

public class State {
    private ArrayList<String> states;
    private String currentState;

   /**State() {
        this.states = new ArrayList<>();
    }

    void addState(String state) {
        states.add(state);
    }

    public void setState(String state) throws IllegalArgumentException {
        if (states.contains(state)) {
            currentState = state;
        } else {
            throw new IllegalArgumentException("The requested state has not been defined.");
        }
    }

    public String getState() throws IllegalStateException {
        if (currentState != null) {
            return currentState;
        } else {
            throw new IllegalStateException("No State has been set");
        }
    }

    public Boolean ifState(String state) throws IllegalArgumentException {
        if (!states.contains(state)) {
            throw new IllegalArgumentException("The requested state has not been set.");
        }
        if (state.equals(currentState)) {
            return true;
        } else {
            return false;
        }
    }*/
}
