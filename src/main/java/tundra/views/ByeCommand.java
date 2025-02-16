package tundra.views;

import tundra.utils.Storage;
import tundra.utils.TaskList;


/**
 * Represents the command "bye".
 */
public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     * Takes no arguments.
     */
    @Override
    public void init(String fullCommand) {
        setArguments(fullCommand.split(" "));
    }

    /**
     * {@inheritDoc}
     * Saves the task list then calls exit on the user interface.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        taskList.save(storage);
        ui.exit();
    }
}
