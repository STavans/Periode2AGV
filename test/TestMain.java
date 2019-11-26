import TI.BoeBot;
import avg1a2.project.hardware.signal.led.IndividualLed;
import avg1a2.project.hardware.signal.led.LED;
import avg1a2.project.hardware.signal.led.LedGroup;
import avg1a2.project.hardware.signal.led.NeoPixel;

public class TestMain {
    public static void main (String args[]) {
        LedGroup pixels = new LedGroup(500);
        pixels.addLed("pixel1" ,new NeoPixel(0,153,255,153));
        pixels.addLed("pixel2" ,new NeoPixel(1,51,102,255));
        pixels.addLed("pixel3" ,new NeoPixel(2,255,51,204));
        pixels.addLed("pixel4" ,new NeoPixel(3,255,255,0));
        pixels.addLed("pixel5" ,new NeoPixel(4,0,51,102));
        pixels.addLed("pixel6" ,new NeoPixel(5,0,102,255));

        while (true) {
            try {
                pixels.update();
            } catch (RuntimeException runtimeException) {
                System.out.println(runtimeException);
            }
            BoeBot.wait(1);
        }
    }
}
