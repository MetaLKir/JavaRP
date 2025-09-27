import java.util.concurrent.ForkJoinPool;

public class CompareAppl {
    public static void main(String[] args) {
        long res, start, end;
        int numberPosition = 40;

        start = System.currentTimeMillis();
        res = FibonacciDefault.calculateNumber(numberPosition);
        end = System.currentTimeMillis();
        System.out.println("Default, result = " + res + ", time (ms) = " + (end - start));

        start = System.currentTimeMillis();
        res = FibonacciStream.calculateNumber(numberPosition);
        end = System.currentTimeMillis();
        System.out.println("Stream, result = " + res + ", time (ms) = " + (end - start));

        start = System.currentTimeMillis();
        res = ForkJoinPool.commonPool().invoke(new FibonacciFJ(numberPosition));
        end = System.currentTimeMillis();
        System.out.println("Fork Join, result = " + res + ", time (ms) = " + (end - start));

        start = System.currentTimeMillis();
        res = FibonacciRecursion.calculateNumber(numberPosition);
        end = System.currentTimeMillis();
        System.out.println("Recursion, result = " + res + ", time (ms) = " + (end - start));

    }
}
