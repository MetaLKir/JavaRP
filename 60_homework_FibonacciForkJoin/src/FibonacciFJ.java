import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciFJ extends RecursiveTask<Long> {
    private final int position;

    public FibonacciFJ(int position) {
        this.position = position;
    }

    @Override
    protected Long compute() {
        if (position <= 1) return (long) position;
        else {
            FibonacciFJ prevNum = new FibonacciFJ(position - 1);
            prevNum.fork();
            long prevPrevNum = new FibonacciFJ(position - 2).compute();
            return prevNum.join() + prevPrevNum;
        }
    }

    public static void main(String[] args) {
        int numPos = 11;
        long num = ForkJoinPool.commonPool().invoke(new FibonacciFJ(numPos));
        System.out.println("Fibonacci number №" + numPos + ", value is: " + num);
    }
}
