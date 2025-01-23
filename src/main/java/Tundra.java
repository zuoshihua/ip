import java.util.ArrayList;
import java.util.Scanner;

public class Tundra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> db = new ArrayList<>();
        String welcome = "\t____________________________________________________________\n"
                + "\tHello! I'm Tundra\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(welcome);

        String userInput = "";
        while (true) {
            userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("\t____________________________________________________________");
                for (String s : db) {
                    System.out.printf("\t%d. %s\n", db.indexOf(s) + 1, s);
                }
                System.out.println("\t____________________________________________________________");
            } else if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else {
                db.add(userInput);
                String response = "\t____________________________________________________________\n"
                        + "\tadded: " + userInput + "\n"
                        + "\t____________________________________________________________\n";
                System.out.println(response);
            }
        }

        String goodbye = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";
        System.out.println(goodbye);
    }
}
