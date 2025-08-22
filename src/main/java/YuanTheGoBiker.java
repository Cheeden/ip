import java.util.Scanner;

public class YuanTheGoBiker {
    static String[] storage = new String[100];
    static int index = 0;
    static String line = "____________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! I'm Yuan");
        System.out.println("How can I help you?");
        System.out.println(line);

        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("    " + line);
                System.out.println("    " + "Bye. Hope to see you again");
                System.out.println("    " + line);
                break;
            }

            if (input.equals("storage")) {
                System.out.println("    " + line);
                for(int j = 0; j < index; j++) {
                    System.out.println("    " + (j+1) + "." + storage[j]);
                }
                System.out.println("    " + line);
                continue;
            }

            System.out.println("    " + line);
            System.out.println("    " + "added: " + input);
            System.out.println("    " + line);
            storage[index] = input;
            index++;
        }
    }
}
