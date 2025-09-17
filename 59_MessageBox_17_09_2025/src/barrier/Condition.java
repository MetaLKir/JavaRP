package barrier;

import java.util.List;
import java.util.stream.IntStream;

public class Condition {
    static final int THREADS_COUNT = 100;
    private final Object monitor = new Object();
    private int count = 0;

    public void barrier() {
        synchronized (monitor) {
            count++;
            while (count < THREADS_COUNT) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            monitor.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Condition condition = new Condition();
        Runnable run = () -> {
            condition.barrier();
            System.out.println("thread: " + Thread.currentThread().threadId());
        };
        List<Thread> threads = IntStream.
                range(0, THREADS_COUNT).
                mapToObj(i -> new Thread(run, "thread-" + i)).
                peek(Thread::start).toList();

        for (Thread t : threads) {
            t.join();
        }
    }
}
