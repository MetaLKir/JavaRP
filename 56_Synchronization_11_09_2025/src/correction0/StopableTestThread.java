package correction0;

public class StopableTestThread extends Thread {
    private volatile boolean stopped = false;

    @Override
    public void run() {
        System.out.println("Run start");
        while (!stopped) {
            // work imitation
        }
        System.out.println("Run stopped");
    }

    public void askStop() {
        System.out.println("Ask stop");
        stopped = true;
    }

    public boolean isStopped() {
        return stopped;
    }

    public static void main(String[] args) throws InterruptedException {
        StopableTestThread t = new StopableTestThread();
        t.start();
        Thread.sleep(3000);
        t.askStop();
        System.out.println(t.isStopped());
    }
}
