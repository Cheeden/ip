package yuan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.application.Platform;
import yuan.exception.YuanException;
import yuan.storage.Storage;
import yuan.task.Deadline;
import yuan.task.Event;
import yuan.task.Task;
import yuan.task.Todo;
import yuan.tasklist.TaskList;
import yuan.ui.UI;


/**
 * The main entry point of the chatbot application.
 * Yuan is a task manager that allows users to add, list, mark
 * unmark, remove tasks in the task manager
 */
public class Yuan {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final Storage storage = new Storage("./data/yuan.txt");
    private static final TaskList taskList = storage.load();
    private static final UI ui = new UI();

    /**
     * Generates a response for the user's chat message for gui.
     */
    public String getResponse(String input) {
        return executeCommand(input);
    }

    /**
     * execute commands method for gui
     * @param input
     * @return
     */
    public String executeCommand(String input) {
        try {
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String instruction = parts.length > 1 ? parts[1] : "";

            if (input.equals("bye")) {
                Platform.exit();
                return "Bye. I don't wanna see you again";
            }

            if (input.equals("list")) {
                return ui.renderTasks(taskList);
            }

            if (command.equals("find")) {
                TaskList found = taskList.findTaskWithKeyword(instruction);
                return ui.renderTasks(found);
            }

            if (command.equals("mark")) {
                int idx = Integer.parseInt(instruction) - 1;
                if (idx < 0 || idx >= taskList.size()) {
                    throw new YuanException("No task with that number!");
                }
                taskList.markTask(idx);
                storage.save(taskList);
                return ui.renderMark(taskList.get(idx));
            }

            if (command.equals("unmark")) {
                int idx = Integer.parseInt(instruction) - 1;
                if (idx < 0 || idx >= taskList.size()) {
                    throw new YuanException("No task with that number!");
                }
                taskList.unmarkTask(idx);
                storage.save(taskList);
                return ui.renderUnmark(taskList.get(idx));
            }

            switch (command) {
            case "todo":
                if (instruction.isEmpty()) {
                    throw new YuanException("Todo cannot be empty.");
                }
                Task todo = new Todo(instruction, false);
                taskList.addTask(todo);
                storage.save(taskList);
                return ui.renderAdded(todo, taskList.size());

            case "deadline":
                String[] rest = instruction.split(" /by ", 2);
                String desc = rest[0];
                LocalDate by = LocalDate.parse(rest[1], formatter);
                Task deadline = new Deadline(desc, by, false);
                taskList.addTask(deadline);
                storage.save(taskList);
                return ui.renderAdded(deadline, taskList.size());

            case "event":
                String[] fromParts = instruction.split(" /from ", 2);
                String descE = fromParts[0];
                String[] toParts = fromParts[1].split(" /to ", 2);
                LocalDate from = LocalDate.parse(toParts[0], formatter);
                LocalDate to = LocalDate.parse(toParts[1], formatter);
                Task event = new Event(descE, from, to, false);
                taskList.addTask(event);
                storage.save(taskList);
                return ui.renderAdded(event, taskList.size());

            case "delete":
                int idxDel = Integer.parseInt(instruction) - 1;
                if (idxDel < 0 || idxDel >= taskList.size()) {
                    throw new YuanException("No task with that number!");
                }
                Task removed = taskList.removeTask(idxDel);
                storage.save(taskList);
                return ui.renderRemoved(removed, taskList.size());

            default:
                throw new YuanException("What are you saying??? Try again lil bro");
            }
        } catch (Exception e) {
            return ui.renderError(e.getMessage());
        }
    }

    /**
     * main method for cli
     * @param args
     */
    public static void main(String[] args) {
        ui.showWelcome();

        String input;

        while (true) {
            try {
                input = ui.readCommand();
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                String instruction = parts.length > 1 ? parts[1] : "";

                if (input.equals("bye")) {
                    ui.showBye();
                    break;
                }

                if (input.equals("list")) {
                    ui.showTasks(taskList);
                    continue;
                }

                if (command.equals("find")) {
                    String keyword = instruction;
                    TaskList found = taskList.findTaskWithKeyword(keyword);
                    ui.showTasks(found);
                    continue;
                }

                if (command.equals("mark")) {
                    int markIndex = Integer.parseInt(instruction) - 1;

                    //error handling
                    if (markIndex < 0 || markIndex >= taskList.size()) {
                        throw new YuanException("Dude, there there isn't a task with that number!");
                    }

                    taskList.markTask(markIndex);
                    storage.save(taskList);
                    ui.showMark(taskList.get(markIndex));
                    continue;

                } else if (command.equals("unmark")) {
                    int unmarkIndex = Integer.parseInt(instruction) - 1;

                    //error handling
                    if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) {
                        throw new YuanException("Dude, there there isn't a task with that number!");
                    }

                    taskList.unmarkTask(unmarkIndex);
                    storage.save(taskList);
                    ui.showUnmark(taskList.get(unmarkIndex));
                    continue;
                }

                switch (command) {
                case "todo":
                    //error handling
                    if (instruction.isEmpty()) {
                        throw new YuanException("Yo, I don't know what you mean, why is it empty??");
                    }

                    Task todo = new Todo(instruction, false);
                    taskList.addTask(todo);
                    storage.save(taskList);
                    ui.showAdded(todo, taskList.size());
                    break;

                case "deadline":
                    try {
                        String[] rest = instruction.split(" /by ", 2);
                        String deadlineDescription = rest[0];
                        LocalDate by = LocalDate.parse(rest[1], formatter);
                        Task deadline = new Deadline(deadlineDescription, by, false);
                        taskList.addTask(deadline);
                        storage.save(taskList);
                        ui.showAdded(deadline, taskList.size());

                    } catch (DateTimeParseException e) {
                        ui.showError("Don't make me say again, pls enter the date in dd/MM/yyyy format");
                    }
                    break;

                case "event":
                    try {
                        String[] fromParts = instruction.split(" /from ", 2);
                        String eventDescription = fromParts[0];
                        String[] toParts = fromParts[1].split(" /to ", 2);
                        LocalDate from = LocalDate.parse(toParts[0], formatter);
                        LocalDate to = LocalDate.parse(toParts[1], formatter);
                        Task event = new Event(eventDescription, from, to, false);
                        taskList.addTask(event);
                        storage.save(taskList);
                        ui.showAdded(event, taskList.size());

                    } catch (DateTimeParseException e) {
                        ui.showError("Don't make me say again, pls enter the date in dd/MM/yyyy format");
                    }
                    break;

                case "delete":
                    //error handling
                    if (instruction.isEmpty()) {
                        throw new YuanException("Yo, you need to specify the task number to delete!");
                    }

                    int deleteIndex = Integer.parseInt(instruction) - 1;

                    //error handling
                    if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
                        throw new YuanException("Dude, there isn't a task with that number!");
                    }

                    Task removedTask = taskList.removeTask(deleteIndex);
                    storage.save(taskList);
                    ui.showRemoved(removedTask, taskList.size());
                    break;

                default:
                    throw new YuanException("Try again man, you are almost there");
                }
            } catch (YuanException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
