package correction0;

public class Truck extends Thread {
    int load; // грузоподъёмность
    int nLoads; // количество ходок
    private static long elevator1 = 0;
    private static long elevator2 = 0;

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
            loadElevator1(load);
            loadElevator2(load);

        }
    }

    synchronized private static void loadElevator1(int load) {
        elevator1 += load; // 3 operations: take current, add value, put new instead current
    }

    synchronized private static void loadElevator2(int load) {
        elevator2 += load;
    }
}
