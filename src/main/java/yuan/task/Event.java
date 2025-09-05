package yuan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task that accepts a start and end date
 */
public class Event extends Task {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for event task
     * @param description
     * @param start
     * @param end
     * @param isDone
     */
    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startTime.format(FORMATTER) + " to: " + endTime.format(FORMATTER) + ")";
    }

    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + startTime + " | " + endTime;
    }
}
