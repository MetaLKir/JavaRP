public class YieldAppl implements Runnable {
    private String name;

    public YieldAppl(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(name + ": " + i);
            if (i == 2) {
                System.out.println(name + " is yielding the CPU");
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new YieldAppl("first"));
        Thread t2 = new Thread(new YieldAppl("second"));
        t1.start();
        t2.start();
    }
}
