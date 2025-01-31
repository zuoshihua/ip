package tundra.exceptions;

import tundra.views.Ui;

public class TundraException extends RuntimeException {
    public TundraException(String message) {
        super(message);
    }
}
