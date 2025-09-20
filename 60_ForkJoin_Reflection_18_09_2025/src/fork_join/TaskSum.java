package fork_join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class TaskSum extends RecursiveTask<Long> {
    private static final int TH = 20_000;
    private final int START, END;
    private long[] array;

    public TaskSum(long[] array, int START, int END) {
        this.array = array;
        this.START = START;
        this.END = END;
    }

    @Override
    protected Long compute() {
        if (END - START <= TH) {
            long sum = 0;
            for (int i = START; i < END; i++) {
                sum += array[i];
            }
            return sum;
        }
        int mid = (START + END) / 2;
        TaskSum left = new TaskSum(array, START, mid);
        left.fork();
        long right = new TaskSum(array, mid, END).compute();
        return left.join() + right;
    }

    public static void main(String[] args) {
        long[] arr = LongStream.
                rangeClosed(1, 1_000_000).
                toArray();
        long res = 0;
        long start = System.currentTimeMillis();
        for (long l : arr) {
            res += l;
        }
        System.out.println("Simple sum " + (System.currentTimeMillis() - start) + " ms, sum = " + res);

        start = System.currentTimeMillis();
        res = ForkJoinPool.commonPool().invoke(new TaskSum(arr, 0, arr.length));
        System.out.println("ForkJoin sum " + (System.currentTimeMillis() - start) + " ms, sum = " + res);
    }


}
