import java.util.ArrayList;
import java.util.Scanner;

public class Tundra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> db = new ArrayList<>();
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
                for (Task s : db) {
                    System.out.printf("\t%d.%s\n", db.indexOf(s) + 1, s);
                }
                System.out.println("\t____________________________________________________________");
            } else if (userInput.contains("mark")) {
                String[] tokens = userInput.split(" ");
                int i = Integer.parseInt(tokens[1]);
                if (tokens[0].equalsIgnoreCase("mark")) {
                    db.set(i-1, db.get(i-1).mark());

                    String response = "\t____________________________________________________________\n"
                            + "\tNice! I've marked this task as done:\n"
                            + "\t  " + db.get(i-1) + "\n"
                            + "\t____________________________________________________________\n";
                    System.out.println(response);
                } else {
                    db.set(i-1, db.get(i-1).unmark());
                    String response = "\t____________________________________________________________\n"
                            + "\tOk, I've marked this task as not done yet:\n"
                            + "\t  " + db.get(i-1) + "\n"
                            + "\t____________________________________________________________\n";
                    System.out.println(response);
                }
            } else if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else {
                db.add(new Task(userInput));
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
