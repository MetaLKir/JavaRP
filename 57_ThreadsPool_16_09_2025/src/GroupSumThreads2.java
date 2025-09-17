import java.util.concurrent.atomic.AtomicLong;

public class GroupSumThreads2 extends GroupSum {

    public GroupSumThreads2(int[][] numbers) {
        super(numbers);
    }

    @Override
    public long computeSum() {
        AtomicLong sum = new AtomicLong(0);
        Thread[] threads = new Thread[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int index = i; // if put i directly in thread, it would be changing in it
            threads[i] = new Thread(() -> {
                long localSum = 0L;
                for (int t : numbers[index]) {
                    localSum += t;
                }
                sum.addAndGet(localSum);
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }


        return sum.get();
    }

    public static void main(String[] args) {
        int[][] box = {
                {1, 2, 3},
                {4, 5, 6}
        };
        GroupSumThreads2 gS = new GroupSumThreads2(box);
        System.out.println(gS.computeSum());
    }
}
