package tundra.views;

import java.util.ArrayList;

import tundra.models.Task;
import tundra.utils.Storage;
import tundra.utils.TaskList;


/**
 * Represents the command "list".
 */
public class ListCommand extends Command {

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
     * Prints all the tasks in their string representations.
     * A task's string representation differs from their stored string representation.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Here are the tasks in your list:\n");
        int i = 0;
        for (Task task : taskList.getTasks()) {
            lines.add(++i + "." + task.toString() + "\n");
        }
        ui.printMessage(lines.toArray(String[]::new));
    }
}
