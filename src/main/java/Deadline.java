public class Deadline extends Task {
    String deadline;

    public Deadline(String description, String date, boolean isDone) {
        super(description, isDone);
        this.deadline = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toStorageFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + deadline;
    }
}
