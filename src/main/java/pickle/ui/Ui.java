package pickle.ui;

import pickle.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interface inputs and outputs for Pickle.
 */
public class Ui {
    private final Scanner sc = new Scanner(System.in);
    private static String line = "________________________________________________";

    /**
     * Using scanner to read the command given by the user.
     * @return
     */
    public String readComment() {
        return sc.nextLine().trim();
    }

    /**
     * Prints a greeting when Pickle is first ran.
     */
    public void showWelcome() {
        System.out.println(line);
        System.out.println("Good Morning! I'm Pickle");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }

    /**
     * Prints a line to seperate each command.
     */
    public void showLine() {
        System.out.println(line);
    }

    /**
     * Prints a goodbye message when the user terminates Pickle.
     */
    public void showBye() {
        System.out.println("Bye. Hope you have a nice day!");

    }

    public void showFind() {
        System.out.println("Is this what u are looking for??");
    }

    public void show(String msg) {
        System.out.println(msg);
    }
    public void showAdded(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints out an error message due to invalid input.
     * @param msg Specific message tied to each error.
     */
    public void showError(String msg) {
        System.out.println("Yikes!!!" + msg + "  Try Again!!");
    }

    /**
     * Prints the list of tasks.
     * @param tasks List of tasks.
     */
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

    /**
     * Prints the task that is added.
     * @param t Task added.
     * @param size Size of list of task.
     */
    public void showTaskAdded(Task t, int size) {

        System.out.println("Aights. pickle.task.Task added:");
        System.out.println(t.toString());
        System.out.println("You got " + size + " tasks in the list.");

    }

    /**
     * Prints the task deleted.
     * @param t Task deleted.
     * @param size Size of list of tasks.
     */
    public void showTaskDeleted(Task t, int size) {

        System.out.println("Ok! I've removed this task:");
        System.out.println(t.toString());
        System.out.println("You got " + size + " tasks in the list.");

    }

    /**
     * Print task that is marked.
     * @param t Task to be marked.
     */
    public void showMarked(Task t) {

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.toString());

    }

    /**
     * Print task that is unmarked.
     * @param t Task to be unmarked.
     */
    public void showUnmarked(Task t) {

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());

    }
}

