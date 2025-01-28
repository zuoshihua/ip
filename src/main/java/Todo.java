public class Todo extends Task {

    public Todo(String name) {
        super(name);
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
