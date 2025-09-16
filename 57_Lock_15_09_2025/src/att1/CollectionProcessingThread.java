package att1;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CollectionProcessingThread extends Thread {
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 50;
    static final Lock lock = new ReentrantLock();
    public static AtomicLong countLock = new AtomicLong(0);
    private Collection<Integer> collection;
    private int nRuns;
    private int probUpdate;
    Random r = new Random();

    public CollectionProcessingThread(Collection<Integer> collection, int nRuns, int probUpdate) {
        this.collection = collection;
        this.nRuns = nRuns;
        this.probUpdate = probUpdate;
    }

    @Override
    public void run() {
        for (int i = 0; i < nRuns; i++) {
            if (r.nextInt(1, 101) <= probUpdate)
                update();
            else
                read();
        }
    }

    private void update() {
        try {
            tryDoLock();
            int number = r.nextInt(MIN_VALUE, MAX_VALUE);
            collection.add(number);
            collection.remove(number);
        } finally {
            lock.unlock();
        }
    }

    private void read() {
        try {
            tryDoLock();
            collection.forEach(n -> r.nextInt(1, n + 1));
        } finally {
            lock.unlock();
        }
    }

    private void tryDoLock() {
        while (!lock.tryLock())
            countLock.getAndIncrement();
    }
}
