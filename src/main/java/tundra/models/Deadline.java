import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toStoredString() {
        return String.format("D | %s | %s", super.toStoredString(), by.format(fmt));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by.format(fmt));
    }
}
