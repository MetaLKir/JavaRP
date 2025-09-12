package correction1;

public class Truck extends Thread {
    int load; // грузоподъёмность
    int nLoads; // количество ходок
    private static long elevator1 = 0;
    private static long elevator2 = 0;
    private static final Object mutex = new Object();

    public Truck(int load, int nLoads) {
        this.load = load;
        this.nLoads = nLoads;
    }

    public static long getElevator1() {
        return elevator1;
    }

    public static long getElevator2() {
        return elevator2;
    }

    @Override
    public void run() {
        for (int i = 0; i < nLoads; i++) {
            loadElevator1();
            loadElevator2();

        }
    }

    private void loadElevator1() {
        synchronized (mutex) {
            elevator1 += load; // 3 operations: take current, add value, put new instead current
        }
    }

    private void loadElevator2() {
        synchronized (mutex) {
            elevator2 += load;
        }
    }
}
