package Duke;

public class Todo extends Task{

    private String taskDescription;

    public Todo(String taskName) {
        super(taskName);
        if (taskName.length() == 0) {
            throw new DukeException("Error: ", DukeException.TYPE.INCOMPLETE);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return this.toString();
    }
}
