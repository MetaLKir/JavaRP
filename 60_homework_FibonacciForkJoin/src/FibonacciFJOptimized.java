import java.util.concurrent.RecursiveTask;

public class FibonacciFJOptimized extends RecursiveTask<Long> {
    private final int position;
    private int border = 10;

    public FibonacciFJOptimized(int position) {
        this.position = position;
    }

    public FibonacciFJOptimized(int position, int border) {
        this.position = position;
        this.border = border;
    }

    @Override
    protected Long compute() {
        if (position <= border) {
            if (position <= 1) return (long) position;
            long x = 0, y = 1;
            for (int i = 2; i <= position; i++) {
                long temp = x + y;
                x = y;
                y = temp;
            }
            return y;
        } else {
            FibonacciFJOptimized prevNum = new FibonacciFJOptimized(position - 1);
            prevNum.fork();
            long prevPrevNum = new FibonacciFJOptimized(position - 2).compute();
            return prevNum.join() + prevPrevNum;
        }
    }
}
