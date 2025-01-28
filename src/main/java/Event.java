import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStoredString() {
        return String.format("E | %s | %s | %s", super.toStoredString(), from.format(fmt), to.format(fmt));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(fmt), to.format(fmt));
    }
}
