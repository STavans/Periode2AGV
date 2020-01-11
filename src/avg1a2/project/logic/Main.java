package avg1a2.project.logic;

/**
 * Only starts the programs and runs it.
 * It also catches any runtime exceptions and prints the error message in the console.
 */
public class Main {
    public static void main (String[] args) {
        try {
            Program program = new Program();
            program.run();

        } catch (RuntimeException error) {
            System.out.println("The following error was encountered when running the program\n" + error);
            error.printStackTrace();
        }
    }
}
