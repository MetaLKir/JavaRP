package stream_intro;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamRelationAppl {
    static final long N_NUMBERS = 10;

    public static void main(String[] args) {
        /*  RELATION TYPES:
         1. one-to-one
            - map, boxed, peek
         2. one-to-many | many-to-one
            - flatMap, mapMulty,
            - distinct
         3. many-to-many
            - takeWhile, dropWhile, limit, skip
         */
        List<Integer> list = getRandomNumbers();
        System.out.println(list);
        long sum = getSumEven(list);
        System.out.println(sum);
        IntSummaryStatistics stats = list.stream().mapToInt(n->n).summaryStatistics();
        System.out.println(stats);

        OptionalInt maxOp = list.stream().mapToInt(Integer::intValue).max();
        maxOp.ifPresent(s-> System.out.println("max = " + s));
        int max1 = maxOp.orElse(Integer.MIN_VALUE);
        System.out.println("max = " + max1);
        long count = list.stream().mapToInt(n -> n).count();
        System.out.println(count);
        System.out.println(stats.getAverage());
        OptionalInt max_ = IntStream.empty().max();
        int safeMax = max_.orElse(Integer.MIN_VALUE);
        System.out.println(safeMax);

        int safeMax_ = max_.orElseThrow();
        System.out.println(safeMax);
    }

    private static List<Integer> getRandomNumbers() {
        return new Random().
                ints(N_NUMBERS, 1, Integer.MAX_VALUE).
                boxed().
                collect(Collectors.toList());
    }

    private static long getSumEven(List<Integer> list) {
        return list.stream().
                filter(n -> n % 2 == 0).
                mapToLong(n -> n).
                sum();
    }
}
