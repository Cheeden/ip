package yuan.ui;

import yuan.task.Task;
import yuan.tasklist.TaskList;

import java.util.Scanner;

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

    public void showMark(Task task) {
        showLine();
        showMessage("Fine... I've marked this task as done:");
        showMessage(task.toString());
        showLine();
    }

    public void showUnmark(Task task) {
        showLine();
        showMessage("Fine... I've marked this task as not done:");
        showMessage(task.toString());
        showLine();
    }

    public void showError(String msg) {
        showLine();
        showMessage(msg);
        showLine();
    }

    public void showTasks(TaskList taskList) {
        showLine();
        for (int i = 0; i < taskList.size(); i++) {
            showMessage((i + 1) + ". " + taskList.get(i));
        }
        showLine();
    }

    public void showAdded(Task task, int newSize) {
        showLine();
        showMessage("Alright, I've added this task:");
        showMessage(task.toString());
        showMessage("Now you have " + newSize + " tasks in the list");
        showLine();
    }

    public void showRemoved(Task task, int newSize) {
        showLine();
        showMessage("Fine... I've removed this task:");
        showMessage(task.toString());
        showMessage("Now you have " + newSize + " tasks in the list");
        showLine();
    }

    public void showBye() {
        showLine();
        showMessage("Bye. I don't wanna see you again");
        showLine();
    }
}
