package tundra.views;

import tundra.utils.Storage;
import tundra.utils.TaskList;

public abstract class Command {

    private String[] arguments = null;

    public Command() {}

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public abstract void init(String fullCommand);

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
