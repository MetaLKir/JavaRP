package stream_intro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAdv_1Appl {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 3, -3, 3, 4, -5, 6, 4, -7, -7, -7, 8);

        // ======== Distinct ======== // only unique values
        numbers.stream().
                distinct().
                forEach(System.out::println);

        System.out.println("=".repeat(25));
        // same, but with HashSet
        Set<Integer> set = new HashSet<>(numbers);
        System.out.println(set);

        // ======== Limit ======== //
        System.out.println("=".repeat(50));
        IntStream.
                range(1, 100).
                limit(5).
                forEach(System.out::println);

        // ======== Limit ======== //
        System.out.println("=".repeat(50));
        IntStream.
                range(1, 100).
                skip(95).
                forEach(System.out::println);

        Stream.of(1, 3, -3, 3, 4, -5, 6, 4, -7, -7, -7, 8).
                distinct().
                skip(3).
                limit(2).
                forEach(System.out::println);

        // ======== peek ======== // for "debug" through console, because debugger works bad with streams
        System.out.println("=".repeat(50));
        numbers.stream().
                peek(n -> System.out.println("in element: " + n)).
                distinct().
                peek(n -> System.out.println("after distinct element: " + n)).
                forEach(System.out::println);

        System.out.println("=".repeat(50));
        numbers.stream().
                peek(n -> System.out.println("in element: " + n)).
                filter(n -> n % 2 == 0).
                peek(n -> System.out.println("after filter element: " + n)).
                map(n -> n * n).
                peek(n -> System.out.println("after map element: " + n)).
                forEach(System.out::println);

        System.out.println("=".repeat(50));
        int[] count = {0};
        int sum = IntStream.
                rangeClosed(0, 5).
                peek(n -> count[0]++).
                sum();
        System.out.println("sum = " + sum + ", counter = " + count[0]);

        // ======== takeWhile ======== //
        System.out.println("=".repeat(50));
        List<Integer> numbers1 = List.of(1, 3, 3, 4, -3, -5, 6, 4, -7, -7, -7, 8);
        numbers1.stream().takeWhile(x -> x > 0).forEach(System.out::println);

        // ======== dropWhile ======== //
        System.out.println("=".repeat(50));
        numbers1.stream().dropWhile(x -> x > 0).forEach(System.out::println);
    }

}
