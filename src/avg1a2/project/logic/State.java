package avg1a2.project.logic;

public class State {
    private String active;
    private String inactive;
    private boolean state;

    State(String active, String inactive) {
        this.active = active;
        this.inactive = inactive;
        this.state = false;
    }

    public void setState(String state) throws IllegalArgumentException {
        if (state.equals(active)) {
            this.state = true;
        } else if (state.equals(inactive)) {
            this.state = false;
        } else {
            throw new IllegalArgumentException("Not a valid state.");
        }
    }
    
    String getState() {
        if (state) {
            return active;
        } else {
            return inactive;
        }
    }
}
