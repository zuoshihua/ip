package tundra.views;

public class Ui {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printMessage(String... strings) {
        System.out.print(formatMessage(strings));
    }

    public static String formatMessage(String... strings) {
        String output = String.format("\t%s\n", HORIZONTAL_LINE);
        for (String string : strings) {
            output = String.format("%s\t%s", output, string);
        }
        return String.format("%s\t%s\n", output, HORIZONTAL_LINE);
    }
}
