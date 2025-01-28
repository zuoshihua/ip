public class Deadline extends Task {

    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toStoredString() {
        return String.format("D | %s | %s", super.toStoredString(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
