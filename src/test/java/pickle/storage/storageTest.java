package pickle.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import pickle.task.*;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @TempDir Path tmp;

    @Test
    void save_then_load_restoresTasks() throws Exception {
        Storage storage = new Storage(tmp.resolve("tasks.txt").toString());

        TaskList list = new TaskList();
        list.add(new ToDos("read"));
        list.add(new Deadline("return book", "2019-12-04 1800"));


        storage.save(list.all());
        TaskList loaded = new TaskList(storage.load());

        assertEquals(list.size(), loaded.size());
        assertTrue(loaded.get(1).toString().contains("read"));
        assertEquals(list.get(2).toString(), loaded.get(2).toString()); //compare add and loaded after save is same

    }

    @Test
    void load_fileMissing() throws Exception {
        Storage storage = new Storage(tmp.resolve("empty.txt").toString());
        TaskList loaded = new TaskList(storage.load());
        assertEquals(0, loaded.size());
    }
}