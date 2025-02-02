package tundra.models;

/**
 * Represents a to-do task the user can create.
 * A <code>TodoTask</code> has a name and a completed flag.
 */
public class TodoTask extends Task {

    public TodoTask() {}

    /**
     * Creates a new <code>TodoTask</code> with the specified name and completed flag unset.
     * @param name Name of the to-do task.
     */
    public TodoTask(String name) {
        super(name);
    }

    @Override
    public boolean fromStoredString(String str, String separator) {
        try {
            return super.fromStoredString(str, separator);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String toStoredString() {
        return String.format("%s | %s", TaskEnum.T, super.toStoredString());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TaskEnum.T, super.toString());
    }
}
