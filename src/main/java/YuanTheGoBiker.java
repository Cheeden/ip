import java.util.ArrayList;
import java.util.Scanner;

public class YuanTheGoBiker {
    static Storage storage = new Storage("./data/yuan.txt");
    static ArrayList<Task> taskList = storage.load();

    public static void line() {
        System.out.println("    ____________________________________________");
    }

    public static void printMessage(String msg) {
        System.out.println("    " + msg);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        line();
        printMessage("Hello... I'm Yuan");
        printMessage("Why are you bothering me");
        line();

        String input = "";

        while (true) {
            try {
                input = scanner.nextLine();
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                String instruction = parts.length > 1 ? parts[1] : "";

                if (input.equals("bye")) {
                    line();
                    printMessage("Bye. I don't wanna see you again");
                    line();
                    break;
                }

                if (input.equals("list")) {
                    line();
                    for (int j = 0; j < taskList.size(); j++) {
                        printMessage((j + 1) + "." + taskList.get(j).toString());
                    }
                    line();
                    continue;
                }

                if (command.equals("mark")) {
                    int markIndex = Integer.parseInt(instruction) - 1;

                    //error handling
                    if (markIndex < 0 || markIndex >= taskList.size()) {
                        throw new YuanException("Dude, there there isn't a task with that number!");
                    }

                    taskList.get(markIndex).markAsDone();
                    storage.save(taskList);
                    line();
                    printMessage("Okay! I've mark this task as done:");
                    printMessage(taskList.get(markIndex).toString());
                    line();
                    continue;
                } else if (command.equals("unmark")) {
                    int unmarkIndex = Integer.parseInt(instruction) - 1;

                    //error handling
                    if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) {
                        throw new YuanException("Dude, there there isn't a task with that number!");
                    }

                    taskList.get(unmarkIndex).markAsNotDone();
                    storage.save(taskList);
                    line();
                    printMessage("Okay! I've mark this task as not done:");
                    printMessage(taskList.get(unmarkIndex).toString());
                    line();
                    continue;
                }

                switch (command) {
                    case "todo":
                        //error handling
                        if (instruction.isEmpty()) {
                            throw new YuanException("Yo, I don't know what you mean, why is it empty??");
                        }
                        taskList.add(new Todo(instruction, false));
                        storage.save(taskList);

                        line();
                        printMessage("Alright, I've added this task:");
                        printMessage(taskList.get(taskList.size() - 1).toString());
                        printMessage("Now you have " + taskList.size() + " tasks in the list");
                        line();

                        break;

                    case "deadline":
                        String[] rest = instruction.split(" /by ", 2);
                        String deadlineDescription = rest[0];
                        String by = rest[1];
                        taskList.add(new Deadline(deadlineDescription, by, false));
                        storage.save(taskList);

                        line();
                        printMessage("Alright, I've added this task:");
                        printMessage(taskList.get(taskList.size() - 1).toString());
                        printMessage("Now you have " + taskList.size() + " tasks in the list");
                        line();

                        break;

                    case "event":
                        String[] fromParts = instruction.split(" /from ", 2);
                        String eventDescription = fromParts[0];
                        String[] toParts = fromParts[1].split(" /to ", 2);
                        String from = toParts[0];
                        String to = toParts[1];
                        taskList.add(new Event(eventDescription, from, to, false));
                        storage.save(taskList);

                        line();
                        printMessage("Alright, I've added this task:");
                        printMessage(taskList.get(taskList.size() - 1).toString());
                        printMessage("Now you have " + taskList.size() + " tasks in the list");
                        line();

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

                        Task removedTask = taskList.remove(deleteIndex);
                        storage.save(taskList);

                        line();
                        printMessage("Fine... I've removed this task:");
                        printMessage(removedTask.toString());
                        printMessage("Now you have " + taskList.size() + " tasks in the list");
                        line();

                        break;

                    default:
                        throw new YuanException("Try again man, you are almost there");
                }
            } catch (YuanException e) {
                line();
                printMessage(e.getMessage());
                line();
            }
        }
    }
}
