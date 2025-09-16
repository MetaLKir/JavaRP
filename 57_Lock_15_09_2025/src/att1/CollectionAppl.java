package att1;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

public class CollectionAppl {
    private static int N_THREADS = 10;
    private static int N_NUMBERS = 100;
    private static int N_RUNS = 1000;
    private static int PROB_UPDATE = 50;


    public static void main(String[] args) {
        CollectionProcessingThread[] threads = new CollectionProcessingThread[N_THREADS];
        startThreads(threads);
        waitThreads(threads);
        System.out.println(CollectionProcessingThread.countLock);
    }

    private static void waitThreads(CollectionProcessingThread[] threads) {
        for(CollectionProcessingThread c : threads){
            try {
                c.join();
            } catch (InterruptedException e) {  }
        }
    }

    private static void startThreads(CollectionProcessingThread[] threads) {
        Collection<Integer> coll = getRandomColl();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new CollectionProcessingThread(coll, N_RUNS, PROB_UPDATE);
            threads[i].start();
        }
    }

    private static Collection<Integer> getRandomColl() {
        return new Random().ints(N_NUMBERS, CollectionProcessingThread.MIN_VALUE, CollectionProcessingThread.MAX_VALUE).
                boxed().
                collect(Collectors.toList());
    }
}
