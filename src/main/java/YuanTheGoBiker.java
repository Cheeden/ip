import java.util.Scanner;

public class YuanTheGoBiker {
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

            System.out.println("    " + line);
            System.out.println("    " + input);
            System.out.println("    " + line);
        }
    }
}
