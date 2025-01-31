package tundra.models;

public class TodoTask extends Task {

    public TodoTask() {}

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
