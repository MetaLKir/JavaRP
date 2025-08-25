import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MovieAppl {
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
        //displayMovieTitle(movies, 2019, 4.8);
        Movie m = Arrays.stream(movies).
                filter(c -> c.getYear() >= 2017 && c.getRating() > 4).
                findFirst().get();
        System.out.println("findFirst movie = " + m);

        Movie m1 = Arrays.stream(movies).
                parallel().
                filter(c -> c.getYear() >= 2017 && c.getRating() > 4).
                findAny().get();
        System.out.println("findFirst movie = " + m1);
    }

    private static void displayMovieTitle(Movie[] movies, int year, double rating) {
        List<Movie> list = new ArrayList<>();
        for (Movie m : movies) {
            if (m.getYear() == year && m.getRating() == rating)
                list.add(m);
        }
        list.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                int res = Double.compare(o1.getRating(), o2.getRating());
                return res == 0 ? o1.getTitle().compareTo(o2.getTitle()) : res;
            }
        });

        for (Movie m : list) {
            System.out.println(m.getTitle());
        }
    }


}
