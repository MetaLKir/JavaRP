/* TODO:
    Напишите программу, которая запрашивает у пользователя число.
    Если введённое число делится нацело на три, вывести на экран – «Ваше число делится на три».
    Если нет – вывести на экран – «Ваше число не делится на три».
 */
public class TaskOne {

    public static void main(String[] args) {
        System.out.println("I help you check if a number is multiple of 3.");
        double userNumber = askNumber();
        isMultipleOfTree(userNumber);
    }
    //====================================================================//

    static void isMultipleOfTree(double n){
        if (n % 3 == 0)
            System.out.println("Ваше число делится на три");
        else
            System.out.println("Ваше число не делится на три");
    }

    static double askNumber(){
        while (true){
            try {
                System.out.print("Enter a number: ");
                String res = System.console().readLine().replace(',', '.');
                return Double.parseDouble(res);
            }
            catch (NumberFormatException e){
                System.out.println("Wrong input value, try again...");
            }
            catch (Exception e){
                System.out.println("Something wrong, try again...");
            }
        }

    }
}
