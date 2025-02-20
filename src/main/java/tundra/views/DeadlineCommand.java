package tundra.views;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import tundra.exceptions.TundraException;
import tundra.models.DeadlineTask;
import tundra.models.Task;
import tundra.utils.Parser;
import tundra.utils.Storage;
import tundra.utils.TaskList;


/**
 * Represents the command "deadline".
 */
public class DeadlineCommand extends Command {

    /**
     * {@inheritDoc}
     * Takes two arguments in the format "[description] /by [date/time]".
     */
    @Override
    public void init(String fullCommand) {
        try {
            String body = fullCommand.split(" ", 2)[1];
            String[] parts = body.split(" /by ", 2);
            setArguments(new String[]{parts[0], parts[1]});
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TundraException("Incorrect syntax. "
                    + "Usage: deadline [description] /by [date/time]");
        }
    }

    /**
     * {@inheritDoc}
     * Adds a deadline task into the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        String[] arguments = getArguments();
        try {
            LocalDateTime due = LocalDateTime.parse(arguments[1], Parser.INPUT_FORMAT);
            Task task = new DeadlineTask(arguments[0], due);
            if (!isForced() && taskList.contains(task)) {
                throw new TundraException("Task already exists. "
                        + "Use force command to add it to the list regardless.");
            }
            taskList.add(task);
            ui.printMessage(
                    "Got it. I've added this task:\n",
                    "\t" + task + "\n",
                    "Now you have " + taskList.size() + " task(s) in the list.\n"
            );
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TundraException("Incorrect syntax. Usage: deadline [description] [date/time]");
        } catch (DateTimeParseException e) {
            String example = LocalDateTime.now().format(Parser.INPUT_FORMAT);
            throw new TundraException("Incorrect date/time format. Example: " + example);
        }
    }
}
