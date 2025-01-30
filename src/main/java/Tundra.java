import tundra.exceptions.TundraException;
import tundra.models.Deadline;
import tundra.models.Event;
import tundra.models.Task;
import tundra.models.Todo;
import tundra.utils.Storage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tundra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String welcome = "\t____________________________________________________________\n"
                + "\tHello! I'm Tundra\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";
        System.out.println(welcome);

        Storage db = new Storage("./data/tundra.txt");
        int status = db.load(tasks);

        if (status < 0)
            throw new TundraException("Failed to load all tasks");

        if (status > 0)
            throw new TundraException("Failed to load " + status + " task(s)");

        String commands = "Commands: bye, list, delete, mark, unmark, todo, deadline, event";

        String userInput = "";
        while (true) {
            try {
                userInput = sc.nextLine();
                if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\tHere are the tasks in your list:");
                    for (Task s : tasks) {
                        System.out.printf("\t%d.%s\n", tasks.indexOf(s) + 1, s);
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
                            t = tasks.get(i - 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new TundraException("Invalid request. Enter `list` to see available tasks");
                        }
                        t.setCompleted(true);
                        tasks.set(i - 1, t);
                        String response = "\t____________________________________________________________\n"
                                + "\tNice! I've marked this task as done:\n"
                                + "\t  " + tasks.get(i - 1) + "\n"
                                + "\t____________________________________________________________\n";
                        System.out.println(response);
                    } else if (tokens[0].equalsIgnoreCase("unmark")) {
                        Task t;
                        try {
                            t = tasks.get(i - 1);
                        } catch (IndexOutOfBoundsException e) {
                            throw new TundraException("Invalid request. Enter `list` to see available tasks");
                        }
                        t.setCompleted(false);
                        tasks.set(i - 1, t);
                        String response = "\t____________________________________________________________\n"
                                + "\tOk, I've marked this task as not done yet:\n"
                                + "\t  " + tasks.get(i - 1) + "\n"
                                + "\t____________________________________________________________\n";
                        System.out.println(response);
                    } else throw new TundraException("Unrecognized command." + commands);
                } else if (userInput.contains("delete")) {
                    String[] tokens = userInput.split(" ");
                    int i;
                    if (tokens.length < 2)
                        throw new TundraException("Incorrect syntax. Usage: delete [number]");
                    try {
                        i = Integer.parseInt(tokens[1]);
                    } catch (NumberFormatException e) {
                        throw new TundraException("Incorrect syntax. Usage: delete [number]");
                    }
                    Task t;
                    try {
                        t = tasks.get(i - 1);
                        tasks.remove(i - 1);
                    } catch (IndexOutOfBoundsException e) {
                        throw new TundraException("Invalid request. Enter `list` to see available tasks");
                    }
                    String response = "\t____________________________________________________________\n"
                            + "\tNoted. I've removed this task:\n"
                            + "\t  " + t + "\n"
                            + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                            + "\t____________________________________________________________\n";
                    System.out.println(response);
                } else if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    String[] tokens = userInput.split(" ", 2);
                    Task t = null;
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                    switch (tokens[0]) {
                        case "todo":
                            if (tokens.length < 2)
                                throw new TundraException("Incorrect syntax. Usage: todo [description]");
                            t = new Todo(tokens[1]);
                            tasks.add(t);
                            break;
                        case "deadline":
                            String[] dtokens = tokens[1].split(" /by ");
                            if (dtokens.length < 2)
                                throw new TundraException("Incorrect syntax. Usage: deadline [description] /by [date]");
                            try {
                                LocalDateTime by = LocalDateTime.parse(dtokens[1], fmt);
                                t = new Deadline(dtokens[0], by);
                            } catch (DateTimeParseException e) {
                                throw new TundraException("Incorrect date and time format. Example: 2019-10-15 1800");
                            }
                            tasks.add(t);
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
                            try {
                                LocalDateTime from = LocalDateTime.parse(etokens2[0], fmt);
                                LocalDateTime to = LocalDateTime.parse(etokens2[1], fmt);
                                t = new Event(name, from, to);
                            } catch (DateTimeParseException e) {
                                throw new TundraException("Incorrect date and time format. Example: 2019-10-15 1800");
                            }
                            tasks.add(t);
                            break;
                            default:
                                throw new TundraException("Unrecognized command.\n\t" + commands);
                    }
                    String response = "\t____________________________________________________________\n"
                            + "\tGot it. I've added this task:\n"
                            + "\t " + t + "\n"
                            + "\tNow you have " + tasks.size() + " tasks in the list.\n"
                            + "\t____________________________________________________________\n";
                    System.out.println(response);
                }
            } catch (TundraException e) {
                System.out.println(e.getMessage());
            }
        }

        db.save(tasks);

        String goodbye = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";
        System.out.println(goodbye);
    }
}
