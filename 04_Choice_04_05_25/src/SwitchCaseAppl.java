// + - * / % ^

public class SwitchCaseAppl {
    public static void main(String[] args){
//        fan3(5);
        double res = calculator(4, 2, 21);
        System.out.println("Result = " + res);
    }

    public static double calculator(double a, double b, int operator){
        return switch (operator) {
            case 1, 11, 21, 31 -> a + b;
            case 2 -> a - b;
            case 3 -> a * b;
            case 4 -> a / b;
            default -> {
                System.out.println("Wrong operation");
                yield Double.NaN;
            }
        };
    }

    public static void fan1(int mode){
        if (mode == 0)
            System.out.println("Stop");
        else if (mode == 1)
            System.out.println("Slow");
        else if (mode == 2)
            System.out.println("Medium");
        else if (mode == 3)
            System.out.println("Fast");
        else
            System.out.println("Wrong mode");
    }

    public static void fan2(int mode){
        if (mode == 0){
            System.out.println("Stop");
            return;
        }
        if (mode == 1){
            System.out.println("Slow");
            return;
        }
        if (mode == 2) {
            System.out.println("Medium");
            return;
        }
        if (mode == 3) {
            System.out.println("Fast");
            return;
        }
        System.out.println("Wrong mode");
    }

    public static void fan3(int mode){
        switch (mode){
            case 0:
                System.out.println("Stop");
                break;
            case 1:
                System.out.println("Slow");
                break;
            case 2:
                System.out.println("Medium");
                break;
            case 3:
                System.out.println("Fast");
                break;
            default:
                System.out.println("Wrong mode");
        }
        System.out.println("End of the function fan3");
    }
}
