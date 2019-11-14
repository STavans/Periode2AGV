package avg1a2.engine;

import TI.BoeBot;
import avg1a2.modules.*;


/**
 * This class will define and maintain the running loop.
 * It will serve as the heart of this program.
 */
class Program {
    Program() {
    }

    /**
     * Running loop the program will continuously run, using all enabled modules.
     */
    void Run() {
        // Initialization of used Parameters.
        Engine engine = new Engine(14,15);

        while (true) { // The actual infinite loop the program uses.
            engine.TurnDegrees(90,60);
            BoeBot.wait(2000);
        }
    }
}