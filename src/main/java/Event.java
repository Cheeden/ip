public class Event extends Task {
    String startTime;
    String endTime;

    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.startTime = start;
        this.endTime = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    @Override
    public String toStorageFormat() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | " + startTime + " | " + endTime;
    }
}
