package yuan.tasklist;

import yuan.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

//    public duke.tasklist.TaskList(ArrayList<duke.task.Task> tasks) {
//        this.tasks = tasks;
//    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
    }

    public void unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }
}
