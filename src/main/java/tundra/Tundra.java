package tundra;

import tundra.exceptions.TundraException;
import tundra.utils.Parser;
import tundra.utils.Storage;
import tundra.utils.TaskList;
import tundra.views.Command;
import tundra.views.Ui;

/**
 * Represents the entry point of the application
 */
public class Tundra {

    private final String path;

    public Tundra(String path) {
        this.path = path;
    }

    /**
     * Runs the application.
     * The user interface, storage source and task list are instantiated.
     */
    public void run() {
        Ui ui = new Ui();
        Storage storage = new Storage(path);
        TaskList tasks = new TaskList();
        try {
            tasks.load(storage);
        } catch (TundraException e) {
            ui.printMessage(e.getMessage());
        }

        while (ui.isRunning()) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (TundraException e) {
                ui.printMessage(e.getMessage() + "\n");
            }
        }
    }

    /**
     * Instantiates a new application instance and runs it.
     * Takes no command line arguments.
     * @param args Supplied command line arguments.
     */
    public static void main(String[] args) {
        new Tundra("./data/tundra.txt").run();
    }
}
