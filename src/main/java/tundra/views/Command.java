package tundra.views;

import tundra.utils.Storage;
import tundra.utils.TaskList;

public interface Command {

    public void execute(TaskList taskList, Ui ui, Storage storage);
}
