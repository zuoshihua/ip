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

        String commands = "Commands: bye, list, mark, unmark, todo, deadline, event";

        String userInput = "";
        while (true) {
            try {
                userInput = sc.nextLine();
                if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tHere are the tasks in your list:");
                    for (Task s : db) {
                        System.out.printf("\t%d.%s\n", db.indexOf(s) + 1, s);
                    }
                    System.out.println("\t____________________________________________________________\n");
                } else if (userInput.contains("mark")) {
                    String[] tokens = userInput.split(" ");
                    int i;
                    if (tokens.length < 2)
                        throw new TundraException("Incorrect syntax. Usage: mark [number] || unmark [number]");
                    try {
                       i = Integer.parseInt(tokens[1]);
                    } catch (NumberFormatException e) {
                        throw new TundraException("Incorrect syntax. Usage: mark [number] || unmark [number]");
                    }
                    if (tokens[0].equalsIgnoreCase("mark")) {
                        Task t;
                        try {
                            t = db.get(i - 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new TundraException("Invalid request. Enter `list` to see available tasks");
                        }
                        t.setCompleted(true);
                        db.set(i - 1, t);

                        String response = "\t____________________________________________________________\n"
                                + "\tNice! I've marked this task as done:\n"
                                + "\t  " + db.get(i - 1) + "\n"
                                + "\t____________________________________________________________\n";
                        System.out.println(response);
                    } else if (tokens[0].equalsIgnoreCase("unmark")) {
                        Task t;
                        try {
                            t = db.get(i - 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new TundraException("Invalid request. Enter `list` to see available tasks");
                        }
                        t.setCompleted(false);
                        db.set(i - 1, t);
                        String response = "\t____________________________________________________________\n"
                                + "\tOk, I've marked this task as not done yet:\n"
                                + "\t  " + db.get(i - 1) + "\n"
                                + "\t____________________________________________________________\n";
                        System.out.println(response);
                    }
                    else throw new TundraException("Unrecognized command." + commands);
                } else if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    String[] tokens = userInput.split(" ", 2);
                    Task t = null;
                    switch (tokens[0]) {
                        case "todo":
                            if (tokens.length < 2)
                                throw new TundraException("Incorrect syntax. Usage: todo [description]");
                            t = new Todo(tokens[1]);
                            db.add(t);
                            break;
                        case "deadline":
                            String[] dtokens = tokens[1].split(" /by ");
                            if (dtokens.length < 2)
                                throw new TundraException("Incorrect syntax. Usage: deadline [description] /by [date]");
                            t = new Deadline(dtokens[0], dtokens[1]);
                            db.add(t);
                            break;
                        case "event":
                            String[] etokens = tokens[1].split(" /from ");
                            if (etokens.length < 2)
                                throw new TundraException("Incorrect syntax. Usage: event [description] /from [date] /to [date]");
                            String name = etokens[0];
                            String duration = etokens[1];
                            String[] etokens2 = duration.split(" /to ");
                            if (etokens2.length < 2)
                                throw new TundraException("Incorrect syntax. Usage: event [description] /from [date] /to [date]");
                            String from = etokens2[0];
                            String to = etokens2[1];
                            t = new Event(name, from, to);
                            db.add(t);
                            break;
                            default:
                                throw new TundraException("Unrecognized command.\n\t" + commands);
                    }
                    String response = "\t____________________________________________________________\n"
                            + "\tGot it. I've added this task:\n"
                            + "\t " + t + "\n"
                            + "\tNow you have " + db.size() + " tasks in the list.\n"
                            + "\t____________________________________________________________\n";
                    System.out.println(response);
                }
            } catch (TundraException e) {
                System.out.println(e.getMessage());
            }
        }

        String goodbye = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";
        System.out.println(goodbye);
    }
}
