package tundra.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import tundra.exceptions.TundraException;
import tundra.models.Task;
import tundra.models.TaskEnum;


/**
 * Represents the text file containing tasks in their stored string representation.
 */
public class Storage {

    private static final String VALUE_SEPARATOR = " \\| ";

    private final Path filePath;

    public Storage(String path) {
        filePath = Path.of(path);
    }

    /**
     * Creates the text file if it does not exist.
     * @param fp Path to the text file.
     * @throws IOException If text file could not be created.
     */
    private static void createSaveFile(Path fp) throws IOException {
        if (!Files.exists(fp.getParent())) {
            Files.createDirectories(fp.getParent());
        }
        if (!Files.exists(fp)) {
            Files.createFile(fp);
        }
    }

    /**
     * Populates a collection with the tasks read from the text file.
     * @param tasks Collection of tasks.
     * @return Status of the loading process where 0 means all tasks were loaded successfully,
     * -1 means the text file could not be opened,
     * an integer greater than 0 denotes the number of tasks that failed to load.
     */
    public int load(ArrayList<Task> tasks) {
        Scanner sc;
        try {
            createSaveFile(filePath);
            sc = new Scanner(filePath);
        } catch (IOException e) {
            return -1;
        }

        int bad = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(VALUE_SEPARATOR, 2);
            try {
                Task task = TaskEnum.valueOf(parts[0]).getTask();
                if (task.fromStoredString(parts[1], VALUE_SEPARATOR))
                    tasks.add(task);
                else
                    bad++;
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                bad++;
            }
        }

        return bad;
    }

    /**
     * Writes a collection of tasks in their stored string representations to the text file
     * @param tasks Collection of tasks
     */
    public void save(ArrayList<Task> tasks) {
        try (PrintWriter pw = new PrintWriter(filePath.toFile())) {
            for (Task task : tasks) {
                pw.println(task.toStoredString());
            }
        } catch (IOException e) {
            throw new TundraException("I'm sorry. I couldn't save your tasks.");
        }
    }

}
