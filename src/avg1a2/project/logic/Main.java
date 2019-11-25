package avg1a2.project.logic;

public class Main {
    public static void main (String[] args) {
        try {
            Program program = new Program();
            program.run();
        } catch (RuntimeException error) {
            System.out.println("The following error was encountered when running the program\n" + error);
        }
    }
}
