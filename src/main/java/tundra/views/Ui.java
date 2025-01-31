package tundra.views;

import java.util.Scanner;

public class Ui {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    private final Scanner sc;
    private boolean isRunning;

    public Ui() {
        sc = new Scanner(System.in);
        isRunning = true;
        printMessage("Hello I'm Tundra!\n", "What can I do for you?\n");
    }

    public void printMessage(String... strings) {
        System.out.print(formatMessage(strings));
    }

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

    public String readCommand() {
        return sc.nextLine();
    }

    public void exit() {
        printMessage("Bye. Hope to see you again soon!\n");
        sc.close();
        isRunning = false;
    }

}
