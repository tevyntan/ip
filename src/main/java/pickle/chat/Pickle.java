package pickle.chat;

import pickle.ui.Ui;
import pickle.storage.Storage;
import pickle.task.TaskList;
import pickle.parser.Parser;

import java.io.IOException;

public class Pickle {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;
    private static String line = "________________________________________________";

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
}