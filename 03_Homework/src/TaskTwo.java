/* TODO:
    Напишите программу, которая запрашивает у пользователя три числа.
    Выведите на экран фразу "Сумма трёх чисел положительная и чётная", если это так.
    Для всех остальных случаев нужно просто выводить сумму трёх чисел на экран.
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskTwo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double first, second, third;

        System.out.println("I help you check if sum of 3 numbers positive and even number.");
        System.out.print("Enter the first number: ");
        first = readInputDouble(scanner);
        System.out.print("Enter the second number: ");
        second = readInputDouble(scanner);
        System.out.print("Enter the third number: ");
        third = readInputDouble(scanner);

        sumOfThreeNumbers(first, second, third);
    }
    //===============================================================//

    static void sumOfThreeNumbers(double a, double b, double c) {
        double res = a + b + c;
        if (res > 0 && res % 2 == 0)
            System.out.println("Сумма трёх чисел положительная и чётная");
        else
            System.out.println("Sum of three numbers: " + res);
    }

    static double readInputDouble(Scanner sc){
        while(true){
            try{
                return sc.nextDouble();
            }
            catch (InputMismatchException e){
                System.out.print("Wrong input value, try again: ");
                sc.next();
            }
            catch (Exception e) {
                System.out.print("Something went wrong, try once again: ");
            }
        }
    }
}
