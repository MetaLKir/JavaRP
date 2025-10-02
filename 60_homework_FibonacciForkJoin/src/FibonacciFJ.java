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
}
