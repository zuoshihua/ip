package tundra.views;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import tundra.exceptions.TundraException;
import tundra.models.EventTask;
import tundra.models.Task;
import tundra.utils.Parser;
import tundra.utils.Storage;
import tundra.utils.TaskList;


/**
 * Represents the command "event".
 */
public class EventCommand extends Command {

    /**
     * {@inheritDoc}
     * Takes three arguments in the format "[description] /from [date/time] /to [date/time]".
     */
    @Override
    public void init(String fullCommand) {
        try {
            String body = fullCommand.split(" ", 2)[1];
            String[] first = body.split(" /from ", 2);
            String[] second = first[1].split(" /to ", 2);
            setArguments(new String[]{first[0], second[0], second[1]});
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TundraException("Incorrect syntax. "
                    + "Usage: event [description] /from [date/time] /to [date/time]");
        }
    }

    /**
     * {@inheritDoc}
     * Adds an event task into the task list.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        String[] arguments = getArguments();
        try {
            LocalDateTime from = LocalDateTime.parse(arguments[1], Parser.INPUT_FORMAT);
            LocalDateTime to = LocalDateTime.parse(arguments[2], Parser.INPUT_FORMAT);
            Task task = new EventTask(arguments[0], from, to);
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
        } catch (DateTimeParseException e) {
            String example = LocalDateTime.now().format(Parser.INPUT_FORMAT);
            throw new TundraException("Incorrect date/time format. Example: " + example);
        }
    }

}
