package tundra.views;

import java.util.Scanner;

/**
 * Represents a user interface the user interacts with to use the application
 */
public class Ui {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    private final Scanner sc;
    private boolean isRunning;
    private String lastMessage;

    /**
     * Creates a new user interface instance.
     * Prints the welcome message.
     */
    public Ui() {
        sc = new Scanner(System.in);
        isRunning = true;
        printMessage("Hello I'm Tundra!\n", "What can I do for you?\n");
    }

    /**
     * Prints the formatted speech bubble to standard output.
     * @param strings Variadic argument representing strings to format.
     * @see #formatMessage(String...)
     */
    public void printMessage(String... strings) {
        String formattedMessage = formatMessage(strings);
        System.out.print(formattedMessage);
        lastMessage = String.join("", strings);
    }

    /**
     * Formats multiple strings as a speech bubble.
     * @param strings Variadic argument representing strings to format.
     * @return Formatted speech bubble.
     */
    public String formatMessage(String... strings) {
        String output = String.format("\t%s\n", HORIZONTAL_LINE);
        for (String string : strings) {
            output = String.format("%s\t%s", output, string);
        }
        return String.format("%s\t%s\n", output, HORIZONTAL_LINE);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a farewell message and sets isRunning to false
     */
    public void exit() {
        printMessage("Bye. Hope to see you again soon!\n");
        sc.close();
        isRunning = false;
    }

}
