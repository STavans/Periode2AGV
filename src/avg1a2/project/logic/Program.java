package avg1a2.project.logic;

import avg1a2.project.modules.controller.PcControl;
import avg1a2.project.modules.controller.RemoteControl;
import avg1a2.project.modules.data.DataStore;

/**
 * Main program which runs and supplements the running loop.
 */
class Program {
    private boolean running;
    private DataStore dataStore;
    private State state;
    private PcControl pcControl;
    private RemoteControl remoteControl;

    /**
     * Constructor calls the Init "script" and creates the program state.
     */
    Program() {
        running = true;
        dataStore = Init.buildData();
        state = new State("Override", "Routing");
    }

    /**
     * Main running loop, the controller to be called within this loop will change depending on the state of the program.
     * @throws RuntimeException If the program somehow enters an undefined state,
     *                          or if the states aren't defined properly, the loop will exit with an Illegal State error.
     */
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