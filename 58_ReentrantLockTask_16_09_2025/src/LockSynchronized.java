public class LockSynchronized {

    static void transfer(AccountM from, AccountM to, int amount) {
        synchronized (from.mutex) {
            sleep(50);

            synchronized (to.mutex) {
                if (from.balance >= amount) {
                    from.balance -= amount;
                    to.balance += amount;
                }
            }
        }
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean run() throws InterruptedException {
        AccountM A = new AccountM(1000);
        AccountM B = new AccountM(1000);
        Thread t1 = new Thread(() -> transfer(A, B, 100), "Tr1");
        Thread t2 = new Thread(() -> transfer(B, A, 200), "Tr2");
        t1.start();
        t2.start();
        t1.join(1000);
        t2.join(1000);
        boolean finish = !t1.isAlive() && !t2.isAlive();
        System.out.println("finish = " + finish + " deadlock");
        System.out.println("balance A = " + A.balance
                + ", balance B = " + B.balance);
        return finish;
    }
}
