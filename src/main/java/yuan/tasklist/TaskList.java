package yuan.tasklist;

import java.util.ArrayList;

import yuan.task.Task;

/**
 * Represents a list of tasks. Provides method to add, remove, and get size of list
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    //public duke.tasklist.TaskList(ArrayList<duke.task.Task> tasks) {
    //   this.tasks = tasks;
    //}

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Finding a list of task with keyword
     * @param keyword
     * @return
     */
    public TaskList findTaskWithKeyword(String keyword) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                result.addTask(task);
            }
        }
        return result;
    }

    /**
     * Removes task from tasklist
     * @param index
     * @return
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in removeTask";
        return tasks.remove(index);
    }

    /**
     * Marks task as done
     * @param index
     */
    public void markTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in markTask";
        Task task = tasks.get(index);
        task.markAsDone();
    }

    /**
     * Marks task as not done
     * @param index
     */
    public void unmarkTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in unmarkTask";
        Task task = tasks.get(index);
        task.markAsNotDone();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in get";
        return tasks.get(index);
    }
}
