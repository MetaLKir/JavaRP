import java.util.stream.Stream;

public class Terminal1Appl {
    public static void main(String[] args) {

        // reduce
        System.out.println("=".repeat(20) + "reduce" + "=".repeat(20));
        int sum1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(0, Integer::sum);
        System.out.println(sum1);

        int sum2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(Integer::sum).orElse(0);
        System.out.println(sum2);

        int sum3 = Stream.of(1, 2, 3).
                parallel().
                reduce(0, (a, b) -> a + b, (a, b) -> a + b);
        System.out.println(sum3);

        // matching
        // anyMatch -> true, if at least one matches
        // noneMatch -> true, if no matching elements
        // allMatch -> true, all elements match
        System.out.println("=".repeat(20) + "matching" + "=".repeat(20));
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).anyMatch(n -> n == 9));
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).noneMatch(n -> n < 0));
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).noneMatch(n -> n % 2 == 0));
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).allMatch(n -> n < 10));

        // findFirst (for regular streams)
        // findAny (for parallel streams)
        System.out.println("=".repeat(20) + "find" + "=".repeat(20));
        System.out.println(Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).findFirst().get());

        // forEach for regular stream
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).
                forEach(n -> System.out.print(n + " "));
        System.out.println();
        // forEachOrdered for parallel stream
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).
                parallel().forEachOrdered(n -> System.out.print(n + " "));
        System.out.println();
        // get mess when use forEach in parallel stream
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).
                parallel().forEach(n -> System.out.print(n + " "));
        System.out.println();
    }
}
