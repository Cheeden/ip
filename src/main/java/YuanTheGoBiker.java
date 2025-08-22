import java.util.Scanner;

public class YuanTheGoBiker {
    static Task[] taskList = new Task[100];
    static int index = 0;
    static String line = "____________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! I'm Yuan");
        System.out.println("How can I help you?");
        System.out.println(line);

        String input = "";

        while (true) {
            input = scanner.nextLine();
            String[] parts = input.split(" ");

            if (input.equals("bye")) {
                System.out.println("    " + line);
                System.out.println("    " + "Bye. Hope to see you again");
                System.out.println("    " + line);
                break;
            }

            if (input.equals("storage")) {
                System.out.println("    " + line);
                for(int j = 0; j < index; j++) {
                    System.out.println("    " + (j+1) + "." + "[" + taskList[j].getStatusIcon() + "] " + taskList[j].description);
                }
                System.out.println("    " + line);
                continue;
            }

            if (parts[0].equals("mark")) {
                int listNumber = Integer.parseInt(parts[1]);
                taskList[listNumber - 1].markAsDone();
                System.out.println("    " + line);
                System.out.println("    Okay! I've mark this task as done:");
                System.out.println("    [" + taskList[listNumber - 1].getStatusIcon() + "] " + taskList[listNumber - 1].description);
                System.out.println("    " + line);
                continue;
            } else if (parts[0].equals("unmark")) {
                int listNumber = Integer.parseInt(parts[1]);
                taskList[listNumber - 1].markAsNotDone();
                System.out.println("    " + line);
                System.out.println("Okay! I've mark this task as not done:");
                System.out.println("[" + taskList[listNumber - 1].getStatusIcon() + "] " + taskList[listNumber - 1].description);
                System.out.println("    " + line);
                continue;
            }

            System.out.println("    " + line);
            System.out.println("    " + "added: " + input);
            System.out.println("    " + line);
            taskList[index] = new Task(input);
            index++;
        }
    }
}
