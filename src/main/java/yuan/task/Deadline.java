package yuan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task that accepts a deadline
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate deadline;

    /**
     * Constructor for deadline
     * @param description
     * @param date
     * @param isDone
     */
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
