package tundra;

import tundra.exceptions.TundraException;
import tundra.utils.Parser;
import tundra.utils.Storage;
import tundra.utils.TaskList;
import tundra.views.Command;
import tundra.views.Ui;

/**
 * Represents the entry point of the application.
 */
public class Tundra {

    private final String path;

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Tundra instance.
     *
     * @param path Location of the Tundra text file.
     */
    public Tundra(String path) {
        this.path = path;
        ui = new Ui();
        storage = new Storage(path);
        tasks = new TaskList();
    }

    /**
     * Loads all tasks from Tundra text file.
     *
     * Any tasks not yet written to Tundra text file will be lost.
     */
    public void loadAllTasks() {
        if (tasks.size() > 0) {
            tasks = new TaskList();
        }

        try {
            tasks.load(storage);
            ui.printMessage("I have successfully loaded all tasks!");
        } catch (TundraException e) {
            ui.printMessage(e.getMessage());
        }
    }

    /**
     * Returns the output of running a single command.
     *
     * @return The command output.
     */
    public String runCommand(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
        } catch (TundraException e) {
            ui.printMessage(e.getMessage() + "\n");
        }

        return ui.getLastMessage();
    }

    /**
     * Returns the last message printed to standard output.
     *
     * @return The cached output.
     */
    public String getLastMessage() {
        return ui.getLastMessage();
    }

    /**
     * Runs the application.
     * The user interface, storage source and task list are instantiated.
     */
    public void run() {
        loadAllTasks();
        while (ui.isRunning()) {
            String fullCommand = ui.readCommand();
            runCommand(fullCommand);
        }
    }

    public boolean shouldExit() {
        return !ui.isRunning();
    }

}
