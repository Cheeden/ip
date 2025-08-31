package yuan.task;

public class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageFormat() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
