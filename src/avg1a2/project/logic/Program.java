package avg1a2.project.logic;

import avg1a2.project.modules.controller.PcControl;
import avg1a2.project.modules.controller.RemoteControl;
import avg1a2.project.modules.data.DataStore;

class Program {
    private boolean running;
    private DataStore dataStore;
    private State state;
    private PcControl pcControl;
    private RemoteControl remoteControl;

    Program() {
        running = true;
        dataStore = Init.buildData();
        state = new State();
    }

    void run() throws RuntimeException{
        while (running) {
            if (state.getState().equals("Override")) {
                remoteControl.run(dataStore);
            } else if (state.getState().equals("Routing")) {
                pcControl.run(dataStore);
            } else {
                running = false;
                throw new RuntimeException("Program exited due to an illegal state.");
            }
        }
    }
}