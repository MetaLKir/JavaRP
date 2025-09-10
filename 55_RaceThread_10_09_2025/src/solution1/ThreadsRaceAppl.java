package solution1;

public class ThreadsRaceAppl {
    static String winner = "";
    static boolean finish;

    public static void main(String[] args) throws InterruptedException {
        int numbersOfRacers = 5;
        int distance = 50;
        String w = race(numbersOfRacers, distance);
        System.out.println(w);
    }

    private static String race(int numbersOfRacers, int distance) throws InterruptedException {
        Racer[] racers = new Racer[numbersOfRacers];

        for (int i = 0; i < numbersOfRacers; i++) {
            racers[i] = new Racer(distance, "Racer" + (i + 1));
            racers[i].start();
        }

        while (!finish) {
            for (Racer racer : racers) {
                if (racer.getMove() == distance && winner.isBlank()) {
                    winner = "Congratulations " + racer.getName();
                    finish = true;
                    break;
                }
            }
        }
        for (Racer r : racers) {
            r.join(); // ждём завершения каждого
        }
        return winner;
    }
}
