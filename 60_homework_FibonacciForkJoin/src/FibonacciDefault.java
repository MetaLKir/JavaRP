public class FibonacciDefault {
    public static long calculateNumber(int numPos) {
        if (numPos <= 1) return numPos;

        long x = 0, y = 1;
        for (int i = 2; i <= numPos; i++) {
            long temp = x + y;
            x = y;
            y = temp;
        }
        return y;
    }
}
