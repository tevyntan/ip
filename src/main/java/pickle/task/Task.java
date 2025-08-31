package pickle.task;

public abstract class Task {
    private String description;
    private boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void mark() {
        status = true;
    }

    public void unmark() {
        status = false;
    }

    public boolean isStatus() {
        return this.status;
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
        if (status) {
            symbol = "X";
        }
        return "[" + symbol + "] " + description;
    }
}
