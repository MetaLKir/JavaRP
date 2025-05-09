public class Homework {
    public static void main(String[] args) {
        int res = countDigits(123); // 3
        System.out.println("Count digits first = " + res);
        res = countDigits(8); // 1
        System.out.println("Count digits second = " + res);
        res = countDigits(0); // 1
        System.out.println("Count digits third = " + res);
        System.out.println("=============================");
        luckyNumber(173854); // 1+3+5 != 7+8+4 --> unlucky
        luckyNumber(143253); // 1+3+5 == 4+2+3 --> lucky
        luckyNumber(222222);
        luckyNumber(111);
        luckyNumber(0);
        luckyNumber(-3234);
    }

    private static void luckyNumber(int num) {
        // some checks, just because I want
        if (num == 0) {
            System.out.println("Extra-unlucky, literally ZERO");
            return;
        }
        if (countDigits(num) % 2 != 0) { // prevent numbers with odd digit count
            System.out.println("Digit count is odd. Can't check is lucky.");
            return;
        }
        if (num < 0)
            num = -num;
        // actual code below
        int sumEven = 0;
        int sumOdd = 0;
        for (boolean isOdd = true; num > 0; num /= 10){ // flip-flop between "if" and "else" on each iteration
            int remainder = num % 10;
            if (isOdd)
                sumEven += remainder;
            else
                sumOdd += remainder;
            isOdd = !isOdd;
        }
        if(sumEven == sumOdd)
            System.out.println("Number is lucky");
        else
            System.out.println("Number is unlucky");
    }

    private static int countDigits(int num) {
        if (num == 0)
            return 1;
        if (num < 0)
            num = -num;

        int counter = 0;
        while (num > 0){
            counter++;
            num /= 10;
        }
        return counter;
    }
}
