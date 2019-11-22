import avg1a2.project.hardware.signal.led.IndividualLed;
import avg1a2.project.hardware.signal.led.LED;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;

public class TestMain {
    public static void main (String args[]) {
            LED test1 = new IndividualLed(2);
            LED test2 = new LedGroup();
            LED test3 = new NeoPixel(1);

            while (true) {
                try {
                    test1.update();
                    test2.update();
                    test3.update();
                } catch (RuntimeException runtimeException) {
                   System.out.println(runtimeException);
                }
        }
    }
}
