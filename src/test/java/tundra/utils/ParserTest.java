package tundra.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tundra.exceptions.TundraException;


public class ParserTest {

    @Test
    public void parse_pipeCharacter_exceptionThrown() {
        assertThrows(TundraException.class, () -> Parser.parse("todo | 1 "));
    }

}
