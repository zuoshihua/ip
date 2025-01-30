package tundra.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    private LocalDateTime from;
    private LocalDateTime to;

    public Event() {}

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public boolean fromStoredString(String str, String separator) {
        try {
            String[] parts = str.split(separator, 3);
            setFrom(LocalDateTime.parse(parts[0]));
            setTo(LocalDateTime.parse(parts[1]));
            return super.fromStoredString(parts[2], separator);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String toStoredString() {
        return String.format("E | %s | %s | %s", from.format(fmt), to.format(fmt), super.toStoredString());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(fmt), to.format(fmt));
    }
}
