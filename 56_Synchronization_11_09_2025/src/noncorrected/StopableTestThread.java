package noncorrected;

public class StopableTestThread extends Thread {
    private boolean stopped = false;

    @Override
    public void run() {
        System.out.println("Run start");
        while (!stopped) {
//            System.out.print("");
            try {
                Thread.sleep(0); // any non-calc operation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        Thread.sleep(1000);
        t.askStop();
        System.out.println(t.isStopped());
    }
}
