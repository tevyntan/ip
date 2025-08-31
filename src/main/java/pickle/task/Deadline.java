package pickle.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{

    private LocalDateTime date;
    // SINGLE accepted input/storage format: "yyyy-MM-dd HHmm" (e.g., 2019-12-02 1800)
    private static final DateTimeFormatter IN_OUT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // Display formats
    private static final DateTimeFormatter OUT_DATE = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter OUT_TIME = DateTimeFormatter.ofPattern("h:mma");

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }
    public Deadline(String description, String date) {
        super(description);

        try {
            this.date = LocalDateTime.parse(date.trim(), IN_OUT).withSecond(0).withNano(0);
        } catch (DateTimeParseException ignore) {
        }
    }

    public String type() {
        return "D";
    }

    public String fileAdd() {

        String state = super.isStatus() ? "1" : "0";
        return "D | " + state + " | " + getDescription().trim() + " | " + this.date.format(IN_OUT);
    }

    @Override
    public String toString() {
        String output = date.toLocalDate().format(OUT_DATE) + ", " + date.toLocalTime().format(OUT_TIME);
        return "[D]" + super.toString() + " (by: " + output + ")";
    }
}
