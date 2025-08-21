public class Task {
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

    @Override
    public String toString() {
        String symbol = " ";
        if (status) {
            symbol = "X";
        }
        return "[" + symbol + "] " + description;
    }
}
