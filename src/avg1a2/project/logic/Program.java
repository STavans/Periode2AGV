package avg1a2.project.logic;

import TI.BoeBot;
import avg1a2.project.modules.data.DataStore;

/**
 * Main program which runs and supplements the running loop.
 */
class Program {
    private boolean running;
    private DataStore dataStore;

    /**
     * Constructor calls the Init "script" and creates the program state.
     */
    Program() {
        running = true;
        dataStore = Init.buildData();
        dataStore.getProgramState().setState("BlueBot");
    }

    /**
     * Main running loop, the controller to be called within this loop will change depending on the state of the program.
     * @throws RuntimeException If the program somehow enters an undefined state,
     *                          or if the states aren't defined properly, the loop will exit with an Illegal State error.
     */
    void run() throws IllegalStateException{
        boolean valid = false;
        while (running) {
            switch (dataStore.getProgramState().getState()) {
                case "Override" :
                    dataStore.getRemoteControl().run();
                    valid = true;
                    break;
                case "BlueBot" :
                    dataStore.getBlueBotControl().run();
                    valid = true;
                    break;
            }
            if (!valid) {
                throw new IllegalStateException("Program exited due to an illegal state.");
            }
            BoeBot.wait(1); //BoeBot wait 1 is required for the program to function and not disrupt itself, this is due to BoeBot behaviour.
        }
    }
}