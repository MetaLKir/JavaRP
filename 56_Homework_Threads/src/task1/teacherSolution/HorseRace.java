package task1.teacherSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HorseRace {
    public static void main(String[] args) throws InterruptedException {
        horseRace(10, 300);
    }

    private static void horseRace(int numOfHorses, int distance) throws InterruptedException {
        Horse[] horses = new Horse[numOfHorses];
        for (int i = 0; i < numOfHorses; i++) {
            horses[i] = new Horse(distance, "horse " + (i + 1));
            horses[i].start();
        }

        Map<Long, List<String>> results = new TreeMap<>();

        for (Horse horse : horses) {
            horse.join();
            results.computeIfAbsent(horse.getHorseResult(), k -> new ArrayList<>()).add(horse.getName());
        }

        System.out.println("place\t\tthread number\t\ttime ms");
        int place = 1;
        for (Map.Entry<Long, List<String>> e : results.entrySet()) {
            List<String> names = e.getValue();
            int groupSize = names.size();
            String placeLabel = (groupSize == 1) ? String.valueOf(place) :
                    place + "-" + (place + groupSize - 1);
            for (String name : names) {
                System.out.println(placeLabel + "\t\t\t" + name + "\t\t\t\t" + e.getKey());
            }
            place += groupSize;
        }
    }
}
