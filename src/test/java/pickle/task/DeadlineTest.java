package pickle.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class DeadlineTest {

    @Test
    void checkDateTimeFormat() {
        //Create new deadline
        Deadline d = new Deadline("return book", "2019-12-04 1800");
        Deadline e = new Deadline("movies", "2019-12-04 2100");
        //check if the date is converted when toString() called to print
        assertTrue(d.toString().contains("Dec 4 2019, 6:00pm"));
        assertTrue(e.toString().contains("Dec 4 2019, 9:00pm"));


        // fileAdd should give the same as what is inputted so that can load safely
        String saved = d.fileAdd();
        assertTrue(saved.endsWith("2019-12-04 1800"));
    }

}