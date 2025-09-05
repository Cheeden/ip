package yuan.ui;

import java.util.Scanner;

import yuan.task.Task;
import yuan.tasklist.TaskList;

/**
 * Handles user interactions by displaying messages and reading input from the console.
 */
public class UI {
    private final Scanner scanner = new Scanner(System.in);

    public void showLine() {
        System.out.println("    ____________________________________________");
    }

    /**
     * Displays welcome msg when program starts
     */
    public void showWelcome() {
        showLine();
        showMessage("Hello... I'm Yuan");
        showMessage("Why are you bothering me");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println("    " + msg);
    }

    /**
     * prints marked task in terminal
     * @param task
     */
    public void showMark(Task task) {
        showLine();
        showMessage("Fine... I've marked this task as done:");
        showMessage(task.toString());
        showLine();
    }

    /**
     * prints unmarked task in terminal
     * @param task
     */
    public void showUnmark(Task task) {
        showLine();
        showMessage("Fine... I've marked this task as not done:");
        showMessage(task.toString());
        showLine();
    }

    /**
     * shows error message
     * @param msg
     */
    public void showError(String msg) {
        showLine();
        showMessage(msg);
        showLine();
    }

    /**
     * prints all tasks in the list
     * @param taskList
     */
    public void showTasks(TaskList taskList) {
        showLine();
        for (int i = 0; i < taskList.size(); i++) {
            showMessage((i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    /**
     * prints newly added task
     * @param task
     * @param newSize
     */
    public void showAdded(Task task, int newSize) {
        showLine();
        showMessage("Alright, I've added this task:");
        showMessage(task.toString());
        showMessage("Now you have " + newSize + " tasks in the list");
        showLine();
    }

    /**
     * prints removed task
     * @param task
     * @param newSize
     */
    public void showRemoved(Task task, int newSize) {
        showLine();
        showMessage("Fine... I've removed this task:");
        showMessage(task.toString());
        showMessage("Now you have " + newSize + " tasks in the list");
        showLine();
    }

    /**
     * prints bye message
     */
    public void showBye() {
        showLine();
        showMessage("Bye. I don't wanna see you again");
        showLine();
    }
}
