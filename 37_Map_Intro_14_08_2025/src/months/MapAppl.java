package months;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class MapAppl {
    public static void main(String[] args) {
        Map<Integer, Integer> values = new TreeMap<>();

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int value = random.nextInt(0, 10);
            values.put(value, values.getOrDefault(value, 0) + 1);
        }

        System.out.println(values);

        // check sum
        int sum = 0;
        for (Integer value : values.values()) {
            sum += value;
        }
        System.out.println("Sum = " + sum);
    }
}
