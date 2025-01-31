package tundra.models;

import tundra.utils.Storage;
import tundra.utils.TaskList;
import tundra.views.Ui;

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
