public class LocksDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("===== Mutex =====");
        LockSynchronized.run();
        System.out.println("===== Reent =====");
        LockReent.run();

    }
}
