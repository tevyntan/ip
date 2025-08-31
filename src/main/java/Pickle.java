import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Pickle {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private static String line = "________________________________________________";

    public Pickle(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage();

        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException io) {
            System.out.println("ERROR" + io.getMessage());
            this.tasks = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {

            String input = ui.readComment();
            try {
                ui.showLine();
                isExit = Parser.parse(input, tasks, ui, storage);
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Pickle("data/Pickle.txt").run();
    }
//    private static final DateTimeFormatter IN_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
//    private static String line = "________________________________________________";
//    public static void main(String[] args) {
//        Storage storage = new Storage();
//
//        ArrayList<Task> tasks = new ArrayList<>();
//
//        try {
//            tasks = storage.load();
//        } catch (IOException io) {
//            System.out.println("ERROR" + io.getMessage());
//        }
//
//        Scanner sc = new Scanner(System.in);
//        int counter = tasks.size();
//
//        System.out.println(line);
//        System.out.println("Good Morning! I'm Pickle");
//        System.out.println("What can I do for you?");
//        System.out.println(line);
//
//        while(sc.hasNextLine()) {
//            String inputs = sc.nextLine();
//            String[] input = inputs.split(" ",2);
//
//            try {
//                if (input[0].equals("bye")) {
//                    System.out.println("Bye. Hope you have a nice day!");
//                    System.out.println(line);
//                    break;
//                } else if (input[0].equals("list")) {
//                    if (tasks.isEmpty()) {
//                        throw new PickleException("Your list is empty. ");
//                    }
//                    System.out.println(line);
//                    System.out.println("Here is your list:");
//                    int count = 1;
//                    for (int i = 0; i < tasks.size(); i++) {
//                        System.out.println(count + "." + tasks.get(count - 1).toString());
//                        count++;
//                    }
//                    System.out.println(line);
//
//                } else if (input[0].equals("mark")) {
//                    if (input.length < 2 || input[1].trim().isEmpty()) {
//                        throw new PickleException("Please specify which task to mark....");
//                    }
//                    System.out.println(line);
//                    Task t = tasks.get(Integer.parseInt(input[1]) - 1);
//                    t.mark();
//                    System.out.println("Nice! I've marked this task as done:");
//                    System.out.println(t.toString());
//                    System.out.println(line);
//                } else if (input[0].equals("unmark")) {
//                    if (input.length < 2 || input[1].trim().isEmpty()) {
//                        throw new PickleException("Please specify which task to unmark....");
//                    }
//                    System.out.println(line);
//                    Task t = tasks.get(Integer.parseInt(input[1]) - 1);
//                    t.unmark();
//                    System.out.println("OK, I've marked this task as not done yet:");
//                    System.out.println(t.toString());
//                    System.out.println(line);
//                } else if (input[0].equals("delete")) {
//                    if (input.length < 2 || input[1].trim().isEmpty() || Integer.parseInt(input[1]) > counter) {
//                        throw new PickleException("Please specify which task to delete....");
//                    }
//                    System.out.println(line);
//                    System.out.println("Ok! I've removed this task:");
//                    Task t =  tasks.get(Integer.parseInt(input[1]) -1);
//                    System.out.println(t.toString());
//                    tasks.remove(Integer.parseInt(input[1]) -1);
//                    counter--;
//                    System.out.println("You got " + counter + " tasks in the list.");
//                    System.out.println(line);
//                } else if (input[0].equals("todo")) {
//                    if (input.length < 2 || input[1].trim().isEmpty()) {
//                        throw new PickleException("There is nothing to do....");
//                    }
//                    System.out.println(line);
//                    counter++;
//                    System.out.println("Aights. Task added:");
//                    Task t = new ToDos(input[1]);
//                    tasks.add(t);
//                    System.out.println(t.toString());
//                    System.out.println("You got " + counter + " tasks in the list.");
//                    System.out.println(line);
//                } else if (input[0].equals("deadline")) {
//                    if (input.length < 2 || !input[1].contains("/by")) {
//                        throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be deadline " +
//                                "<description> /by <date>");
//                    }
//
//                    String[] d = input[1].split("/by");
//                    try {
//                        LocalDateTime.parse(d[1].trim(), IN_OUT).withSecond(0).withNano(0);
//                    } catch (DateTimeParseException ignore) {
//                        throw new PickleException("Use format yyyy-MM-dd HHmm, e.g., 2019-12-02 1800");
//                    }
//                    Task t = new Deadline(d[0], d[1]);
//                    tasks.add(t);
//                    counter++;
//                    System.out.println(line);
//                    System.out.println(t.toString());
//                    System.out.println("Aights. Task added:");
//                    System.out.println("You got " + counter + " tasks in the list.");
//                    System.out.println(line);
//                } else if (input[0].equals("event")) {
//                    if (input.length < 2 || !input[1].contains("/from") || !input[1].contains("/to")) {
//                        throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be event " +
//                                "<description> /from <time> /to <time>");
//                    }
//                    String[] d = input[1].split("/from");
//                    String[] e = d[1].split("/to");
//                    try {
//                        LocalDateTime.parse(e[0].trim(), IN_OUT).withSecond(0).withNano(0);
//                        LocalDateTime.parse(e[1].trim(), IN_OUT).withSecond(0).withNano(0);
//                    } catch (DateTimeParseException ignore) {
//                        throw new PickleException("Use format yyyy-MM-dd HHmm, e.g., 2019-12-02 1800");
//                    }
//                    System.out.println(line);
//                    counter++;
//                    System.out.println("Aights. Task added:");
//
//                    Task t = new Event(d[0], e[0], e[1]);
//                    tasks.add(t);
//                    System.out.println(t.toString());
//                    System.out.println("You got " + counter + " tasks in the list.");
//                    System.out.println(line);
//                } else {
//                    throw new PickleException("My bad, I don't know what that means.");
//                }
//            } catch (PickleException e) {
//                System.out.println(line);
//                System.out.println("Yikes!!! " + e.getMessage() + " Try Again!!!");
//                System.out.println(line);
//            } finally {
//                try {
//                    storage.save(tasks);
//                } catch (IOException io) {
//                    System.out.println("ERROR!!" + io.getMessage());
//                }
//
//            }
//         }
//
//
//    }
}
