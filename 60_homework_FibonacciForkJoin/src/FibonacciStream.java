import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonacciStream {
    public static long calculateNumber(int numPos) {
        return Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]}).
                limit(numPos + 1).
                map(f -> f[0]).
                reduce((first, second) -> second).
                orElse(0L);
    }
}
