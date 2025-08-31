package pickle.chat;

import pickle.ui.Ui;
import pickle.storage.Storage;
import pickle.task.TaskList;
import pickle.parser.Parser;

import java.io.IOException;

/**
 * Start of Pickle Chatbot
 * Pickle chatbot allows users to add, remove, mark and unmark tasks to do in a list
 * Such tasks include, todos, deadlines and events.
 */
public class Pickle {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private static String line = "________________________________________________";

    /**
     * Creates a new instance of the Pickle application.
     *
     * @param filePath the file path where all tasks are save into and loaded from.
     */
    public Pickle(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException io) {
            System.out.println("ERROR" + io.getMessage());
            this.tasks = new TaskList();
        }

    }

    /**
     * Runs the main body of the Pickle Chatbot.
     * Creates a loop that constantly accepts inputs by the user.
     *
     */
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

    /**
     * Begins the Pickle Chatbot application.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Pickle("data/Pickle.txt").run();
    }
}