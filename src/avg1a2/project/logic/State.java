package avg1a2.project.logic;

public class State {
    private String override;
    private String routing;
    private boolean overwritten;

    public State() {
        this.override = "Override";
        this.routing = "Routing";
        this.overwritten = false;
    }


    public String getState() {
        if (overwritten) {
            return override;
        } else {
            return routing;
        }
    }
}
