package tundra.views;

import tundra.exceptions.TundraException;
import tundra.utils.Parser;
import tundra.utils.Storage;
import tundra.utils.TaskList;

/**
 * Represents the command "force".
 */
public class ForceCommand extends Command {


    /**
     * {@inheritDoc}
     * Takes one valid command as an argument.
     */
    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        setArguments(parts);
    }

    /**
     * {@inheritDoc}
     * Calls {@link Command#setForced(boolean)} with <code>true</code>
     * before executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Command c = Parser.parse(getArguments()[1]);
            c.setForced(true);
            c.execute(taskList, ui, storage);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TundraException("Incorrect syntax. Usage: force [command]");
        }
    }
}
