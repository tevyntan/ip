package pickle.task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> all() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public Task delete(int value) {
        return tasks.remove(value - 1);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task get(int value) {
        return tasks.get(value - 1);
    }



}
