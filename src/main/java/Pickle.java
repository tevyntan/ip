import java.util.*;

public class Pickle {
    private static String line = "________________________________________________";
    public static void main(String[] args) {
        String[] list = new String[100];
        Scanner sc = new Scanner(System.in);
        int counter = 0;

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
            } else if (input.equals("list")) {
                System.out.println(line);
                int count = 1;
                for (int i = 0; i < counter; i++){
                    System.out.println(count + ". " + list[count - 1]);
                    count++;
                }


                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + input);
                list[counter] = input;
                counter++;
                System.out.println(line);
            }

         }


    }
}
