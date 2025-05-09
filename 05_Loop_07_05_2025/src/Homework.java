public class Homework {
    public static void main(String[] args) {
        int res = countDigits(123); // 3
        System.out.println("Count digits = " + res);
        res = countDigits(8); // 1
        res = countDigits(0); // 1


        luckyNumber(173854); // 1+3+5 != 7+8+4 --> unlucky
        luckyNumber(173867); // 1+3+6 == 7+8+7 --> lucky
        // count sum of odd and even numbers, if equal >> lucky, else >> unlucky
    }

    private static void luckyNumber(int i) {
        //TODO: sout - lucky/unlucky
    }

    private static int countDigits(int i) {
        return 0;
    }
}
