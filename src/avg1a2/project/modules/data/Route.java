package avg1a2.project.modules.data;

import java.util.ArrayList;

public class Route {
    private ArrayList<String> steps;
    private int currentStep;
    private boolean complete;
    private boolean finished;

    public Route(){
        this.steps = new ArrayList<>();
        this.complete = false;
        this.finished = false;
    }

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

    public void reset() {
        this.finished = false;
        this.currentStep = 0;
    }

    public void clearSteps() {
        this.steps = new ArrayList<>();
        this.complete = false;
    }
}
