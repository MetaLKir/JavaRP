import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GroupSumExecutorService extends GroupSum {
    int nThreads;

    public GroupSumExecutorService(int[][] numbers) {
        super(numbers);
        Runtime rt = Runtime.getRuntime();
        nThreads = rt.availableProcessors();
        //nThreads = 16;
    }

    public void setnThreads(int nThreads) {
        this.nThreads = nThreads;
    }

    @Override
    public long computeSum() {
        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        OneGroupSum[] groups = goodExecutor(pool);
        waitThreads(pool);
        return computeGroup(groups);
    }

    private long computeGroup(OneGroupSum[] groups) {
        return Arrays.stream(groups).
                mapToLong(OneGroupSum::getSum).
                sum();
    }

    private void waitThreads(ExecutorService pool) {
        pool.shutdown();
        try {
            pool.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {}
    }

    private OneGroupSum[] goodExecutor(ExecutorService pool) {
        OneGroupSum[] res = new OneGroupSum[numbers.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = new OneGroupSum(numbers[i]);
            pool.execute(res[i]);
        }
        return res;
    }
}
