package tundra.models;

import tundra.exceptions.TundraException;
import tundra.utils.Storage;
import tundra.utils.TaskList;
import tundra.views.Ui;

public class TodoCommand extends Command {

    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        setArguments(parts);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] arguments = getArguments();
        try {
            Task task = new TodoTask(arguments[1]);
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
