package tundra.views;

import tundra.utils.Storage;
import tundra.utils.TaskList;

public class ListCommand implements Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Ui.printMessage("Here are the tasks in your list:", taskList.toString());
    }
}
