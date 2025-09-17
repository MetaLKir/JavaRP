public class LockReent {

    static boolean transfer(AccountR from, AccountR to, int amount, long over) {
        long deadLine = System.currentTimeMillis() + over * 1_000_000L;
        while (System.currentTimeMillis() < deadLine){
            if(from.lock.tryLock()){
                try {
                    if(to.lock.tryLock()) {
                        try {
                            if (from.balance >= amount) {
                                from.balance -= amount;
                                to.balance += amount;
                            }
                            return true;
                        } finally {
                            to.lock.unlock();
                        }
                    }
                } finally {
                    from.lock.unlock();
                }
            }
        }
        return false;
    }

    public static void run() throws InterruptedException {
        AccountR A = new AccountR(1000);
        AccountR B = new AccountR(1000);
        Thread t1 = new Thread(() ->{
            boolean ok = transfer(A, B, 100, 2000);
            System.out.println("Lock reent T1: " + ok);
        }, "Tr1");
        Thread t2 = new Thread(() ->{
            boolean ok = transfer(B, A, 200, 2000);
            System.out.println("Lock reent T2: " + ok);
        }, "Tr2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("balance A = " + A.balance
                + ", balance B = " + B.balance);
    }
}
