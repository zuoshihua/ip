package tundra.views;

import tundra.exceptions.TundraException;
import tundra.models.Task;
import tundra.utils.Storage;
import tundra.utils.TaskList;

public class MarkCommand extends Command {

    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        setArguments(parts);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] arguments = getArguments();
        try {
            int i = Integer.parseInt(arguments[1]) - 1;
            Task task = taskList.get(i);
            task.setCompleted(true);
            taskList.update(i, task);
            ui.printMessage(
                    "Nice! I've marked this task as done:\n",
                    "\t" + task + "\n"
            );
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new TundraException("Incorrect syntax. Usage: mark [number]");
        } catch (IndexOutOfBoundsException e) {
            throw new TundraException("No such task. Enter 'list' to see all tasks");
        }
    }

}
