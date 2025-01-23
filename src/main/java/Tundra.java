import java.util.Scanner;

public class Tundra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String welcome = "\t____________________________________________________________\n"
                + "\tHello! I'm Tundra\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(welcome);

        String userInput = "";
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            String response = "\t____________________________________________________________\n"
                    + "\t" + userInput + "\n"
                    + "\t____________________________________________________________\n";
            System.out.println(response);
        }

        String goodbye = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";
        System.out.println(goodbye);
    }
}
