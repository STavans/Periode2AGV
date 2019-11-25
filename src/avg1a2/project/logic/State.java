package avg1a2.project.logic;

public class State {
    private String override;
    private String routing;
    private boolean overwritten;

    State() {
        this.override = "Override";
        this.routing = "Routing";
        this.overwritten = false;
    }

    public void setState(String state) throws IllegalArgumentException {
        if (state.equals("Override")) {
            this.overwritten = true;
        } else if (state.equals("Routing")) {
            this.overwritten = false;
        } else {
            throw new IllegalArgumentException("Not a valid state.");
        }
    }

    String getState() {
        if (overwritten) {
            return override;
        } else {
            return routing;
        }
    }
}
