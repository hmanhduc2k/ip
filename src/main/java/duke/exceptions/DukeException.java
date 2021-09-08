package duke.exceptions;

public abstract class DukeException extends RuntimeException {
    private static final String DEFAULT_STRING = "Please type in 'help' for instructions";
    /**
     * Constructor for Duke Exception class
     * @param message Error message
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Indicating the message error for each type of Duke Exception used
     * @return message error of Duke Exception
     */
    @Override
    public String getMessage() {
        return super.getMessage() + DEFAULT_STRING;
    }
}

