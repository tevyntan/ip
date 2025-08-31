package pickle.task;

public abstract class Task {
    private String description;
    private boolean isComplete;

    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public void mark() {
        isComplete = true;
    }

    public void unmark() {
        isComplete = false;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public String getDescription() {
        return this.description;
    }

    public String type() {
        return " ";
    }

    public abstract String fileAdd();

    @Override
    public String toString() {
        String symbol = " ";
        if (isComplete) {
            symbol = "X";
        }
        return "[" + symbol + "] " + description;
    }
}
