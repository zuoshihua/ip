package tundra.views;

import tundra.utils.Storage;
import tundra.utils.TaskList;

public class ByeCommand extends Command {

    @Override
    public void init(String fullCommand) {
        setArguments(fullCommand.split(" "));
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.save(storage);
        ui.exit();
    }
}
