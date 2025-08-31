import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private static String line = "________________________________________________";

    public String readComment() {
        return sc.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println(line);
        System.out.println("Good Morning! I'm Pickle");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showBye() {
        System.out.println("Bye. Hope you have a nice day!");

    }

    public void show(String msg) {
        System.out.println(msg);
    }
    public void showAdded(String msg) {
        System.out.println(msg);
    }

    public void showError(String msg) {
        System.out.println("Yikes!!!" + msg + "  Try Again!!");
    }

    public void showList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("List empty! There is nothing to do....");
            return;
        }
        System.out.println("Here is your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showTaskAdded(Task t, int size) {

        System.out.println("Aights. Task added:");
        System.out.println(t.toString());
        System.out.println("You got " + size + " tasks in the list.");

    }

    public void showTaskDeleted(Task t, int size) {

        System.out.println("Ok! I've removed this task:");
        System.out.println(t.toString());
        System.out.println("You got " + size + " tasks in the list.");

    }

    public void showMarked(Task t) {

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());

    }

    public void showUnmarked(Task t) {

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());

    }
}

