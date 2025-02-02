package tundra.views;

import tundra.utils.Storage;
import tundra.utils.TaskList;

/**
 * Represents a command entered by the user.
 * A command consists of an array of arguments.
 */
public abstract class Command {

    private String[] arguments = null;

    public Command() {
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    /**
     * Converts the full command string into an array of arguments.
     * @param fullCommand The full command string
     */
    public abstract void init(String fullCommand);

    /**
     * Executes the command.
     * @param taskList The list of tasks the command accesses
     * @param ui The user interface the command communicates with
     * @param storage The storage source the command accesses
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
