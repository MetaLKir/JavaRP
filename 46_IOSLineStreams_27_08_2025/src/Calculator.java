import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = readInt(sc, "Enter first number: >> ");
        int b = readInt(sc, "Enter second number: >> ");
        System.out.println("Enter operator + - / * >> ");
        String op = sc.nextLine().trim();
        if (op.length() != 1)
            throw new IllegalArgumentException("bad operator");

        try {
            double res = calc(a, b, op);
            System.out.println("Result = " + res);
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: unknown operator " + e.getMessage());
        }
        sc.close();
    }


    private static double calc(int a, int b, String op) {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("division by zero");
                yield (double) a / b;
            }
            default -> throw new IllegalArgumentException("bad operator " + op);
        };
    }

    private static int readInt(Scanner sc, String string) {
        while (true) {
            System.out.println(string);
            String s = sc.nextLine().trim();

            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e){
                System.out.println("Not an integer, try again");
            }
        }
    }
}
