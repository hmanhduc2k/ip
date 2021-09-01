package duke.exceptions;

public class DukeIncompleteException extends DukeException {
    public DukeIncompleteException() {
        super("☹ Cannot lah! Your command is incomplete!\n", Type.INCOMPLETE);
    }
}
