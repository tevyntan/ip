package pickle.task;

/**
 * Represent a generic task.
 * Abstract class to serve as a base that all other task inherit from.
 */
public abstract class Task {
    private String description;
    private boolean status;

    /**
     * Creates a new task with the given description.
     * @param description information of task.
     */
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        status = true;
    }

    /**
     * Marks the task as not yet done.
     */
    public void unmark() {
        status = false;
    }

    /**
     * shows the status of whether it is completed.
     * @return true if task is completed.
     */
    public boolean isStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public String type() {
        return " ";
    }

    /**
     * Base case of how to add the task to the storage file.
     * @return a string of how it should be stored.
     */
    public abstract String fileAdd();

    @Override
    public String toString() {
        String symbol = " ";
        if (status) {
            symbol = "X";
        }
        return "[" + symbol + "] " + description;
    }
}
