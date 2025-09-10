public class PriorityAppl {

    public static void main(String[] args) throws InterruptedException {
        Runnable job = () -> {
            long end = System.currentTimeMillis() + 50;
            long ops = 0;
            while (System.currentTimeMillis() < end) {
                ops++;
                System.out.println(Thread.currentThread().getName() + " priority = "
                        + Thread.currentThread().getPriority() + ", ops = " + ops);
            }
        };
        Thread low = new Thread(job, "low");
        Thread norm = new Thread(job, "norm");
        Thread high = new Thread(job, "high");

        low.setPriority(Thread.MIN_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);
        high.setPriority(Thread.MAX_PRIORITY);

        low.start();
        norm.start();
        high.start();

        low.join();
        norm.join();
        high.join();
    }
}
