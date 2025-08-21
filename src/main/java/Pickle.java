import java.util.*;

public class Pickle {
    private static String line = "________________________________________________";
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! I'm Pickle");
        System.out.println("What can I do for you?\n");
        System.out.println(line);

        while(sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else {
                System.out.println(line);
                System.out.println(input);
                System.out.println(line);
            }

         }


    }
}
