package pickle.task;

public class ToDos extends Task{

    public ToDos(String description) {
        super(description);
    }

    public String type() {
        return "T";
    }

    public String fileAdd() {
        String state = super.isComplete() ? "1" : "0";
        return "T | " + state + " | " + getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
