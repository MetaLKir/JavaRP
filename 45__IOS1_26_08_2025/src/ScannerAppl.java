import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScannerAppl {
    public static void main(String[] args) {
        String name;
        Scanner scanner = new Scanner(System.in);
//        System.out.println("enter your name please >>> ");
//        name = scanner.nextLine();
//        System.out.println("Your name is " + name);
//
//        scanner.close();

//        while (true) {
//            System.out.println("enter some text or exit to quit");
//            String text = scanner.nextLine();
//            if(text.equalsIgnoreCase("exit")) break;
//            System.out.println("You wrote " + text);
//        }

        List<String> names = new ArrayList<>();
        while (true) {
            System.out.println("enter some text or exit to quit");
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("exit")) break;
            names.add(text);
        }
        System.out.println(names);

        scanner.close();
    }
}
