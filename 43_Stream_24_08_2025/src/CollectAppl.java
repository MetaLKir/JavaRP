import java.util.*;
import java.util.stream.Collectors;

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

        // .collect - method stream
        // Collector - interface describes how to collect
        // Collectors - utilities class (ways to collect?)
        List<Movie> moo = Arrays.stream(movies).
                collect(Collectors.toList());
        System.out.println(moo);

        List<Movie> moo1 = Arrays.stream(movies).
                toList();
        System.out.println(moo1);

        Set<Movie> mooSet = Arrays.stream(movies).collect(Collectors.toSet());
        System.out.println(mooSet);

        System.out.println();

        Map<String, Double> map1 = Arrays.stream(movies).
                collect(Collectors.toMap(Movie::getTitle, Movie::getRating, (r1, r2) -> r1, TreeMap::new));
        // 1st argument is key, 2nd is value, 3rd is what to do if the same key,
        // 4th is type of map (may omit this argument)
        System.out.println(map1);

    }
}
