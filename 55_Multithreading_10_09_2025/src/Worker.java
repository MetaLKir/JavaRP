// isAlive()
public class Worker extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Worker w = new Worker();
        System.out.println("before start: " + w.isAlive()); // false
        w.start();
        System.out.println("after start: " + w.isAlive()); // true
        w.join();
        System.out.println("after join: " + w.isAlive()); // false

    }
}


