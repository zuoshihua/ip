package tundra.utils;

import tundra.models.Task;
import tundra.models.TaskEnum;

import java.io.FileNotFoundException;
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

    public Storage(String path) {
        filePath = Path.of(path);
    }

    private static void createSaveFile(Path fp) throws IOException {
        if (!Files.exists(fp.getParent())) {
            Files.createDirectories(fp.getParent());
        }
        if (!Files.exists(fp)) {
            Files.createFile(fp);
        }
    }

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

    public boolean save(ArrayList<Task> tasks) {
        try {
            createSaveFile(filePath);
            PrintWriter pw = new PrintWriter(filePath.toFile());
            for (Task task : tasks) {
                pw.println(task.toStoredString());
            }
            pw.close();
            return pw.checkError();
        } catch (IOException e) {
            return false;
        }
    }

}
