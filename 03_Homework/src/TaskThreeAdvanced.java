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
// T___T
// It took some time to get how to mix generics with lambdas to make it work

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaskThreeAdvanced {
    final static int ageAdult = 21;
    final static double minIncomeUSD = 12000;

    public static void main(String[] args) {
        int age;
        double incomeUSD;
        boolean isDebtor;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        age = ask(reader, "Enter age: ", Integer::parseInt);
        incomeUSD = ask(reader, "Enter income: ", Double::parseDouble);

        Parser<Boolean> booleanParser = (s) -> {
            if (s.equalsIgnoreCase("yes") || s.equalsIgnoreCase("y"))
                return true;
            if (s.equalsIgnoreCase("no") || s.equalsIgnoreCase("n"))
                return false;
            throw new IllegalArgumentException();
        };
        isDebtor = ask(reader, "Is debtor? (yes/no): ", booleanParser);

        CheckCreditApproved(isDebtor,incomeUSD,age);
    }
    //====================================================================//

    static <T> T ask(BufferedReader rd, String question, Parser<T> parser){
        while(true){
            try {
                System.out.print(question + " ");
                String str = rd.readLine();
                return parser.parse(str);
            } catch (Exception e) {
                System.out.println("Something wrong. Enter again...");
            }
        }
    }

    static void CheckCreditApproved(boolean isDebtor, double incomeUSD, int age){
        if (!isDebtor && age > ageAdult && incomeUSD > minIncomeUSD)
            System.out.println("Кредит одобрен");
        else
            System.out.println("Кредит не одобрен");
    }
}

@FunctionalInterface
interface Parser<T> {
    T parse(String strToParse);
}