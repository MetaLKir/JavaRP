package stream_intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamAppl {
    public static void main(String[] args) {
        Movie[] movies = {
                new Movie("movie1", 2017, 4.5),
                new Movie("movie2", 2010, 4.1),
                new Movie("movie3", 2019, 4.8),
                new Movie("movie4", 2015, 3.5),
                new Movie("movie5", 2018, 5.5),
                new Movie("movie6", 2018, 4.5),
                new Movie("movie7", 2019, 4.8)
        };
        // ======= from Collection =======
        List<Movie> list = new ArrayList<>(Arrays.asList(movies));
        Stream<Movie> stream1 = list.stream();
        // map.entrySet().stream(); // this is how to open stream for Map

        // ======== from Array ========
        Stream<Movie> stream2 = Arrays.stream(movies);

        // ======== from values ========
        Stream<String> streamStr = Stream.of("a", "b", "c");
        Stream<Integer> streamInt = Stream.of(1, 2, 3, 4, 5);

        // ======== from String ========
        IntStream streamFromString = "123sfdafas".chars();

        // ======== from builder ========
        Stream.builder().add("a").add("b").add("c").build();
        Stream<String> s = Stream.<String>builder().add("a").add("b").add("c").build();

        //===================================================
        IntStream.iterate(1, x -> x * 2).limit(10).forEach(System.out::println);
        // this loop gives the same result as stream at the line above
        int x = 1;
        for (int i = 0; i < 10; i++) {
            System.out.println(x);
            x *= 2;
        }

        Stream.generate(() -> "Hello Karmiel").limit(3).forEach(System.out::println);
        Stream.generate(() -> "Hello Karmiel!!!").limit(3).forEach(t -> System.out.println(t));
    }
}
