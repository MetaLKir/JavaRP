public class PrintAppl {
    private static final int N_PRINT = 50;

    public static void main(String[] args) throws InterruptedException {
        Printer p1 = new Printer('#', N_PRINT);
        Printer p2 = new Printer('*', N_PRINT);

        long start = System.nanoTime();

//        Thread.currentThread().join(); // deadlock

        p1.start();
        p1.join(); // tells to "main" (and next threads) to pause until "p1" will be finished
        p2.start();
        p2.join();
        Thread.sleep(3000);
        System.out.printf("\nprinting time is %d\n", System.nanoTime() - start);
    }
}
