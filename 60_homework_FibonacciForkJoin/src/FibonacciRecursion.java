public class FibonacciRecursion {
    public static long calculateNumber(int numPos) {
        if (numPos <= 1) return numPos;
        return calculateNumber(numPos - 1) + calculateNumber(numPos - 2);
    }
}
