package pickle.parser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pickle.exception.PickleException;
import pickle.storage.Storage;
import pickle.task.*;
import pickle.ui.Ui;

/**
 * Parses a single line of command and executes the intended action.
 */
public class Parser {

    private static final DateTimeFormatter IN_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses and executes the intended command.
     * For CLI.
     * @param inputs line typed by the user that is scanned by the ui.
     * @param tasks List of task that Pickle currently has saved.
     * @param ui User interface to print the messages.
     * @param storage storage used to store the list in a text file
     * @return true if the command is "bye" in order to exit the loop in Pickle
     */
    public static boolean parse(String inputs, TaskList tasks, Ui ui, Storage storage) {
        if (inputs.isEmpty()) {
            ui.showError("U did not say anything???");
        }
        String line = inputs.trim();
        String[] input = line.split("\\s+", 2);

        String command = input[0];
        String rests = (input.length > 1) ? input[1] : "";
        try {
            switch (command) {
            case "bye":
                ui.showBye();
                return true;


            case "list":
                ui.showList(tasks.all());
                return false;

            case "mark": {
                if (input.length < 2 || rests.trim().isEmpty()) {
                    throw new PickleException("Please specify which task to mark....");
                }
                Task t = tasks.get(Integer.parseInt(rests));
                t.mark();
                ui.showMarked(t);
                return false;
            }
            case "unmark": {
                if (input.length < 2 || rests.trim().isEmpty()) {
                    throw new PickleException("Please specify which task to unmark....");
                }

                Task t = tasks.get(Integer.parseInt(rests));
                t.unmark();
                ui.showUnmarked(t);
                return false;
            }
            case "delete": {
                if (input.length < 2 || rests.trim().isEmpty() || Integer.parseInt(rests) > tasks.size()) {
                    throw new PickleException("Please specify which task to delete....");
                }

                Task t = tasks.get(Integer.parseInt(rests));
                tasks.delete(Integer.parseInt(rests));
                ui.showTaskDeleted(t, tasks.size());
                return false;
            }
            case "todo": {
                if (input.length < 2 || rests.trim().isEmpty()) {
                    throw new PickleException("There is nothing to do....");
                }
                Task t = new ToDos(rests.trim());
                tasks.add(t);
                ui.showTaskAdded(t, tasks.size());
                return false;
            }
            case "deadline": {
                String[] desc = (rests == null) ? new String[0] : rests.split("\\s*/by\\s*", 2);
                if (desc.length < 2 || desc[0].isBlank() || desc[1].isBlank()) {
                    throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be deadline "
                            + "<description> /by <date>");
                }
                try {
                    LocalDateTime.parse(desc[1].trim(), IN_OUT).withSecond(0).withNano(0);
                } catch (DateTimeParseException ignore) {
                    throw new PickleException("Use format yyyy-MM-dd HHmm, e.g., 2019-12-02 1800");
                }
                Task t = new Deadline(desc[0].trim(), desc[1].trim());
                tasks.add(t);
                ui.showTaskAdded(t, tasks.size());
                return false;
            }
            case "event": {
                if (input.length < 2 || !rests.contains("/from") || !rests.contains("/to") || rests == null) {
                    throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be event "
                            + "<description> /from <time> /to <time>");
                }
                String[] d = input[1].split("\\s*/from\\s*", 2);
                String[] e = d[1].split("\\s*/to\\s*", 2);
                try {
                    LocalDateTime.parse(e[0].trim(), IN_OUT).withSecond(0).withNano(0);
                    LocalDateTime.parse(e[1].trim(), IN_OUT).withSecond(0).withNano(0);
                } catch (DateTimeParseException ignore) {
                    throw new PickleException("Use format yyyy-MM-dd HHmm, e.g., 2019-12-02 1800");
                }
                Task t = new Event(d[0].trim(), e[0].trim(), e[1].trim());
                tasks.add(t);
                ui.showTaskAdded(t, tasks.size());
                return false;
            }
            case "find": {
                if (rests.isBlank()) {
                    throw new PickleException("No keyword to find....");
                }
                String keyword = rests.toLowerCase();
                int i = 1;
                ui.showFind();
                for (Task t : tasks.all()) {
                    if (t.getDescription().toLowerCase().contains(keyword)) {
                        ui.show(i + ". " + t.toString());
                        i++;
                    }

                }
                return false;
            }

            default:
                ui.showError("My bad, I don't know what that means.");
                return false;
            }
        } catch (PickleException e) {
            ui.showError(e.getMessage());
            return false;
        } finally {
            try {
                storage.save(tasks.all());
            } catch (IOException io) {
                System.out.println("ERROR!!" + io.getMessage());
            }

        }
    }

    /**
     * Parses and executes the intended command.
     * For GUI.
     * @param inputs line typed by the user that is scanned by the ui.
     * @param tasks List of task that Pickle currently has saved.
     * @param ui User interface to print the messages.
     * @param storage storage used to store the list in a text file
     * @return true if the command is "bye" in order to exit the loop in Pickle
     */
    public static String parseGui(String inputs, TaskList tasks, Ui ui, Storage storage) {
        if (inputs.isEmpty()) {
            ui.showErrorGui("U did not say anything???");
        }
        String line = inputs.trim();
        String[] input = line.split("\\s+", 2);

        String command = input[0];
        String rests = (input.length > 1) ? input[1] : "";
        try {
            switch (command) {
            case "bye":
                return ui.showByeGui();



            case "list":
                return ui.showListGui(tasks.all());


            case "mark": {
                if (input.length < 2 || rests.trim().isEmpty()) {
                    throw new PickleException("Please specify which task to mark....");
                }
                Task t = tasks.get(Integer.parseInt(rests));
                t.mark();
                return ui.showMarkedGui(t);

            }
            case "unmark": {
                if (input.length < 2 || rests.trim().isEmpty()) {
                    throw new PickleException("Please specify which task to unmark....");
                }

                Task t = tasks.get(Integer.parseInt(rests));
                t.unmark();
                return ui.showUnmarkedGui(t);

            }
            case "delete": {
                if (input.length < 2 || rests.trim().isEmpty() || Integer.parseInt(rests) > tasks.size()) {
                    throw new PickleException("Please specify which task to delete....");
                }

                Task t = tasks.get(Integer.parseInt(rests));
                tasks.delete(Integer.parseInt(rests));
                return ui.showTaskDeletedGui(t, tasks.size());

            }
            case "todo": {
                if (input.length < 2 || rests.trim().isEmpty()) {
                    throw new PickleException("There is nothing to do....");
                }
                Task t = new ToDos(rests.trim());
                tasks.add(t);
                return ui.showTaskAddedGui(t, tasks.size());

            }
            case "fixed": {
                if (input.length < 2 || !rests.contains("/for") || rests == null) {
                    throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be fixed "
                            + "<description> /for <duration>");
                }
                String[] desc = (rests == null) ? new String[0] : rests.split("\\s*/for\\s*", 2);
                if (desc.length < 2 || desc[0].isBlank() || desc[1].isBlank()) {
                    throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be deadline "
                            + "<description> /by <date>");
                }
                Task t = new Fixed(desc[0].trim(), desc[1].trim());
                tasks.add(t);
                return ui.showTaskAddedGui(t, tasks.size());

            }
            case "deadline": {
                String[] desc = (rests == null) ? new String[0] : rests.split("\\s*/by\\s*", 2);
                if (desc.length < 2 || desc[0].isBlank() || desc[1].isBlank()) {
                    throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be deadline "
                            + "<description> /by <date>");
                }
                try {
                    LocalDateTime.parse(desc[1].trim(), IN_OUT).withSecond(0).withNano(0);
                } catch (DateTimeParseException ignore) {
                    throw new PickleException("Use format yyyy-MM-dd HHmm, e.g., 2019-12-02 1800");
                }
                Task t = new Deadline(desc[0].trim(), desc[1].trim());
                tasks.add(t);
                return ui.showTaskAddedGui(t, tasks.size());

            }
            case "event": {
                if (input.length < 2 || !rests.contains("/from") || !rests.contains("/to") || rests == null) {
                    throw new PickleException("I'm sorry, I'm not sure what you meant. Format should be event "
                            + "<description> /from <time> /to <time>");
                }
                String[] d = input[1].split("\\s*/from\\s*", 2);
                String[] e = d[1].split("\\s*/to\\s*", 2);
                try {
                    LocalDateTime.parse(e[0].trim(), IN_OUT).withSecond(0).withNano(0);
                    LocalDateTime.parse(e[1].trim(), IN_OUT).withSecond(0).withNano(0);
                } catch (DateTimeParseException ignore) {
                    throw new PickleException("Use format yyyy-MM-dd HHmm, e.g., 2019-12-02 1800");
                }
                Task t = new Event(d[0].trim(), e[0].trim(), e[1].trim());
                tasks.add(t);
                return ui.showTaskAddedGui(t, tasks.size());

            }
            case "find": {
                if (rests.isBlank()) {
                    throw new PickleException("No keyword to find....");
                }
                String keyword = rests.toLowerCase();
                int i = 1;
                String output = ui.showFindGui() + "\n";
                for (Task t : tasks.all()) {
                    if (t.getDescription().toLowerCase().contains(keyword)) {
                        output = output + ui.showGui(i + ". " + t.toString() + "\n");
                        i++;
                    }


                }
                return output;
            }

            default:
                return ui.showErrorGui("My bad, I don't know what that means.");

            }
        } catch (PickleException e) {
            return ui.showErrorGui(e.getMessage());

        } finally {
            try {
                storage.save(tasks.all());
            } catch (IOException io) {
                return "ERROR!!" + io.getMessage();
            }

        }
    }
}


