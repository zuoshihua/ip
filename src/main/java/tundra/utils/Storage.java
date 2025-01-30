package tundra.utils;

import tundra.models.Task;
import tundra.models.TaskEnum;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String DATETIME_FORMAT = "MMM dd yyyy hh:mm a";
    private static final String VALUE_SEPARATOR = " \\| ";

    private final Path filePath;
    private final Scanner sc;
    private final PrintWriter pw;
    private int bad;

    public Storage(String path) throws IOException {
        filePath = Path.of(path);
        bad = 0;
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        sc = new Scanner(filePath);
        pw = new PrintWriter(filePath.toFile());
    }

    public int getBad() {
        return bad;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(VALUE_SEPARATOR, 2);
            try {
                Task task = TaskEnum.valueOf(parts[0]).getTask();
                if (task.fromStoredString(parts[1], VALUE_SEPARATOR)) {
                    tasks.add(task);
                } else {
                    bad++;
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                bad++;
            }
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            pw.println(task.toStoredString());
        }
    }

}
