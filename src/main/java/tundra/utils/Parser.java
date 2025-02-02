package tundra.utils;

import java.time.format.DateTimeFormatter;

import tundra.exceptions.TundraException;
import tundra.views.Command;
import tundra.views.CommandEnum;
import tundra.views.HelpCommand;


/**
 * A non-instantiable utility class to convert a user input string into a <code>Command</code> instance.
 */
public class Parser {

    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private Parser() {

    }

    /**
     * Returns a <code>Command</code> based on user input.
     * @param input User input string from standard input.
     * @return Command corresponding to the user input.
     */
    public static Command parse(String input) {
        if (input.contains("|")) {
            throw new TundraException("I'm sorry. You are not allowed to use the '|' symbol");
        }

        try {
            String[] args = input.split(" ", 2);
            String name = args[0].toUpperCase();
            Command command = CommandEnum.valueOf(name).getCommand();
            command.init(input);
            return command;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
            HelpCommand help = new HelpCommand();
            help.init("");
            return help;
        }
    }
}
