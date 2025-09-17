package reentrant;

public class Producer extends Thread {
    MessageBox box;

    public Producer(MessageBox box) {
        this.box = box;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            box.put("producer " + threadId() + ", message " + i);
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
