import java.util.Scanner;

public class YuanTheGoBiker {
    static Task[] taskList = new Task[100];
    static int index = 0;

    public static void line() {
        System.out.println("    ____________________________________________");
    }

    public static void printMessage(String msg) {
        System.out.println("    " + msg);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        line();
        printMessage("Hello! I'm Yuan");
        printMessage("How can I help you?");
        line();

        String input = "";

        while (true) {
            input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            String instruction = parts.length > 1 ? parts[1] : "";

            if (input.equals("bye")) {
                line();
                printMessage("Bye. Hope to see you again");
                line();
                break;
            }

            if (input.equals("list")) {
                line();
                for(int j = 0; j < index; j++) {
                    printMessage((j+1) + "." + taskList[j].toString());
                }
                line();
                continue;
            }

            if (command.equals("mark")) {
                int listNumber = Integer.parseInt(instruction);
                taskList[listNumber - 1].markAsDone();
                line();
                printMessage("Okay! I've mark this task as done:");
                printMessage(taskList[listNumber - 1].toString());
                line();
                continue;
            } else if (command.equals("unmark")) {
                int listNumber = Integer.parseInt(instruction);
                taskList[listNumber - 1].markAsNotDone();
                line();
                printMessage("Okay! I've mark this task as not done:");
                printMessage(taskList[listNumber - 1].toString());
                line();
                continue;
            }

            switch (command) {
                case "todo":
                    taskList[index] = new Todo(instruction);

                    line();
                    printMessage("Alright, I've added this task:");
                    printMessage(taskList[index].toString());
                    printMessage("Now you have " + (index + 1) + " tasks in the list");
                    line();

                    index++;
                    break;

                case "deadline":
                    String[] rest = instruction.split(" /by ", 2);
                    String deadlineDescription = rest[0];
                    String by = rest[1];
                    taskList[index] = new Deadline(deadlineDescription, by);

                    line();
                    printMessage("Alright, I've added this task:");
                    printMessage(taskList[index].toString());
                    printMessage("Now you have " + (index + 1) + " tasks in the list");
                    line();

                    index++;
                    break;

                case "event":
                    String[] fromParts = instruction.split(" /from ", 2);
                    String eventDescription = fromParts[0];
                    String[] toParts = fromParts[1].split(" /to ", 2);
                    String from = toParts[0];
                    String to = toParts[1];
                    taskList[index] = new Event(eventDescription, from, to);

                    line();
                    printMessage("Alright, I've added this task:");
                    printMessage(taskList[index].toString());
                    printMessage("Now you have " + (index + 1) + " tasks in the list");
                    line();

                    index++;
                    break;
            }
        }
    }
}
