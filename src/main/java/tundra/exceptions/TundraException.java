package tundra.exceptions;

import tundra.views.Ui;

/**
 * Represents a problem encountered by the application.
 */
public class TundraException extends RuntimeException {
    public TundraException(String message) {
        super(message);
    }
}
