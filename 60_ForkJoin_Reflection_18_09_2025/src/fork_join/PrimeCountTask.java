package fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class PrimeCountTask extends RecursiveTask<Long> {
    private static final int TH = 20_000;
    private final int START, END;

    public PrimeCountTask(int start, int end) {
        this.START = start;
        this.END = end;
    }

    @Override
    protected Long compute() {
        int len = END - START + 1;
        if (len <= TH) {
            long sum = 0;
            for (int i = START; i <= END; i++) {
                if (isPrime(i)) sum++;
            }
            return sum;
        }
        int mid = START + len / 2;
        PrimeCountTask left = new PrimeCountTask(START, mid);
        left.fork();
        long right = new PrimeCountTask(mid + 1, END).compute();
        return left.join() + right;
    }



    public static void main(String[] args) {
        int from = 2, to = 10_000_000;

        long start = System.currentTimeMillis();
        long sum1 = countPrimesUs(from, to);
        System.out.println("Simple count " + (System.currentTimeMillis() - start) + " ms, count = " + sum1);

        start = System.currentTimeMillis();
        long sum2 = ForkJoinPool.commonPool().invoke(new PrimeCountTask(from, to));
        System.out.println("ForkJoin count " + (System.currentTimeMillis() - start) + " ms, count = " + sum2);
    }

    private static long countPrimesUs(int from, int to) {
        long sum = 0;
        for (int i = from; i <= to; i++) {
            if (isPrime(i)) sum++;
        }
        return sum;
    }

    private static boolean isPrime(int number) {
        if (number < 2) return false;
        if (number % 2 == 0) return number == 2; // no even simple numbers except 2
        for (int i = 3; i * i < number; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
