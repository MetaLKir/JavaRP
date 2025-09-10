package solution1;

import java.util.Random;

public class Racer extends Thread {

    private static final int STEP_MIN = 2;
    private static final int STEP_MAX = 5;
    private int move = 0, distance = 0;

    public Racer(int distance, String name) {
        this.distance = distance;
        setName(name);
    }

    public int getMove() {
        return move;
    }

    @Override
    public void run() {
        Random r = new Random();
        while (move < distance) {
            move += r.nextInt(STEP_MIN, STEP_MAX);
            move = Math.min(move, distance);
            System.out.println(getName() + " distance: " + move + "/" + distance);
        }
    }
}
