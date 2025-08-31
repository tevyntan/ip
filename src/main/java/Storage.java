import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final Path FILE_LOCATION = Paths.get("data", "Pickle.txt");

    public Storage(){
    }

    public void save(ArrayList<Task> list) throws IOException {
        Files.createDirectories(Paths.get("data"));

        List<String> lists = new ArrayList<>();

        for (Task t : list) {
            String line = t.fileAdd();
            lists.add(line);
        }

        Files.write(FILE_LOCATION, lists, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> empty = new ArrayList<>();
        Files.createDirectories(FILE_LOCATION.getParent());

        if (!Files.exists(FILE_LOCATION)) {
            Files.write(FILE_LOCATION, empty, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return tasks;
        }

        for (String line : Files.readAllLines(FILE_LOCATION)) {
            if (line == null || line.isBlank()) {
                continue;
            }

            String[] parts = line.split("\\s*\\|\\s*");

            String type = parts[0];
            boolean done = parts[1].equals("1");

            switch(type) {
                case "T" : {
                    Task t = new ToDos(parts[2]);
                    if (done) {
                        t.mark();
                    }
                    tasks.add(t);
                    break;
                }

                case "D" : {
                    Task t = new Deadline(parts[2],parts[3]);
                    if (done) {
                        t.mark();
                    }
                    tasks.add(t);
                    break;
                }

                case "E" : {
                    Task t = new Event(parts[2], parts[3], parts[4]);
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
