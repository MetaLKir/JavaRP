package correction0;

public class ReorderingAppl implements Runnable {
    static int x = 0, y = 0;
    boolean writer;
    final int[] seen;

    public ReorderingAppl(boolean writer, int[] seen) {
        this.writer = writer;
        this.seen = seen;
    }

    public void f1() {
        x = 5;
        y = 10;
    }

    public int f2() {
        while (y != 10) {
            Thread.yield();
        }
        return x;
    }

    @Override
    public void run() {
        if (writer) f1();
        else seen[0] = f2();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100_000; i++) {
            x = y = 0;
            final int[] seen = {-1};
            Thread tRead = new Thread(new ReorderingAppl(false, seen));
            Thread tWriter = new Thread(new ReorderingAppl(true, null));

            tRead.start();
            tWriter.start();

            tRead.join();
            tWriter.join();

            if (seen[0] != 5)
                System.out.printf("y==10 but x==%d (iteration %d)\n",
                        seen[0], i);

        }
    }


}
