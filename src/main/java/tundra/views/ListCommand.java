package tundra.views;

import java.util.ArrayList;

import tundra.models.Task;
import tundra.utils.Storage;
import tundra.utils.TaskList;

public class ListCommand extends Command {

    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ");
        setArguments(parts);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Here are the tasks in your list:\n");
        int i = 0;
        for (Task task : taskList.getTasks()) {
            lines.add(++i + "." + task.toString() + "\n");
        }
        ui.printMessage(lines.toArray(String[]::new));
    }
}
