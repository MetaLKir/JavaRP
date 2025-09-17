public class GroupSumThreads1 extends GroupSum {

    public GroupSumThreads1(int[][] numbers) {
        super(numbers);
    }

    @Override
    public long computeSum() {
        Thread[] threads = new Thread[numbers.length];
        OneGroupSum[] groups = new OneGroupSum[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            groups[i] = new OneGroupSum(numbers[i]);
            threads[i] = new Thread(groups[i]);
            threads[i].start();
        }

        long sumAll = 0L;
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            sumAll += groups[i].getSum();
        }
        return sumAll;
    }

    public static void main(String[] args) {
        int[][] box = {
                {1, 2, 3},
                {4, 5, 6}
        };
        GroupSumThreads1 gS = new GroupSumThreads1(box);
        System.out.println(gS.computeSum());
    }
}
