package telran;

public class ExampleRecursion {
    public static void main(String[] args) {
        System.out.println(factorialSimple(5));
        System.out.println(factorialRecursion(5));
    }

    public static long factorialSimple(int n) {
        // 5! = 1*2*3*4*5
        long res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    public static long factorialRecursion(int n) {
        if (n <= 1) return 1;
        return n * factorialRecursion(n - 1);
    }
}
