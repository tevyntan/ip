package pickle.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import pickle.task.Deadline;
import pickle.task.Event;
import pickle.task.Fixed;
import pickle.task.Task;
import pickle.task.ToDos;



/**
 * Saves the list of tasks into a text file.
 * Loads the list of tasks by an existing text file to keep track of tasks that the user has to do.
 */
public class Storage {

    private final Path fileLocation;

    /**
     * Creates and instance of the storage.
     * @param filePath the file path used to store the list of tasks.
     */
    public Storage(String filePath) {
        this.fileLocation = Paths.get(filePath);

    }

    /**
     * Saves the given tasks list into the disk via a text file.
     * @param list tasks to save.
     * @throws IOException if the saving fails.
     */
    public void save(ArrayList<Task> list) throws IOException {
        Files.createDirectories(Paths.get("data"));

        List<String> lists = new ArrayList<>();

        for (Task t : list) {
            String line = t.writeToFile();
            lists.add(line);
        }

        Files.write(fileLocation, lists, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    /**
     * Load the list of tasks from a pre-existing text file.
     * Return empty list if text file does not exist.
     * @return tasks loaded from the disk.
     * @throws IOException if reading the file fails.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> empty = new ArrayList<>();
        Files.createDirectories(fileLocation.getParent());

        if (!Files.exists(fileLocation)) {
            Files.write(fileLocation, empty, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return tasks;
        }

        for (String line : Files.readAllLines(fileLocation)) {
            if (line == null || line.isBlank()) {
                continue;
            }

            String[] parts = line.split("\\s*\\|\\s*");

            //Check if any line read is at least long enough to be valid.
            if (parts.length < 3) {
                continue;
            }
            String type = parts[0];
            boolean done = parts[1].equals("1");

            switch(type) {
            case "T": {
                Task t = new ToDos(parts[2]);
                if (done) {
                    t.mark();
                }
                tasks.add(t);
                break;
            }

            case "D": {
                Task t = new Deadline(parts[2], parts[3]);
                if (done) {
                    t.mark();
                }
                tasks.add(t);
                break;
            }

            case "E": {
                Task t = new Event(parts[2], parts[3], parts[4]);
                if (done) {
                    t.mark();
                }
                tasks.add(t);
                break;
            }
            case "F": {
                Task t = new Fixed(parts[2], parts[3]);
                if (done) {
                    t.mark();
                }
                tasks.add(t);
                break;
            }
            default:
                break;
            }
        }
        return tasks;
    }
}
