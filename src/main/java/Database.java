import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    private final File file;
    public Database(String path) {
        this.file = new File(path);
    }

    public void load(ArrayList<Task> list) throws TundraException {
        Scanner sc;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            sc = new Scanner(file);
        } catch (IOException e) {
            throw new TundraException("Error creating data file");
        }

        int bad = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" \\| ");
            Task task;
            try {
                switch (parts[0]) {
                    case "T" -> {
                        task = new Todo(parts[2]);
                    }
                    case "D" -> {
                        task = new Deadline(parts[2], LocalDateTime.parse(parts[3], Deadline.fmt));
                    }
                    case "E" -> {
                        LocalDateTime from = LocalDateTime.parse(parts[3], Event.fmt);
                        LocalDateTime to = LocalDateTime.parse(parts[4], Event.fmt);
                        task = new Event(parts[2], from, to);
                    }
                    default -> {
                        bad++;
                        continue;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                bad++;
                continue;
            }
            task.setCompleted(parts[1].equals("1"));
            list.add(task);
        }
        if (bad > 0) throw new TundraException("Data corrupt. Omitted " + bad + " tasks");
    }

    public void store(ArrayList<Task> list) throws TundraException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : list) {
                bw.write(task.toStoredString() + "\n");
            }
        } catch (IOException e) {
            throw new TundraException("Could not save tasks");
        }
    }
}
