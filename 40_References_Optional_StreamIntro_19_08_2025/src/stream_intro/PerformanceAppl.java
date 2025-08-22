package stream_intro;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class PerformanceAppl {

    public static void main(String[] args) {
        final int N = 1_000_000;
        final int MAX = 16;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = i % (MAX + 1);
        System.out.println(arr[18]);

        //======================= sum ===========================
        long start = System.currentTimeMillis();
        long s = IntStream.of(arr).
                flatMap(x -> IntStream.range(0, x)).
                asLongStream().
                sum();
        long end = System.currentTimeMillis();
        System.out.println("flat sum time: " + (end - start) + " ms" + " | sum is " + s);

        start = System.currentTimeMillis();
        s = IntStream.of(arr).
                mapMulti((x, sink) -> {
                    for (int i = 0; i < x; i++)
                        sink.accept(i);
                }).
                asLongStream().
                sum();
        end = System.currentTimeMillis();
        System.out.println("multi sum time: " + (end - start) + " ms" + " | sum is " + s);

        //====================== filter ============================
        System.out.println("=".repeat(50));

        start = System.currentTimeMillis();
        s = IntStream.of(arr).
                flatMap(x -> IntStream.range(0, x)).
                filter(k -> k % 2 == 0).
                asLongStream().
                sum();
        end = System.currentTimeMillis();
        System.out.println("flat filter time: " + (end - start) + " ms" + " | sum is " + s);

        start = System.currentTimeMillis();
        s = IntStream.of(arr).
                mapMulti((x, sink) -> {
                    for (int i = 0; i < x; i++)
                        if (i % 2 == 0) sink.accept(i);
                }).
                asLongStream().
                sum();
        end = System.currentTimeMillis();
        System.out.println("multi filter time: " + (end - start) + " ms" + " | sum is " + s);

        //===================== Boxed =============================
        System.out.println("=".repeat(50));

        Integer[] b = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        start = System.currentTimeMillis();
        s = Arrays.stream(b).
                flatMap(x -> IntStream.range(0, x).boxed()).
                mapToLong(Integer::longValue).
                sum();

        end = System.currentTimeMillis();
        System.out.println("flat boxed time: " + (end - start) + " ms" + " | sum is " + s);

        start = System.currentTimeMillis();
        s = Arrays.stream(b).
                mapMulti((Integer x, Consumer<? super Integer> sink)-> {
                    for (int i = 0; i < x; i++)
                        sink.accept(i);
        }).
                mapToLong(Integer::longValue).
                sum();
        end = System.currentTimeMillis();
        System.out.println("multi boxed time: " + (end - start) + " ms" + " | sum is " + s);
    }
}
