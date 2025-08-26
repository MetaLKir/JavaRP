import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectAppl {
    public static void main(String[] args) {
        Movie[] movies = {
                new Movie("movie1", 2017, 4.5),
                new Movie("movie2", 2010, 4.1),
                new Movie("movie3", 2019, 4.8),
                new Movie("movie4", 2015, 3.5),
                new Movie("movie5", 2018, 5.5),
                new Movie("movie5", 2023, 2.5),
                new Movie("movie6", 2018, 4.5),
                new Movie("movie7", 2019, 4.8)
        };

        // groupingBy
        // group movies by rating - array of movies for each value
        Map<Double, List<Movie>> byRating = Arrays.stream(movies).
                collect(Collectors.groupingBy(Movie::getRating));
        System.out.println(byRating);

        // partitioningBy
        // group movies to <2019 and >=2019 - two movie arrays (true/false)
        Map<Boolean, List<Movie>> byYear = Arrays.stream(movies).
                collect(Collectors.partitioningBy(m -> m.getYear() < 2019));
        System.out.println(byYear);
        System.out.println(byYear.get(true)); // print only moves <2019


        List<Integer> list = Arrays.asList(1, 5, 3, 8, 9, 4, 7, 3, 10);
        Map<Boolean, List<Integer>> evenOdd1 = list.stream().
                collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("======== ODDs and EVENs exercise =========");
        System.out.println(evenOdd1);

        Map<String, List<Integer>> evenOdd2 = list.stream().
                collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
        System.out.println(evenOdd2);

        // joining
        System.out.println(Stream.of("Mama", "mila", "ramu").
                collect(Collectors.joining(" ", "Tvoya ", "!")));

        // mapping collector
        List<String> titles = Arrays.stream(movies).
                collect(Collectors.mapping(Movie::getTitle, Collectors.toList()));
        System.out.println(titles);

        // teeing - runs two streams in different threads.
        /* Arguments:
            1. first stream
            2. second stream
            3. the way we connect results in one
         */
        String minMax = Stream.of("one", "two", "three", "four", "five", "six", "seven").
                collect(Collectors.teeing(
                        Collectors.minBy(Comparator.comparingInt(String::length)),
                        Collectors.maxBy(Comparator.comparingInt(String::length)),
                        (min, max) -> "min value " + min.orElse(null) + " and max value " + max.orElse(null)
                        ));
        System.out.println(minMax);
    }
}
