package task1.teacherSolution;

import java.util.concurrent.ThreadLocalRandom;

public class Horse extends Thread {
    private int travelledPath = 0;
    private int distance;
    private long horseResult;

    public Horse(int distance, String name) {
        this.distance = distance;
        setName(name);
    }

    public long getHorseResult() {
        return horseResult;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        while (travelledPath < distance) {
            int step = ThreadLocalRandom.current().nextInt(1, 11);
            travelledPath = Math.min(travelledPath + step, distance);
        }
        horseResult = ((System.nanoTime() - start) / 10000);
    }
}
