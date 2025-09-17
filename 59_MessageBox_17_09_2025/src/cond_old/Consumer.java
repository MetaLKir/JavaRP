package cond_old;

public class Consumer extends Thread {
    MessageBox box;
//    volatile public boolean running = true;

    public Consumer(MessageBox box) {
        this.box = box;
    }

    @Override
    public void run() {
        while (true) {
            String message;
            try {
                message = box.take();
                System.out.printf("Thread: %s, id: %d, message: %s\n", getName(), threadId(), message);
            } catch (InterruptedException e) {
                message = box.pull();
                if (message != null) {
                    System.out.printf("Thread: %s, id: %d, message: %s\n", getName(), threadId(), message);
                }
                break;
            }
        }
    }
}
