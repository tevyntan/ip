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
}
