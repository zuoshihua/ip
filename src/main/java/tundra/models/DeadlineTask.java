package tundra.models;

import tundra.utils.Parser;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {

    private LocalDateTime due;

    public DeadlineTask() {}

    public DeadlineTask(String name, LocalDateTime due) {
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
            setDue(LocalDateTime.parse(parts[0], Parser.OUTPUT_FORMAT));
            return super.fromStoredString(parts[1], separator);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String toStoredString() {
        return String.format("%s | %s | %s",
                TaskEnum.D,
                due.format(Parser.OUTPUT_FORMAT),
                super.toStoredString());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)",
                TaskEnum.D,
                super.toString(),
                due.format(Parser.OUTPUT_FORMAT));
    }
}
