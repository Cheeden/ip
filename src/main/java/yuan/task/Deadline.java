package yuan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.deadline = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(FORMATTER) + ")";
    }

    @Override
    public String toStorageFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + description + " | " + deadline;
    }
}
