package tundra.models;

import tundra.utils.Storage;
import tundra.utils.TaskList;
import tundra.views.Ui;

import java.util.ArrayList;

public class HelpCommand extends Command {

    @Override
    public void init(String fullCommand) {
        String[] parts = fullCommand.split(" ");
        setArguments(parts);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("Commands:\n");
        for (CommandEnum command : CommandEnum.values()) {
            lines.add("\t" + command.name().toLowerCase() + "\n");
        }
        ui.printMessage(lines.toArray(String[]::new));
    }
}
