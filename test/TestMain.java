import TI.BoeBot;

public class TestMain {
    public static void main (String args[]) {
        while(true) {
            BoeBot.digitalWrite(1, true);
            BoeBot.wait(1);
            BoeBot.digitalWrite(1,false);

            int pulse = BoeBot.pulseIn(11,true,10000);
            System.out.println( "Pulse: " + pulse);
            BoeBot.wait(50);
        }

    }
}
