public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toStorageFormat();

    public static Task fromStorageFormat(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return new Todo(description, isDone);

            case "D":
                String deadline = parts[3];
                return new Deadline(description, deadline, isDone);

            case "E":
                String from = parts[3];
                String to = parts[4];
                return new Event(description, from, to, isDone);

            default:
                throw new IllegalArgumentException("Wrong format in file: " + line);
        }
    }
}
