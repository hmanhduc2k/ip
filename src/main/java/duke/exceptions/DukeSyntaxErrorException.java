package duke.exceptions;

public class DukeSyntaxErrorException extends DukeException {
    public DukeSyntaxErrorException(String command) {
        super("Uh, what is that ah? I cannot recognize '" + command + "'\n", Type.SYNTAX_ERROR);
    }
}
