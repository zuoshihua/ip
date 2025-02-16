package tundra.views;

import tundra.exceptions.TundraException;
import tundra.models.Task;
import tundra.models.TodoTask;
import tundra.utils.Storage;
import tundra.utils.TaskList;

/**
 * Represents the command "todo".
 */
public class TodoCommand extends Command {

    /**
     * {@inheritDoc}
     * Takes one string argument in the form of "[name]".
     */
    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        setArguments(parts);
    }

    /**
     * {@inheritDoc}
     * Adds a to-do task to the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        String[] arguments = getArguments();
        try {
            Task task = new TodoTask(arguments[1]);
            if (!isForced() && taskList.contains(task)) {
                throw new TundraException("Task already exists. " +
                        "Use force command to add it to the list regardless.");
            }
            taskList.add(task);
            ui.printMessage(
                    "Got it. I've added this task:\n",
                    "\t" + task + "\n",
                    "Now you have " + taskList.size() + " task(s) in the list.\n"
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TundraException("Incorrect syntax. Usage: todo [description]");
        }
    }
}
