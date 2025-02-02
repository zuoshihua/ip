package tundra.models;

import java.time.LocalDateTime;

import tundra.utils.Parser;

/**
 * Represents an event task the user can create.
 * An <code>EventTask</code> has a name, a completed flag,
 * a starting date and time and an ending date and time
 */
public class EventTask extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask() {
    }

    /**
     * Creates a new <code>EventTask</code> with the specified name, the
     * specified date, the specified starting date and time,
     * the specified ending date and time and the completed flag unset.
     * @param name Name of the event task
     * @param from Starting date and time of the event task
     * @param to Ending date and time of the event task
     */
    public EventTask(String name, LocalDateTime from, LocalDateTime to) {
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
            setFrom(LocalDateTime.parse(parts[0], Parser.OUTPUT_FORMAT));
            setTo(LocalDateTime.parse(parts[1], Parser.OUTPUT_FORMAT));
            return super.fromStoredString(parts[2], separator);
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public String toStoredString() {
        return String.format("%s | %s | %s | %s",
                TaskEnum.E, from.format(Parser.OUTPUT_FORMAT),
                to.format(Parser.OUTPUT_FORMAT),
                super.toStoredString());
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)",
                TaskEnum.E, super.toString(),
                from.format(Parser.OUTPUT_FORMAT),
                to.format(Parser.OUTPUT_FORMAT));
    }
}
