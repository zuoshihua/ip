package tundra.models;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task the user can create.
 * A <code>Task</code> has a name and a completed flag.
 */
public class Task {

    private String name;
    private boolean completed;

    public Task() {}

    /**
     * Creates a new <code>Task</code> with the specified name and completed flag unset.
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    /**
     * Processes the stored string representation of the <code>Task</code> and sets
     * its name and completed flag accordingly.
     * Returns true if processed successfully.
     * @param str Stored string.
     * @param separator Delimiter used in stored string.
     * @return Outcome of processing.
     */
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
