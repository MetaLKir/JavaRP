import java.util.Random;

public class GroupPerformanceTest {
    private static final int N_GROUPS = 1000;
    private static final int N_NUMBERS_PER_GROUP = 100_000;
    GroupSum grS;

    public GroupPerformanceTest(GroupSum grS) {
        this.grS = grS;
    }

    public void runTest() {
        long start = System.currentTimeMillis();
        long sum = grS.computeSum();
        System.out.println("Test: " + (System.currentTimeMillis() - start) + " ms | sum: " + sum);
    }

    private static int[][] getRNumbers() {
        Random r = new Random();
        int[][] res = new int[N_GROUPS][];
        for (int i = 0; i < N_GROUPS; i++) {
            res[i] = r.ints(N_NUMBERS_PER_GROUP, 1, Integer.MAX_VALUE).
                    toArray();
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] numbers = getRNumbers();
        GroupSumExecutorService eSgrS = new GroupSumExecutorService(numbers);

        GroupPerformanceTest test = new GroupPerformanceTest(eSgrS);
        //test.runTest();
        for (int nThreads = 10; nThreads < 200; nThreads+=10) {
            System.out.println("nThreads: " + nThreads);
            eSgrS.setnThreads(nThreads);
            test.runTest();
        }
    }
}
