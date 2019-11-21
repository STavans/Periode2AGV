package avg1a2.examplecode.engine;

import TI.BoeBot;
import avg1a2.examplecode.modules.*;

import java.util.HashMap;


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
        Speaker speaker = new Speaker(6,1500,1500);
        RemoteControll remoteControll = new RemoteControll(0b00001,engine,speaker);
        IR infra = new IR(8);
        HashMap<String, Integer> scan;

        while (true) { // The actual infinite loop the program uses.
            scan = new HashMap<>(infra.Scan());
            switch (scan.get("command")) {
                case 0b1011010 :
                    remoteControll.Start();
                    break;
                case 0b1011000 :
                    remoteControll.Forward();
                    break;
                case 0b1011001 :
                    remoteControll.Backward();
                    break;
                case 0b0010010 :
                    remoteControll.Right();
                    break;
                case 0b0010011 :
                    remoteControll.Left();
                    break;
                case 0b1011100 :
                    remoteControll.increaseSpeed();
                    break;
                case 0b1011110 :
                    remoteControll.DecreaseSpeed();
                    break;
                case 0b1011111 :
                    remoteControll.Stop();
                    break;
                case 0b0010101 :
                    remoteControll.Alarm();
                    break;
                case 0b0010100 :
                    remoteControll.mute();
                    break;
                case 0b0001001 :
                    remoteControll.Donut();
                    break;
            }
            BoeBot.wait(1);
        }
    }
}