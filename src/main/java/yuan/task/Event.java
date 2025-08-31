package yuan.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

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
