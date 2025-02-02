package tundra.views;

import java.util.ArrayList;

import tundra.exceptions.TundraException;
import tundra.models.Task;
import tundra.utils.Storage;
import tundra.utils.TaskList;

/**
 * Represents the command "find".
 */
public class FindCommand extends Command {

    /**
     * {@inheritDoc}
     * Takes one argument in the form of "[keyword]"
     */
    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ", 2);
        setArguments(parts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> lines = new ArrayList<>();
        String[] arguments = getArguments();
        lines.add("Here are the matching tasks in your list:\n");
        int i = 0;
        try {
            for (Task task : taskList.getTasks()) {
                String name = task.getName();
                if (name.contains(arguments[1])) {
                    lines.add(++i + "." + task.toString() + "\n");
                }
            }
            if (i > 0) {
                ui.printMessage(lines.toArray(String[]::new));
            } else {
                ui.printMessage("No matching tasks found.\n");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TundraException("Incorrect syntax. Usage: find [keyword]");
        }
    }
}
