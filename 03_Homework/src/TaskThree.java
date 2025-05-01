/* TODO:
    *** (задание повышенной сложности, необязательное, только для желающих).
        Напишите программу, которая определяет, может ли пользователь получить кредит на основе введенных данных.
        Программа должна запрашивать у пользователя информацию о его доходе,
        возрасте и наличии задолженностей, а затем использовать логические выражения для принятия решения.
        Запросите у пользователя:
            * Наличие задолженностей (да/нет).
            * Месячный доход (в у.е.).
            * Возраст (в годах).
        Условия для получения кредита:
            * Пользователь должен быть старше 21 года.
            * Месячный доход должен быть не менее 12000.
            * Нельзя иметь задолженности (если "да", то кредит не выдается).
        Используйте логические выражения для проверки условий.
        Напишите логическое выражение, которое использует все эти данные
        и даёт результат true, если кредит выдаётся, false, если нет.
        В зависимости от того, какой результат получился, выведите на экран "Кредит одобрен" или "Кредит не одобрен".
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskThree {
    final static int ageAdult = 21;
    final static double minIncomeUSD = 12000;

    public static void main(String[] args) {
        int ageInput;
        double incomeUSDInput;
        boolean isDebtorInput;

        Scanner scanner = new Scanner(System.in);

        System.out.println("I help you check if credit is approved.");
        ageInput = askAge(scanner);
        incomeUSDInput = askIncome(scanner);
        isDebtorInput = askIsDebtor(scanner);

        CheckCreditApproved(isDebtorInput,incomeUSDInput,ageInput);
    }
    //====================================================================//

    static void CheckCreditApproved(boolean isDebtor, double incomeUSD, int age){
        if (!isDebtor && age > ageAdult && incomeUSD > minIncomeUSD)
            System.out.println("Кредит одобрен");
        else
            System.out.println("Кредит не одобрен");
    }

    static int askAge(Scanner sc){
        while(true){
            try {
                System.out.print("Enter your age: ");
                return sc.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Wrong input value, try again...");
                sc.next();
            }
            catch (Exception e) {
                System.out.println("Something went wrong, try again...");
            }
        }
    }

    static double askIncome(Scanner sc){
        while(true){
            try {
                System.out.print("Enter your income (USD): ");
                double inputIncome = sc.nextDouble();
                if (inputIncome < 0)
                    System.out.println("You wrote negative income, try again...");
                else
                    return inputIncome;
            }
            catch (InputMismatchException e){
                System.out.println("Wrong input value, try again...");
                sc.next();
            }
            catch (Exception e) {
                System.out.println("Something went wrong, try again...");
            }
        }
    }

    static boolean askIsDebtor(Scanner sc){
        while(true){
            try {
                System.out.print("Is you debtor? (true/false): ");
                return sc.nextBoolean();
            }
            catch (InputMismatchException e){
                System.out.println("Wrong input value, try again...");
                sc.next();
            }
            catch (Exception e) {
                System.out.println("Something went wrong, try again...");
            }
        }
    }
}
