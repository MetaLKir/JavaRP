public class PrintAppl {
    private static final int N_PRINT = 50;

    public static void main(String[] args) throws InterruptedException {
        Printer p1 = new Printer('#', N_PRINT);
        Printer p2 = new Printer('*', N_PRINT);

        long start = System.nanoTime();

        p1.start();
        p2.start();
//        p1.join();
//        p2.join();
        System.out.printf("\nprinting time is %d\n", System.nanoTime() - start);
    }
}
