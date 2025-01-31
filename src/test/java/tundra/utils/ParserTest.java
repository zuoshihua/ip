package tundra.utils;

import org.junit.jupiter.api.Test;
import tundra.exceptions.TundraException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parse_pipeCharacter_exceptionThrown() {
        assertThrows(TundraException.class, () -> Parser.parse("todo | 1 "));
    }

}
