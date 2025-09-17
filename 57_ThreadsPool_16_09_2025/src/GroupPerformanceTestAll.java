import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupPerformanceTestAll {
    private static final int N_GROUPS = 1_000;
    private static final int N_NUMBERS_PER_GROUP = 100_000;
    GroupSum grS;

    public GroupPerformanceTestAll(GroupSum grS) {
        this.grS = grS;
    }

    public void runTest() {
        long start = System.currentTimeMillis();
        long sum = grS.computeSum();
        System.out.printf("test: %s | running time: %d | sum: %d\n",
                grS.getClass().getSimpleName(),
                System.currentTimeMillis() - start,
                sum);
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
        List<GroupSum> groups = new ArrayList<>();
        // order affects on running time !!!
        groups.add(new GroupSumThreads1(numbers)); // for warming
        groups.add(new GroupSumStreamParallel(numbers));
        groups.add(new GroupSumStream(numbers));
        groups.add(new GroupSumThreads1(numbers));
        groups.add(new GroupSumThreads2(numbers));
        groups.add(new GroupSumExecutorService(numbers));

        for(GroupSum gr : groups) {
            GroupPerformanceTestAll t = new GroupPerformanceTestAll(gr);
            t.runTest();
        }
    }
}
