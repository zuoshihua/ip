package tundra.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime due;

    public Deadline() {}

    public Deadline(String name, LocalDateTime due) {
        super(name);
        this.due = due;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    @Override
    public boolean fromStoredString(String str, String separator) {
        try {
            String[] parts = str.split(separator, 2);
            setDue(LocalDateTime.parse(parts[0]));
            return super.fromStoredString(parts[1], separator);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String toStoredString() {
        return String.format("D | %s | %s", due.format(DATETIME_FORMATTER), super.toStoredString());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), due.format(DATETIME_FORMATTER));
    }
}
