package noncorrected;

public class KibuzzAppl {
    private static final int N_TRUCKS = 1000;
    private static final int LOAD = 2;
    private static final int N_LOADS = 1000;

    public static void main(String[] args) {
        Truck[] trucks = new Truck[N_TRUCKS];
        for (int i = 0; i < N_TRUCKS; i++) {
            trucks[i] = new Truck(LOAD, N_LOADS);
            trucks[i].start();
        }

        long start = System.nanoTime();
        for(Truck truck : trucks){
            try {
                truck.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("running time is %d ms\nelevator1 contains %d tons" +
                        "\nelevator2 contains %d tons\n",
                (System.nanoTime() - start)/1000,
                Truck.getElevator1(),
                Truck.getElevator2());
    }
}
