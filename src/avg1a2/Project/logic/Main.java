package avg1a2.project.logic;

import TI.*;

public class Main {
    public static void main (String[] args) {
//        Program program = new Program();
//            program.run();
        while(true) {
            BoeBot.digitalWrite(0, true);
            BoeBot.uwait(300);
            BoeBot.digitalWrite(0,false);

            int pulse = BoeBot.pulseIn(1, true, 10000);

            System.out.println( "Pulse: " + pulse);
            BoeBot.uwait(500);
            //Hij geeft een -2 als de sensor een object detecteerd
        }

    }
}
