package tundra.models;

public class Todo extends Task {

    public Todo() {}

    public Todo(String name) {
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
        return String.format("T | %s", super.toStoredString());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
