package tundra.utils;

import tundra.exceptions.TundraException;
import tundra.models.Command;
import tundra.models.CommandEnum;
import tundra.models.HelpCommand;

import java.time.format.DateTimeFormatter;

public class Parser {

    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private Parser() {}

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
