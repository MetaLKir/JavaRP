package telran.validator.tools;

public class Validator {

    public static boolean checkCreditCard(String cardNumber){
        String pattern = "\\d{8,19}";
        return cardNumber.matches(pattern);
    }

    public static boolean checkDateFormatEU(String date) {
        String pattern = "(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.\\d{4}";
        return date.matches(pattern);
    }

    public static boolean checkDateFormatUS(String date) {
        String pattern = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])";
        return date.matches(pattern);
    }
}
