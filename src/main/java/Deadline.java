public class Deadline extends Task{

    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public String type() {
        return "D";
    }

    public String fileAdd() {
        String state = super.isStatus() ? "1" : "0";
        return "D | " + state + " | " + getDescription() + " | " + this.date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + date + ")";
    }
}
