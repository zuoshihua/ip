public class TundraException extends RuntimeException {
    public TundraException(String message) {
        super("\t____________________________________________________________\n"
                + "\t" + message + "\n"
                + "\t____________________________________________________________\n");
    }
}
