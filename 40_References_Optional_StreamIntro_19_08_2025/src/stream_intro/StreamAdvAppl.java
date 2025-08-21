package stream_intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StreamAdvAppl {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 8, -3, 3, 4, -5, 6, -7);
        numbers.stream().filter(n -> n > 0 && n % 2 == 0).forEach(System.out::println);

        System.out.println("=".repeat(50));
        IntStream.of(1, 8, -3, 3, 4, -5, 6, -7).sorted().forEach(System.out::println);

        System.out.println("=".repeat(50));
        IntStream.of(1, 8, -3, 3, 4, -5, 6, -7).
                boxed().
                sorted(Comparator.reverseOrder()).
                mapToInt(Integer::intValue).
                forEach(System.out::println);
        // .boxed puts in Integer wrapper. It allows to use Comparator for .sorted

        //====================================================
        System.out.println("=".repeat(50));
        Movie[] movies = {
                new Movie("movie1", 2017, 4.5),
                new Movie("movie2", 2010, 4.1),
                new Movie("movie3", 2019, 4.5),
                new Movie("movie4", 2015, 3.5),
                new Movie("movie5", 2018, 5.5),
                new Movie("movie6", 2018, 4.5),
                new Movie("movie7", 2019, 4.8)};

        List<Movie> m1 = List.of(movies);
        m1.stream().map(Movie::getTitle).forEach(System.out::println);

        System.out.println("=".repeat(50));
        displayMovieTitleStream(movies, 2018, 4.5);

    }

    private static void displayMovieTitleStream(Movie[] movies, int year, double rating) {
        Arrays.stream(movies).
                filter(m -> m.getYear() >= year && m.getRating() >= rating).
                sorted(Comparator.comparingDouble(Movie::getRating).reversed().thenComparingInt(Movie::getYear)).
                map(Movie::getTitle).
                forEach(System.out::println);
        // .map converts objects type in stream. Was Movie, turned to String (title of Movie)
    }
}
