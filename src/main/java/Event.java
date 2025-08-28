public class Event extends Task{

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from =  from;
        this.to = to;
    }

    public String type() {
        return "E";
    }

    public String fileAdd() {
        String state = super.isStatus() ? "1" : "0";
        return "D | " + state + " | " + getDescription() + " " + this.from + " " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + "to:" + this.to + ")";
    }
}
