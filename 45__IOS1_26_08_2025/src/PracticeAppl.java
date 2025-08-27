import java.util.Scanner;

public class PracticeAppl {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int res = 0;
        int a;
        int b;
        String oper;

        while(true){
            try {
                System.out.println("Enter first number: ");
                a = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter second number: ");
                b = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter operation: + - * /");
                oper = scanner.nextLine();
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Incorrect number");
            }
        }

        switch (oper) {
            case "+" -> res = a + b;
            case "-" -> res = a - b;
            case "*" -> res = a * b;
            case "/" -> res = a / b;
            default -> System.out.println("Some wrong input");
        }

        System.out.println("Result: " + res);
        scanner.close();
    }
}
