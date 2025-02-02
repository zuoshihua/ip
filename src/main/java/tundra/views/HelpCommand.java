package tundra.views;

import tundra.utils.Storage;
import tundra.utils.TaskList;

import java.util.ArrayList;

/**
 * Represents the command "help".
 */
public class HelpCommand extends Command {

    /**
     * {@inheritDoc}
     * Takes no arguments.
     */
    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ");
        setArguments(parts);
    }

    /**
     * {@inheritDoc}
     * Prints a short summary of available commands.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("Commands:\n");
        for (CommandEnum command : CommandEnum.values()) {
            lines.add("\t" + command.name().toLowerCase() + "\n");
        }
        ui.printMessage(lines.toArray(String[]::new));
    }
}
