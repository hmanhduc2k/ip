public abstract class Task {
    private final String taskName;
    private boolean status;

    public enum Type {
        TODO, DEADLINE, EVENT
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    protected void markAsCompleted() {
        this.status = true;
    }

    @Override
    public String toString() {
        if (status) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
