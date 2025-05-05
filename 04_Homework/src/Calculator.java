public class Calculator {
    public static void main(String[] args) {
        double num = calculate(5, 2.5,'^');
        System.out.println(num);
    }

    public static double calculate(double a, double b, char operator) {
        double res =  switch (operator) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '%' -> a % b;
            case '^' -> Math.pow(a, b);
            default -> {
                System.out.println("Wrong operation");
                yield Double.NaN;
            }
        };
        return res;
    }
}
