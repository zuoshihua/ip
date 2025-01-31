package tundra.models;

import java.time.format.DateTimeFormatter;

public class Task {

    private String name;
    private boolean completed;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public boolean fromStoredString(String str, String separator) {
        String[] parts = str.split(separator, 2);
        try {
            setCompleted(parts[0].equalsIgnoreCase("1"));
            setName(parts[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toStoredString() {
        return String.format("%s | %s", completed ? "1" : "0", name);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", name);
    }
}
