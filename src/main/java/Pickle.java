import java.util.*;

public class Pickle {
    private static String line = "________________________________________________";
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int counter = 0;

        System.out.println(line);
        System.out.println("Hello! I'm Pickle");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while(sc.hasNextLine()) {
            String inputs = sc.nextLine();
            String[] input = inputs.split(" ",2);


            if (input[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input[0].equals("list")) {
                System.out.println(line);
                int count = 1;
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(count + "." + tasks.get(count - 1).toString());
                    count++;
                }
                System.out.println(line);

            } else if (input[0].equals("mark")) {
                System.out.println(line);
                Task t = tasks.get(Integer.parseInt(input[1]) - 1);
                t.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t.toString());
                System.out.println(line);
            } else if (input[0].equals("unmark")) {
                System.out.println(line);
                Task t = tasks.get(Integer.parseInt(input[1]) - 1);
                t.unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t.toString());
                System.out.println(line);
            } else if (input[0].equals("todo")) {
                System.out.println(line);
                counter++;
                System.out.println("Aights. Task added:");
                Task t = new ToDos(input[1]);
                tasks.add(t);
                System.out.println(t.toString());
                System.out.println("You got " + counter + " tasks in the list.");
                System.out.println(line);
            } else if (input[0].equals("deadline")) {
                System.out.println(line);
                counter++;
                System.out.println("Aights. Task added:");
                String[] d = input[1].split("/by");
                Task t = new Deadline(d[0], d[1]);
                tasks.add(t);
                System.out.println(t.toString());
                System.out.println("You got " + counter + " tasks in the list.");
                System.out.println(line);
            } else if (input[0].equals("event")) {
                System.out.println(line);
                counter++;
                System.out.println("Aights. Task added:");
                String[] d = input[1].split("/from");
                String[] e = d[1].split("/to");
                Task t = new Event(d[0], e[0], e[1]);
                tasks.add(t);
                System.out.println(t.toString());
                System.out.println("You got " + counter + " tasks in the list.");
                System.out.println(line);
            } else {
                System.out.println(line);
                System.out.println("added: " + inputs);
                Task t = new Task(inputs);
                tasks.add(t);
                counter++;
                System.out.println(line);
                }

         }


    }
}
