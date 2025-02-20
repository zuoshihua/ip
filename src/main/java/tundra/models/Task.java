package tundra.models;


/**
 * Represents a task the user can create.
 * A <code>Task</code> has a name and a completed flag.
 */
public class Task {

    private String name;
    private boolean isCompleted;

    public Task() {
    }

    /**
     * Creates a new <code>Task</code> with the specified name and completed flag unset.
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
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
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public String toStoredString() {
        return String.format("%s | %s", isCompleted ? "1" : "0", name);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", name);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return name.equals(otherTask.name);
    }
}
