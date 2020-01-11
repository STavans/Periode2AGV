package avg1a2.project.modules.data;

import java.util.ArrayList;

/**
 * Class which defines and manages the functions of a route.
 */
public class Route {
    private ArrayList<String> steps;
    private int currentStep;
    private boolean complete;
    private boolean finished;

    /**
     * Constructor which initializes the attributes except for currentStep.
     */
    public Route(){
        this.steps = new ArrayList<>();
        this.complete = false;
        this.finished = false;
    }

    /**
     * Function which adds a step to the current route.
     * @param step Step which to add.
     * @throws IllegalArgumentException Throws error when the given step is not a defined, possible step.
     * @throws IllegalStateException Throws error if the route is finished and a step is still trying to be added.
     */
    public void addStep(String step) throws IllegalArgumentException, IllegalStateException {
        if (!complete) {
            if (step.equals("Left") || step.equals("Right") || step.equals("Forward") || step.equals("Stop")) {
                this.steps.add(step);
            } else if (step.equals("End")) {
                this.steps.add(step);
                this.complete = true;
            } else {
                throw new IllegalArgumentException(step + ": is not a valid step.");
            }
        } else {
            throw new IllegalStateException("The Route has already been completed.");
        }
    }

    /**
     * Method used to request the next step.
     * @return String version of the next step listed in the route.
     * @throws IllegalStateException Throws and exception if the route has no end defined, has already finished, or if there is an error with the route itself in the arrayList.
     */
    public String nextStep() throws IllegalStateException {
        if (!complete) {
            throw new IllegalStateException("The route does not have an endpoint defined!");
        } else if (finished) {
            throw new IllegalStateException("The route has already been finished.");
        } else if (currentStep >= steps.size()) {
            throw new IllegalStateException("The requested step does not exist.");
        }
        String step = steps.get(currentStep);
        if (step.equals("End")) {
            this.finished = true;
        } else {
            this.currentStep++;
        }
        return step;
    }

    /**
     * Resets the route back to step 1, meaning a route can be used multiple times.
     */
    public void reset() {
        this.finished = false;
        this.currentStep = 0;
    }
}
