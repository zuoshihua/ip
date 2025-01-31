package tundra.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tundra.utils.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DeadlineTaskTest {

    @Test
    public void toStoredString_datetimeGiven_correctFormatReturned() {
        DateTimeFormatter fmt = Parser.INPUT_FORMAT;
        LocalDateTime dt = LocalDateTime.parse("2025-01-31 1800", fmt);
        DeadlineTask task = new DeadlineTask("task", dt);
        String expected = "Jan 31 2025 06:00 PM";
        String actual = task.toStoredString().split(" \\| ")[1];
        assertEquals(expected, actual);
    }
}
