package task1;

import java.util.Arrays;
import java.util.Comparator;

public class ThreadsRaceAppl {

    public static void main(String[] args) throws InterruptedException {
        int numbersOfRacers = 5;
        int distance = 50;
        race(numbersOfRacers, distance);
    }

    private static void race(int numbersOfRacers, int distance) throws InterruptedException {
        Racer[] racers = new Racer[numbersOfRacers];

        for (int i = 0; i < numbersOfRacers; i++) {
            racers[i] = new Racer(distance, "Racer" + (i + 1));
            racers[i].start();
        }

        for (Racer r : racers) {
            r.join(); // ждём завершения каждого
        }
        printRaceResults(racers);
    }

    private static void printRaceResults(Racer[] winners) {
        Comparator<Racer> comp = (r1, r2) -> (int) (r1.getNanoTime() - r2.getNanoTime());
        Racer[] winnersSorted = Arrays.copyOf(winners, winners.length);
        Arrays.sort(winnersSorted, comp);

        System.out.println("======== RESULTS ========");
        System.out.println("Place | Thread number | Time (ms)");
        for (int i = 0; i < winnersSorted.length; i++) {
            System.out.printf("%d\t\t%s\t\t\t%.3f\n",
                    i + 1,
                    winnersSorted[i].getName(),
                    (winnersSorted[i].getNanoTime() / 1000. / 1000));
        }
    }
}
