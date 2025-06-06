package telran.wrapper.controller;

public class CalculatorAppl {

    public static void main(String[] args) {
        if (args.length != 3){
            System.out.println("Wrong number of arguments!");
            return;
        }

        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[2]);
        char oper = args[1].charAt(0);

//        if (oper == '/' && b == 0){
//            System.out.println("Infinity");
//            return;
//        }

        double res = calculator(a, oper, b);
        System.out.println("Result = " + res);

    }

    public static double calculator(double a, char oper, double b) {
        return switch (oper) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            case '%' -> a % b;
            default -> 0.;
        };
    }
}
