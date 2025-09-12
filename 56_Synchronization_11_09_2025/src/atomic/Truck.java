package atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Truck extends Thread {
    int load; // грузоподъёмность
    int nLoads; // количество ходок
    private static AtomicLong elevator1 = new AtomicLong(0);
    private static AtomicLong elevator2 = new AtomicLong(0);

    public Truck(int load, int nLoads) {
        this.load = load;
        this.nLoads = nLoads;
    }

    public static long getElevator1() {
        return elevator1.get();
    }

    public static long getElevator2() {
        return elevator2.get();
    }

    @Override
    public void run() {
        for (int i = 0; i < nLoads; i++) {
            loadElevator1();
            loadElevator2();

        }
    }

    private void loadElevator1() {
        elevator1.addAndGet(load);
    }

    private void loadElevator2() {
        elevator2.getAndAdd(load);
    }
}
